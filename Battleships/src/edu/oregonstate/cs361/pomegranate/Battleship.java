package edu.oregonstate.cs361.pomegranate;

/**Creates a Battleship Object*/
public class Battleship extends Ship{

	/**Constructor for the Battleship
	 * String of the ship name ("Battleship")
	 * */
	public Battleship(String kind) {
		super(kind);
		size = 4;
		health = size;
		armor = true;
	}
}
