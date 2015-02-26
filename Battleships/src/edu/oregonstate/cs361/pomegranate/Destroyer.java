package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Destroyer extends Ship{

	public Destroyer(String kind, char x, int y, boolean isVertical) {
		super(kind, x, y, isVertical);
		size = 3;
		health = size;
		valid = checkInput(x, y, isVertical);
		location = setLocation(x, y);
		if (isVertical){
			cq = new Coordinates(x, y-1);
		}
		else {
			cq = new Coordinates((char) (x+1), y);
		}	
		armor = true;
	}
}

