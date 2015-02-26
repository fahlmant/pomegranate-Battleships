package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Minesweeper extends Ship {
	
	public Minesweeper(String kind, char x, int y, boolean isVertical) {
		super(kind, x, y, isVertical);
		size = 2;
		health = size;
		valid = checkInput(x, y, isVertical);
		location = setLocation(x, y);
		cq = new Coordinates(x, y);
		armor = false;
	}
}
