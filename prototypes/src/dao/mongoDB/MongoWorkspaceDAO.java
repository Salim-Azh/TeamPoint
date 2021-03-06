/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package dao.mongoDB;

import java.util.HashSet;

import business_logic.user.User;
import business_logic.workspace.Workspace;
import dao.WorkspaceDAO;


/**
 * Description of MongoWorkspaceDAO.
 * 
 * @author 
 */
public class MongoWorkspaceDAO extends WorkspaceDAO {
	// Start of user code (user defined attributes for MongoWorkspaceDAO)

	// End of user code

	/**
	 * The constructor.
	 */
	public MongoWorkspaceDAO() {
		// Start of user code constructor for MongoWorkspaceDAO)
		super();
		// End of user code
	}

	/**
	 * Description of the method getUser.
	 * @param email 
	 * @param password 
	 * @return 
	 */
	public User getUser(String email, String password) {
		// Start of user code for method getUser
		User getUser = null;
		return getUser;
		// End of user code
	}

	/**
	 * Description of the method delete.
	 * @param email 
	 * @return 
	 */
	public Boolean delete(String email) {
		// Start of user code for method delete
		Boolean delete = Boolean.FALSE;
		return delete;
		// End of user code
	}

	/**
	 * Description of the method signup.
	 * @param name 
	 * @param firstName 
	 * @param email 
	 * @param password 
	 * @return 
	 */
	public User signup(String name, String firstName, String email, String password) {
		// Start of user code for method signup
		User signup = null;
		return signup;
		// End of user code
	}

	/**
	 * Description of the method addWorkspace.
	 * @param workspaceName 
	 * @param user 
	 * @return 
	 */
	public Workspace createWorkspace(String workspaceName, User user) {
		// Start of user code for method addWorkspace
		return null;
		// End of user code
	}

	/**
	 * Description of the method deleteWorkspace.
	 * @param workspace 
	 * @return 
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
	 * @return 
	 */
	public Workspace retrieveWorkspace(Workspace workspace) {
		// Start of user code for method retrieveWorkspace
		Workspace retrieveWorkspace = null;
		return retrieveWorkspace;
		// End of user code
	}

	/**
	 * Description of the method getUserWorkspaces.
	 * @param user 
	 * @return 
	 */
	public HashSet<Workspace> getUserWorkspaces(User user) {
		// Start of user code for method getUserWorkspaces
		HashSet<Workspace> getUserWorkspaces = new HashSet<Workspace>();
		return getUserWorkspaces;
		// End of user code
	}

	// Start of user code (user defined methods for MongoWorkspaceDAO)

	// End of user code

}
