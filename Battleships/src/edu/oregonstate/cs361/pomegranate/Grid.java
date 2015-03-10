package edu.oregonstate.cs361.pomegranate;

/**Enum for the statuses held by the Grid*/
public enum Grid {

	/**
	 * Represents a miss on the grid.
	 */
	MISS,

	/**
	 * Represents a hit on the grid.
	 */
	HIT,

	/**
	 * Represents a space on the grid that has no ship, hit, or miss.
	 */
	EMPTY,
	
	/**
	 * Represents a ship on the grid.
	 */
	SHIP,
}

