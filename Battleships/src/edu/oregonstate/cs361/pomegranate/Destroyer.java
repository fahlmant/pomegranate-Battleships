package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Destroyer extends Ship{

	public Destroyer(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		if (isVertical){
			cq = new Coordinates(x, y-1);
		}
		else {
			cq = new Coordinates((char) (x+1), y);
		}	
		armor = true;
	}
}

