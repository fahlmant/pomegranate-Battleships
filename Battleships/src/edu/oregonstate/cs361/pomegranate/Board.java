package edu.oregonstate.cs361.pomegranate;

public class Board {

	public Board() {
		// TODO Implement
		
	}
	
	public boolean placeShip(Ship ship, char x, int y, boolean isVertical) {
		boolean valid = true;
		if (y > 10 || y < 1)
		{
			valid = false;
		}
		return valid;
		//TODO Implement
	}
	
	public Result attack(char x, int y) {
		//TODO Implement
		return null;
	}

}
