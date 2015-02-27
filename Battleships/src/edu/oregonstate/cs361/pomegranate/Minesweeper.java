package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Minesweeper extends Ship {
	
	public Minesweeper(String kind) {
		super(kind);
		size = 2;
		health = size;
		armor = false;
	}
	
	protected void setCQ(char x, int y) {
		cq = new Coordinates(x, y);
	}
}
