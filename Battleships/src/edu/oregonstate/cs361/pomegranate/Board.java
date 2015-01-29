package edu.oregonstate.cs361.pomegranate;

public class Board {

	private int[][] grid;

	public Board() {
		grid = new int [10][10];
		// TODO Implement 2d array that keeps track of ship placement
		
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public boolean placeShip(Ship ship, char x, int y, boolean isVertical) {
		boolean valid = true;
		if (y > 10 || y < 1) {
			valid = false;
		}
		
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if(y - ship.getHealth() < 0 && isVertical == true) {
			valid = false;
		}
		
		if(x + ship.getHealth() > 'J' && isVertical == false) {
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
