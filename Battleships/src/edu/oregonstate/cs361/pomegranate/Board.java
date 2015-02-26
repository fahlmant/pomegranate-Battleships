package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

public class Board {
	
	private Grid[][] grid;
	private List<Ship> ships;
	private int totalShips;
	private int shipsLeft;
	private boolean isSunk;
	private int sonarCounter;

	public Board() {
		grid = new Grid [10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				grid[i][j] = Grid.EMPTY;
			}
		}
		ships = new ArrayList<Ship>();
		shipsLeft = 0;
		totalShips = 0;
		isSunk = false;
		sonarCounter = 2;
	}
	
	public Ship getShip(int i) {
		return ships.get(i);
	}
	
	public Grid[][] getGrid() {
		return grid;
	}
	
	public int getShipsLeft() {
		return shipsLeft;
	}
	
	public boolean placeShip(Ship ship) {
		
		//TODO check to make sure ships aren't placed on top of each other
		
		if(ship.isValid() == true) {
			ships.add(ship);
			shipsLeft++;
			totalShips++;
			for(int i = 0; i < ship.getSize(); i++) {
				grid[ship.getLocation().get(i).getX() - 'A'][ship.getLocation().get(i).getY() - 1] = Grid.SHIP;
			}
			return true;
		}
		return false;
	}
	
	private int checkLocation(char x, int y) {
		Ship s;
		Coordinates location = new Coordinates(x, y);
		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).getX() == location.getX()
				   && ships.get(i).getLocation().get(j).getY() == location.getY()) {
					s = checkCQ(ships.get(i), location, j);
					ships.set(i, s);
					return i;
				}
			}
		}

		return -1;
	}
	
	private Ship checkCQ(Ship s, Coordinates c, int j) {
		if(s.getCq().getX() == c.getX() && s.getCq().getY() == c.getY()) {
			if(s.isArmor()) {
				s.destroyArmor();
				return s;
			} else {
				s.cqDestroyed();
				for(int i = 0; i < s.getSize(); i++) {
					c = s.getLocation().get(i);
					c.hit();
					s.getLocation().set(i, c);
				}
				return s;
			}
		}
		s.takeDamage();
		c = s.getLocation().get(j);
		c.hit();
		s.getLocation().set(j, c);
		return s;
	}
	
	public List<Result> attack(char x, int y) {
		
		//TODO implement a way to keep track of misses
		//     implement a way to keep track of hits
		
		int i = checkLocation(x, y);
		Ship s = null;
		boolean isHit = false; 
		
		if(i != -1) {
			s = ships.get(i);
			isHit = isHit(x, y, i); 
		}
		
		List<Result> result = new ArrayList<Result>();
		
		Result r = new Result(s, x, y, shipsLeft);
		r.setHit(isHit);
	
		if(r.getResult() == Status.SUNK) {
			shipsLeft--;
			isSunk = true;
			r = new Result(s, x, y, shipsLeft);
		}
		
		result.add(r);
		
		return result;
	}
	
	private boolean isHit(char x, int y, int i) {
		for(int j = 0; j < ships.get(i).getSize(); j++) {
			if(ships.get(i).getLocation().get(j).isHit()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns the result of a sonar pulse
	 * @param x the column of the attack cell
	 * @param y the row of the attack cell
	 * @return a list of coordinates revealed as containing enemy ships
	 * @throws WeaponUnavailableException if the weapon is not available for use
	 * @throws AmmoExhaustedException if the ammo is exhausted
	 */
	public List<Coordinates> sonarPulse(char x, int y) throws WeaponUnavailableException, AmmoExhaustedException {
		
		//TODO add error checking for using sonar out of bounds
		
		if(!isSunk) {
			throw new WeaponUnavailableException();
		}
		
		if(sonarCounter == 0) {
			throw new AmmoExhaustedException();
		}
		
		List<Coordinates> list = new ArrayList<Coordinates>();
		int xCord = x - 'A';
		int yCord = y - 1;
		
		for(int i = yCord - 2; i < yCord + 2; i++) {
			if(grid[xCord][i] == Grid.SHIP) {
				Coordinates c = new Coordinates((char)(xCord + 'A'), i + 1);
				list.add(c);				
			}
		}
		
		for(int i = yCord - 1; i < yCord + 1; i++) {
			if(grid[xCord - 1][i] == Grid.SHIP) {
				Coordinates c = new Coordinates((char)(xCord + 'A' - 1), i + 1);
				list.add(c);				
			}
		}
		
		for(int i = yCord - 1; i < yCord + 1; i++) {
			if(grid[xCord + 1][i] == Grid.SHIP) {
				Coordinates c = new Coordinates((char)(xCord + 'A' + 1), i + 1);
				list.add(c);				
			}
		}
		
		if(grid[xCord - 2][yCord] == Grid.SHIP) {
			Coordinates c = new Coordinates((char)(xCord + 'A' - 2), yCord + 1);
			list.add(c);				
		}
		
		if(grid[xCord + 2][yCord] == Grid.SHIP) {
			Coordinates c = new Coordinates((char)(xCord + 'A' + 2), yCord + 1);
			list.add(c);				
		}
	
		sonarCounter--;
		return list;
	}

	public void moveNorth() {
		
		int shipSize;
		for(int i = 0; i < totalShips; i++)
		{
			shipSize = ships.get(i).getSize();			
			if(ships.get(i).isVertical())
			{
				//check if move will be valid
				//add 'ship' to coord above
				//remove 'ship' from coord below
			}
			else
			{
				//check if move will be valid
				//add 'ship' to all coords directly north of the ship
				//remove 'ship' from the original ships coords
			}
			
			//Push move onto stack
		}	
	}
	
	public void moveEast() {
		
		int shipSize;
		for(int i = 0; i < totalShips; i++)
		{
			shipSize = ships.get(i).getSize();
			if(ships.get(i).isVertical)
			{
				//check if move will be valid
				//add 'ship' to all coords east of the ship
				//remove 'ship' from original placement
			}
			else
			{
				//check if move will be valid
				//add 'ship' to east of coords
				//remove 'ship' from far west coords
			}
		}		
	}
	
	public void moveWest() {
		int shipSize;
		for(int i = 0; i < totalShips; i++)
		{
			shipSize = ships.get(i).getSize();
			if(ships.get(i).isVertical)
			{
				//check if move will be valid
				//add 'ship' to all coords west of the ship
				//remove 'ship' from original placement
			}
			else
			{
				//check if move will be valid
				//add 'ship' to west of coords
				//remove 'ship' from far east coords
			}
		}		
		
	}
	
	public void moveSouth() {
		int shipSize;
		for(int i = 0; i < totalShips; i++)
		{
			shipSize = ships.get(i).getSize();			
			if(ships.get(i).isVertical())
			{
				//check if move will be valid
				//add 'ship' to coord below
				//remove 'ship' from coord above
			}
			else
			{
				//check if move will be valid
				//add 'ship' to all coords directly south of the ship
				//remove 'ship' from the original ships coords
			}
			
			//Push move onto stack
		}	
		
	}
	
	public void undoMove() {
		//pop last move off the stack
		//Perform the opposite of that move (for North, perform south etc.)
		//add to undo stack
		
	}
	
	public void redoMove() {
		//pop last undo stack move
		//perform opposite
		//push move onto stack
		
	}

}