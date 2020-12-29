/*******************************************************************************
 * 2020, All rights reserved.
 *******************************************************************************/
package business_logic.board;

/**
 * Type used in a {@link Column} to specify a set of {@link User}
 *
 * @author Salim Azharhoussen, Birane Ba, Raphael Bourret, Nicolas Galois
 */
public class PersonType extends AbstractType implements  {
	/**
	 * All the {@link User} of the {@link PersonType}
	 */
	public HashSet<User> users;
	
	/**
	 * Create an empty {@link PersonType}
	 */
	public PersonType() {}
	
	/**
	 * Create a {@link PersonType} giving a set of {@link User}
	 * @param users A set of {@link User}
	 */
	public PersonTypes(HashSet<User> users) {
		PersonType PersonTypes = null;
		return PersonTypes;
	}
	 
	/**
	 * @return All the {@link User} of the {@link PersonType}
	 */
	public HashSet<User> getUsers() {
		return this.users;
	}

}
