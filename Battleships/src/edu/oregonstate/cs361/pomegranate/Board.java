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
		case "Minesweeper": type = 2;
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
		
		//place ship if horizontal
		if(isVertical == false && valid == true) {
			for(int i = 0; i < type; i++) {
				grid[x - 'A' + i][y - 1] = type;
			}
		}
		
		return valid;
		
	}
	
	public Result attack(char x, int y) {
		String shipName = "No Ship";
		Ship s = new Ship(shipName);
		int location;
		
		if(x < 'A' || x > 'J')
		{
			Result r = new Result(s, Status.INVALID);
			return r;
		}
		else if( y < 1  || y > 10)
		{
			Result r = new Result(s, Status.INVALID);
			return r;		
		}
		
		location = grid[x - 'A'][y - 1];
		if(location > 1 )
		{
			switch(location)
			{
			case(2):
				shipName = "Minesweeper";
			case(3):
				shipName = "Submarine";
			case(4):
				shipName = "Battleship";
			}
			Ship s2 = new Ship(shipName);
			Result r = new Result(s2, Status.HIT);
			return r;
		}
		
		Result r = new Result(s, Status.MISS);
		return r;
	}


}
