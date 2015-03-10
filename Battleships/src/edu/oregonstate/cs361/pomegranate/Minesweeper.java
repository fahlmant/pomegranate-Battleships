package edu.oregonstate.cs361.pomegranate;

/**Creates a Minesweeper object*/
public class Minesweeper extends Ship {
	
	/**Constructs a Minsweeper  object
	 * @param kind		The string of the name ("Minesweeper")
	 */
	public Minesweeper(String kind) {
		super(kind);
		size = 2;
		health = size;
		armor = false;
	}
}
