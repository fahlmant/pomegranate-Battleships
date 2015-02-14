package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Battleship extends Ship{

	public Battleship(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		if (isVertical){
			cq = new Coordinates(x, y-2);
		}
		else {
			cq = new Coordinates((char) (x+2), y);
		}
		armor = true;
	}
}
