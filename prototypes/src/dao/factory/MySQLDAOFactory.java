package dao.factory;

import dao.BoardDAO;
import dao.ColumnDAO;
import dao.UserDAO;
import dao.WorkspaceDAO;
import dao.CellDAO;
import dao.InvitationDAO;

import dao.mySQL.MySQLBoardDAO;
import dao.mySQL.MySQLColumnDAO;
import dao.mySQL.MySQLUserDAO;
import dao.mySQL.MySQLWorkspaceDAO;
import dao.mySQL.MySQLCellDAO;
import dao.mySQL.MySQLInvitationDAO;

/**
 * Concrete Factory for MySQLDAO products.
 * Implements the {@link DAOFactory} interface
 * @author Salim Azharhoussen, Birane Ba, Raphael Bourret, Nicolas Galois
 */
public class MySQLDAOFactory extends DAOFactory {
	
	/**
	 * The constructor.
	 */
	public MySQLDAOFactory() {}

	/**
	 * Creates a {@link UserDAO} object
	 * @return Returns a {@link UserDAO}
	 */
	public UserDAO createUserDAO() {
		return new MySQLUserDAO();
	}

	/**
	 * Creates a {@link BoardDAO} object
	 * @return Returns a {@link BoardDAO}
	 */
	@Override
	public BoardDAO createBoardDAO() {
		return new MySQLBoardDAO();
	}

	/**
	 * Creates a {@link WorkspaceDAO} object
	 * @return Returns a {@link WorkspaceDAO}
	 */
	@Override
	public WorkspaceDAO createWorkspaceDAO() {
		return new MySQLWorkspaceDAO();
	}

	/**
	 * Creates a {@link ColumnDAO} object
	 * @return Returns a {@link ColumnDAO}
	 */
	@Override
	public ColumnDAO createColumnDAO() {
		return new MySQLColumnDAO();
	}

	@Override
	public CellDAO createCellDAO(){ return new MySQLCellDAO(); }

	@Override
	public InvitationDAO createInvitationDAO() {
		return new MySQLInvitationDAO();
	}
}
