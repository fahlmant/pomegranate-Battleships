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
		
		int type = 0;
		boolean valid = true;
		
		switch(ship.getKind()) {
		case "Minesweaper": type = 2;
		break;
		case "Submarine": type = 3;
		break;
		case "Battleship": type = 4;
		break;
		}
		
		if (y > 10 || y < 1) {
			valid = false;
		}
		
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if(y - type < 0 && isVertical == true) {
			valid = false;
		}
		
		if(x + type > 'J' && isVertical == false) {
			valid = false;
		}
		
		//place ship if vertical
		if(isVertical == true && valid == true) {
			for(int i = 0; i < type; i++) {
				grid[x - 'A'][y - i - 1] = type;
			}
		}
		
		return valid;
		//TODO Implement
	}
	
	public Result attack(char x, int y) {
		Result r = new Result();
		
		if( x > 'J' || x < 'A')
		{
			r.status = Status.INVALID;
			return r;
		}
		else if(y > 10 || y < 0)
		{
			r.status = Status.INVALID;
			return r;
		}
		
		if(grid[x - 'A'][y - 1] >= 2)
		{
			r.status = Status.HIT;
		}
		else
		{
			r.status = Status.MISS;
		}
		
		
		return r;
	}

}
