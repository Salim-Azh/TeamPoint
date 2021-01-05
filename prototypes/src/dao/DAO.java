package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import business_logic.board.types.Type;
import business_logic.board.types.TypeFactory;
import business_logic.user.User;
import database.JDBCConnector;

/**
 * DAO interface
 * @author Salim Azharhoussen, Birane Ba, Raphael Bourret, Nicolas Galois
 */
public interface DAO {
	
	/**
	 *  Format the date database for query 
	 * @param date from Date java package
	 * @return the correct String format according to SQL "yyyy-mm-dd"
	 */
	static String dateFormat(Date date) {
		
		if(date == null) {
			return null;
		}
		
		// The ZoneId here has no implication since we only keep the year, month and day
		ZonedDateTime localDate = date.toInstant().atZone(ZoneId.of("Europe/Paris"));
		
		int year = localDate.getYear(); // Year
		String dateFormat = Integer.toString(year);
		dateFormat += "-";
		dateFormat += localDate.getMonthValue(); // Month
		dateFormat += "-";
		dateFormat += localDate.getDayOfMonth(); // Day
		
		return dateFormat;
	}
	
	/**
	 * Format the given string for database query
	 * @param str the String
	 * @return a null pointer if str null, if not null return "str" : str with double quotes
	 */
	static String stringFormat(String str) {
		
		if(str == null) {
			return null;
		}
		
		return "\"" + str + "\"";
	}
	
	static Connection getConnection() {
		return JDBCConnector.getJDBCConnectorInstance().getConnection();
	}
	
	static void closeConnection() {
		JDBCConnector.getJDBCConnectorInstance().closeConnection();
	}

	/**
	 *
	 * @param name, table
	 * @return the column in the database according to a name, if not found return null
	 */
	static boolean isNameExist(String name, String table) {

		// Result from database
		ResultSet rs = null;
		// Query statement
		PreparedStatement stmt;

		String query = "SELECT * "
				+ "FROM `" + table + "` "
				+ "WHERE " + table + "Name = ?";

		try {
			// Getconnection from JDBCConnector
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
			return false;
		}

		String req = "SELECT * "
				+ "FROM `" + table + "` "
				+ "WHERE " + table + "Name = " + DAO.stringFormat(name);

		try {
			if (stmt.execute(req)) {
				rs = stmt.getResultSet();
			}
		} catch (SQLException e) {
			// TODO explain connection lost
			e.printStackTrace();
			return false;
		}
		// if we have a result then move to the next line

		try {
			assert rs != null;
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	static User getUserById(int id) throws Exception {
		if (id == -1) {
			throw new Exception();
		}
		// List of all fields to create an user
		ArrayList<String> resultat = new ArrayList<>();
		// Result from database
		ResultSet rs = null;
		// Query statement
		PreparedStatement stmt = null;
		String query = "SELECT name, firstName, email, phoneNumber,"
				+ "profileDescription, birthday" + " FROM User "
				+ "WHERE idUser = ?";

		try {
			// Getconnection from JDBCConnector
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}

		String req = "SELECT idUser, name, firstName, email, phoneNumber,"
				+ "profileDescription, birthday" + " FROM User "
				+ "WHERE idUser = " + DAO.stringFormat(id + "");

		try {
			assert stmt != null;
			if (stmt.execute(req)) {
				rs = stmt.getResultSet();
			}
		} catch (SQLException e) {
			// TODO explain connection lost
			e.printStackTrace();
		}

		// if we have a result then move to the next line
		assert rs != null;
		if(rs.next()){

			resultat.add(rs.getInt("idUser") + "");
			resultat.add(rs.getString("name"));
			resultat.add(rs.getString("firstName"));

			resultat.add(rs.getString("email"));

			resultat.add(rs.getString("profileDescription"));
			resultat.add(rs.getString("phoneNumber"));
		}

		if (resultat.size() == 0) {
			// TODO customize Exception
			throw new Exception("User not found");
		}

		int idUser = Integer.parseInt(resultat.get(0));
		String name = resultat.get(1);
		String firstName = resultat.get(2);
		String emailUser = resultat.get(3);
		String profileDesc = resultat.get(4);
		String phoneNumber = resultat.get(5);

		return new User(idUser, name, firstName, emailUser, profileDesc, phoneNumber);
	}

	/**
	 * create the type given an id
	 * @param idType id of the type
	 * @return the Type {@link Type}
	 */
	static Type getTypeById(int idType) {
		if (idType == -1) {
			return null;
		}
		// Result from database
		ResultSet rs = null;
		// Query statement
		PreparedStatement stmt = null;
		String query = "SELECT * "
				+ " FROM Type "
				+ "WHERE idType = ?";

		try {
			// Getconnection from JDBCConnector
			stmt = DAO.getConnection().prepareStatement(query);
		} catch (SQLException e) {
			// TODO explain database not found
			e.printStackTrace();
		}

		String req = "SELECT * "
				+ " FROM Type "
				+ "WHERE idType = " + DAO.stringFormat(idType + "");

		try {
			assert stmt != null;
			if (stmt.execute(req)) {
				rs = stmt.getResultSet();
			}
		} catch (SQLException e) {
			// TODO explain connection lost
			e.printStackTrace();
		}

		Type type = null;
		try {
			assert rs != null;
			if(rs.next()) {
				int id = rs.getInt("idType");
				String label = rs.getString("nameType");
				String descr = rs.getString("descriptionType");

				type = TypeFactory.createType(id, label, descr);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return type;
	}

}