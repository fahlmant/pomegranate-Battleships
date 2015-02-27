package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Destroyer extends Ship{

	public Destroyer(String kind) {
		super(kind);
		size = 3;
		health = size;
		armor = true;
	}
	
	protected void setCQ(char x, int y) {
		if (isVertical) {
			cq = new Coordinates(x, y-1);
		}
		else {
			cq = new Coordinates((char) (x+1), y);
		}
	}
}

