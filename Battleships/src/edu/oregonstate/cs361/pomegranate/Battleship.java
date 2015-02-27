package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Battleship extends Ship{

	public Battleship(String kind) {
		super(kind);
		size = 4;
		health = size;
		armor = true;
	}
	
	protected void setCQ(char x, int y) {
		if (isVertical) {
			cq = new Coordinates(x, y-2);
		}
		else {
			cq = new Coordinates((char) (x+2), y);
		}
	}
}
