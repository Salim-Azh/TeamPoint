/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import business_logic.board.AbstractType;
import business_logic.board.Board;
import business_logic.board.Column;
import business_logic.board.Item;
import business_logic.board.ItemCollection;
import business_logic.board.Permission;
import business_logic.user.User;
import business_logic.workspace.Workspace;
import dao.ColumnDAO;

import javax.xml.transform.Result;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of MySQLBoardDAO.
 * 
 * @author 
 */
public class MySQLBoardDAO extends BoardDAO {
	// Start of user code (user defined attributes for MySQLBoardDAO)

	// End of user code

	/**
	 * The constructor.
	 */
	public MySQLBoardDAO() {
		// Start of user code constructor for MySQLBoardDAO)
		super();
		// End of user code
	}

	@Override
	public Board addBoard(String name, Workspace workspace, User user, Permission permission) {

		if(name.isBlank() || workspace == null | user == null || permission == null) {
			return null;
		}

		if(DAO.isNameExist(name, "board")) {
			return null;
		}

		// Query statement
		PreparedStatement stmt = null;
				
		String query = "INSERT INTO board"
				+ " (userOwner, idPermission, boardName, parentWorkspace) VALUES(?, ?, ?, ?)";
		
		try {
			// Getconnection
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}
		
		String req = "INSERT INTO board"
				+ " (userOwner, idPermission, boardName, parentWorkspace) VALUES("
				+ DAO.stringFormat(user.getUser_id() + "") + ", " 
				+ DAO.stringFormat(permission.getIdPermission() + "") + ", "
				+ DAO.stringFormat(name) + ", "
				+ DAO.stringFormat(workspace.getWorkspace_id() + "")
				+ ")";

		System.out.println(req);
							
		try {
			stmt.execute(req);
		} catch (SQLException e) {
			return null;
		}
				
		return new Board(name, workspace, user);
	}	

	@Override
	public Boolean deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board retrieveBoard(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addItemCollection(String itemCollectionName, Board board) {

		if(board == null || itemCollectionName.isBlank()) {
			return null;
		}

		if(DAO.isNameExist(itemCollectionName, "itemCollection")) {
			return null;
		}

		// Query statement
		PreparedStatement stmt = null;
		int itemCollectionId = -1;
						
		String query = "INSERT INTO itemcollection"
				+ " (idBoard, itemCollectionName) VALUES(?, ?)";
		try {
			// Getconnection
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}
				
		String req = "INSERT INTO itemcollection"
				+ " (idBoard, itemCollectionName) VALUES("
				+ DAO.stringFormat(board.getBoard_id() + "") + ", " 
				+ DAO.stringFormat(itemCollectionName)
				+  ")";

		try {
			stmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			return false;
		}

		// Add the item collection id to board_contains

		try {
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			itemCollectionId = rs.getInt(1);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		stmt = null;

		query = "INSERT INTO board_contains"
				+ " (idBoard, idItemCollection, idColumn) VALUES(?, ?, ?)";
		try {
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		req = "INSERT INTO board_contains"
				+ " (idBoard, idItemCollection, idColumn) VALUES("
				+ DAO.stringFormat(board.getBoard_id() + "") + ", "
				+ DAO.stringFormat(itemCollectionId + "") + ", "
				+ null
				+ ")";

		System.out.println(req);

		try {
			stmt.execute(req);
		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	@Override
	public Boolean deleteItemCollection(ItemCollection itemCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addItem(ItemCollection itemCollection, String itemLabel) {

		if(itemCollection == null || itemLabel.isBlank()) {
			return null;
		}

		if(DAO.isNameExist(itemLabel, "item")) {
			return null;
		}

		// Query statement
		PreparedStatement stmt = null;
		String query = "INSERT INTO item"
				+ " (idBoard, idItemCollection, itemName) VALUES(?, ?, ?)";
		int itemId = -1;
								
		try {
			// Getconnection
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}
		
		String req = "INSERT INTO item"
				+ " (idBoard, idItemCollection, itemName) VALUES("
				+ DAO.stringFormat(itemCollection.getParentBoard().getBoard_id() + "") + ", " 
				+ DAO.stringFormat(itemCollection.getItemCollection_id() + "") + ", "
				+ DAO.stringFormat(itemLabel)
				+  ")";
		
		try {
			stmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			return false;
		}

		// Add the item id to item_collection_item

		try {
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			itemId = rs.getInt(1);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		stmt = null;

		query = "INSERT INTO item_collection_item"
				+ " (idItemCollection, idItem) VALUES(?, ?)";
		try {
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		req = "INSERT INTO item_collection_item"
				+ " (idItemCollection, idItem) VALUES("
				+ DAO.stringFormat(itemCollection.getItemCollection_id() + "") + ", "
				+ DAO.stringFormat(itemId + "")
				+ ")";

		System.out.println(req);

		try {
			stmt.execute(req);
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean deleteItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		MySQLBoardDAO mySQL = new MySQLBoardDAO();

		Workspace parentWorkspace = new Workspace("salut");
		User boardOwner = new User(1, "name", "firstName", "email", "profileDescription", "phoneNumber");
		Board parentBoard = new Board(0, "test", parentWorkspace, boardOwner);
		ItemCollection itemCol = new ItemCollection("test", 0, parentBoard);

		//Board res = mySQL.addBoard("TestBoard", parentWorkspace, boardOwner, new Permission(0, "Perm", "desc"));
		//System.out.println(res);

		//Boolean res = mySQL.addItemCollection("testItemCol", parentBoard);
		//System.out.println(res);

		Boolean res = mySQL.addItem(itemCol, "itemTest");
		System.out.println(res);
	}

}
