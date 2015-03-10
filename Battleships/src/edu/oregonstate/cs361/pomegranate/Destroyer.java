package edu.oregonstate.cs361.pomegranate;

/**Creates a Destroyer Object*/
public class Destroyer extends Ship{

	/**Creates a submarine object
	 * @param kind    			The string of the name of the ship ("Destroyer")
	 */
	public Destroyer(String kind) {
		super(kind);
		size = 3;
		health = size;
		armor = true;
	}
}

