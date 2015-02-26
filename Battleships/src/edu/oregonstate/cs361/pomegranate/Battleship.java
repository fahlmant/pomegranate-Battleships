package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Battleship extends Ship{

	public Battleship(String kind, char x, int y, boolean isVertical) {
		super(kind, x, y, isVertical);
		size = 4;
		health = size;
		valid = checkInput(x, y, isVertical);
		location = setLocation(x, y);
		if (isVertical){
			cq = new Coordinates(x, y-2);
		}
		else {
			cq = new Coordinates((char) (x+2), y);
		}
		armor = true;
	}
}
