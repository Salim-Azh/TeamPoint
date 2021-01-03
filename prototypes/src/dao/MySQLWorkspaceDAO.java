/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import business_logic.user.User;
import business_logic.workspace.Workspace;
import dao.WorkspaceDAO;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of MySQLWorkspaceDAO.
 * 
 * @author 
 */
public class MySQLWorkspaceDAO extends WorkspaceDAO {
	// Start of user code (user defined attributes for MySQLWorkspaceDAO)

	// End of user code

	/**
	 * craete Workspace in the database.
	 * @param workspaceName 
	 * @param user that created it {@link User}
	 * @return a boolean according to the success of insert
	 */
	public Workspace createWorkspace(String workspaceName, User user) {

		if(user == null) {
			return null;
		}

		if(DAO.isNameExist(workspaceName, "workspace")) {
			return null;
		}

		// Query statement
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		int workspaceId = -1;
		
		String query = "INSERT INTO workspace (idUserOwner, workspaceName) "
				+ "VALUES(?, ?)";
		
		try {
			// Getconnection
			prepStmt = DAO.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}
		
		String req = "INSERT INTO workspace"
				+ " (idUserOwner, workspaceName) VALUES("
				+ DAO.stringFormat(user.getUser_id() + "") + ", " 
				+ DAO.stringFormat(workspaceName) + ")";
					
		try {
			prepStmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
			
			rs = prepStmt.getGeneratedKeys();
			rs.next();

			workspaceId = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		// Fill the association between user and workspace
		
		// Query statement
		PreparedStatement stmt = null;
		
		query = "INSERT INTO user_workspace(idUser, idWorkspace, userRole) VALUES(?, ?, ?)";
		
		try {
			// Getconnection
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}
				
		req = "INSERT INTO user_workspace"
				+ " (idUser, idWorkspace, userRole) VALUES("
				+ DAO.stringFormat(user.getUser_id() + "") + ", " 
				+ DAO.stringFormat(workspaceId + "") + ", "
				+ DAO.stringFormat(workspaceAdmin)
				+ ")";
		
		try {
			stmt.execute(req);
		} catch (SQLException e) {
			return null;
		}
			
		// The user has now a workspace
		
		return new Workspace(workspaceName);
	}

	/**
	 * delete Workspace in the database.
	 * @param workspace to be deleted
	 * @return a boolean according to the success of insert
	 */
	public Boolean deleteWorkspace(Workspace workspace) {
		// Start of user code for method deleteWorkspace
		Boolean deleteWorkspace = Boolean.FALSE;
		return deleteWorkspace;
		// End of user code
	}

	/**
	 * Description of the method retrieveWorkspace.
	 * @param workspace 
	 * @return a workspace {@link Workspace}
	 */
	public Workspace retrieveWorkspace(Workspace workspace) {

		if(workspace == null) {
			return null;
		}

		String name = "";
		int id = -1;

		// Result from database
		ResultSet rs = null;
		// Query statement
		Statement stmt = null;

		try {
			// Getconnection from JDBCConnector
			stmt = DAO.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}

		String req = "SELECT idWorkspace, workspaceName"
				+ " FROM workspace "
				+ "WHERE idWorkspace = " + workspace.getWorkspace_id();

		try {
			if (stmt.execute(req)) {
				rs = stmt.getResultSet();
			}
		} catch (SQLException e) {
			// TODO explain connection lost
			e.printStackTrace();
		}

		// if we have a result then move to the next line
		try {
			while(rs.next()){
				id = rs.getInt("idWorkspace");
				name = rs.getString("workspaceName");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return new Workspace(name, id);
	}

	/**
	 * get User Workspaces.
	 * @param user we want the worksapces from
	 * @return a collection of workspace
	 */
	public HashSet<Workspace> getUserWorkspaces(User user) throws Exception {

		if(user == null) {
			return null;
		}

		ArrayList<Integer> resWs = new ArrayList<>();

		HashSet<Workspace> res = new HashSet<>();
		// query on user_workspace to get workspace id of user

		// Result from database
		ResultSet rs = null;
		// Query statement
		PreparedStatement stmt = null;
		String query = "SELECT idUser, idWorkspace"
				+ " FROM user_workspace "
				+ "WHERE idUser = ?";

		try {
			// Getconnection from JDBCConnector
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}

		String req = "SELECT idWorkspace"
				+ " FROM user_workspace "
				+ "WHERE idUser = " + user.getUser_id();

		try {
			if (stmt.execute(req)) {
				rs = stmt.getResultSet();
			}
		} catch (SQLException e) {
			// TODO explain connection lost
			e.printStackTrace();
		}

		// if we have a result then move to the next line
		try {
			while(rs.next()){
				resWs.add(rs.getInt("idWorkspace"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		if (resWs.size() == 0) {
			// TODO customize Exception
			throw new Exception("User has no workspace");
		}

		for (Integer resW : resWs) {
			req = "SELECT idWorkspace, workspaceName"
					+ " FROM workspace "
					+ "WHERE idWorkspace = " + resW;

			try {
				if (stmt.execute(req)) {
					rs = stmt.getResultSet();
				}
			} catch (SQLException e) {
				// TODO explain connection lost
				e.printStackTrace();
			}

			// if we have a result then move to the next line
			try {
				if (rs.next()) {
					String name = rs.getString("workspaceName");
					int id = rs.getInt("idWorkspace");

					Workspace ws = new Workspace(name, id);
					res.add(ws);
				}
			} catch (Exception e) {
				return null;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MySQLWorkspaceDAO mySQL = new MySQLWorkspaceDAO();

		MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();

		User user = null;

		// Login test
		try {
			user = mySQLUserDAO.getUser("galoisnicolas@gmail.com", "toto");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(user);
		
		User workspaceOwner = new User(1, "name", "firstName", "email", "profileDescription", "phoneNumber");
		
		//System.out.println(mySQL.deleteCell(cell));
		
		System.out.println(mySQL.createWorkspace("as", workspaceOwner));

		// Retrieve user workspaces
		try {
			HashSet<Workspace> res = mySQL.getUserWorkspaces(user);
			System.out.println(res);

			System.out.println(mySQL.retrieveWorkspace(res.iterator().next()));

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
