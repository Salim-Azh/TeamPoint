/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package dao;

import business_logic.user.User;

/**
 * MySQL specific DAO for User. This class extends {@link UserDAO}
 * @author Salim Azharhoussen, Birane Ba, Raphael Bourret, Nicolas Galois
 */
public class MySQLUserDAO extends UserDAO {
	
	/**
	 * The constructor.
	 */
	public MySQLUserDAO() {
		//TODO construct the UserDAO object
	}

	@Override
	public Boolean create(User obj) {
		// TODO user insertion query to the DB
		return null;
	}

	@Override
	public Boolean delete(User obj) {
		// TODO user deletion query to the database
		return null;
	}

	@Override
	public User getUser(String email, String password) throws Exception {
		//TODO user select where email and pwd query in the database 
		return null;
	}
}
