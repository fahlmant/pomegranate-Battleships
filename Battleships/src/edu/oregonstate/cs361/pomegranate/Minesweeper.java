package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Minesweeper extends Ship {
	
	public Minesweeper(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		cq = new Coordinates(x, y);
		armor = false;
	}
}
