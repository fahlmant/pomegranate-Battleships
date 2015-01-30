package edu.oregonstate.cs361.pomegranate;

public enum Status {

	/**
	 * The result if an attack results in a miss.
	 */
	MISS,

	/**
	 * The result if an attack results in a hit on an enemy ship.
	 */
	HIT,

	/**
	 * The results if an attack results in the defeat of the opponent (a
	 * surrender).
	 */
	SURRENDER,
	/**
	 * The results if an attack results in a ship sinking).
	 */
	SUNK,
	
	/**
	 * The result if the coordinates given are invalid.
	 */
	INVALID

}
