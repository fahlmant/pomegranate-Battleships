package edu.oregonstate.cs361.pomegranate;

public class Board {

	private int[][] grid;
	private Ship minesweeper;
	private Ship submarine;
	private Ship battleship;

	public Board() {
		grid = new int [10][10];
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public boolean placeShip(Ship ship, char x, int y, boolean isVertical) {
		
		int type = 0;
		boolean valid = true;
		
		switch(ship.getKind()) {
		case "Minesweeper":
			type = 2;
			break;
		case "Submarine":
			type = 3;
			break;
		case "Battleship": 
			type = 4;
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
		
		if(valid == true) {
			
			switch(type) {
			case 2:
				minesweeper = ship;
				minesweeper.setHealth(2);
				break;
			case 3:
				submarine = ship;
				submarine.setHealth(3);
				break;
			case 4: 
				battleship = ship;
				battleship.setHealth(4);
				break;
			}
			
			if(isVertical == true) {
				for(int i = 0; i < type; i++) {
					grid[x - 'A'][y - i - 1] = type;
				}
			} else {
				for(int i = 0; i < type; i++) {
					grid[x - 'A' + i][y - 1] = type;
				}
			}
		}
		
		return valid;
		
	}
	
	public Result attack(char x, int y) {
		String shipName = "No Ship";
		Ship s = new Ship(shipName);
		Result r = new Result(s, Status.INVALID);
		int location;
		
		if(x < 'A' || x > 'J')
		{
			return r;
		}
		else if( y < 1  || y > 10)
		{
			return r;		
		}
		
		location = grid[x - 'A'][y - 1];
		if(location > 1 )
		{
			switch(location)
			{
			case(2):
				minesweeper.setHealth(minesweeper.getHealth() - 1);
				r = new Result(minesweeper, Status.HIT);
				break;
			case(3):
				submarine.setHealth(submarine.getHealth() - 1);
				r = new Result(submarine, Status.HIT);
				break;
			case(4):
				battleship.setHealth(battleship.getHealth() - 1);
				r = new Result(battleship, Status.HIT);
				break;
			}
			
			if(r.getShip().getHealth() <= 0) {
				if(minesweeper.getHealth() <= 0 &&
				   submarine.getHealth() <= 0 &&
				   battleship.getHealth() <= 0) {
					r = new Result(r.getShip(), Status.SURRENDER);
				} else {
					r = new Result(r.getShip(), Status.SUNK);
				}
			}
			
			return r;
		}
	
		r = new Result(s, Status.MISS);
		return r;
	}
}
