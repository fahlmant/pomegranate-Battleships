package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
	private Stack<String> undoStack = new Stack<String>();
	private Stack<String> redoStack = new Stack<String>();
	boolean laserActive;

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
		laserActive = false;
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
		
		if(ship.isValid() && ship.checkOverlap(ships, totalShips)) {
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
	
	private List<Ship> checkLocation(char x, int y) {
		Ship s;
		List<Ship> hitShips = new ArrayList<Ship>();
		Coordinates location = new Coordinates(x, y);
		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).getX() == location.getX()
				   && ships.get(i).getLocation().get(j).getY() == location.getY()) {
					if(ships.get(i).getLocation().get(j).isSubmerged()
							&& !laserActive) {
						break;
					}
					s = checkCQ(ships.get(i), location, j);
					ships.set(i, s);
					hitShips.add(s);
				}
			}
		}

		return hitShips;
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
		
		List<Ship> hitShips = checkLocation(x, y);
		List<Result> result = new ArrayList<Result>();
		Ship s = null;
		
		for(int i = 0; i < hitShips.size(); i++) {
			s = hitShips.get(i);
			Result r = new Result(s, x, y, shipsLeft);
			if(r.getResult() == Status.SUNK) {
				shipsLeft--;
				isSunk = true;
				r = new Result(s, x, y, shipsLeft);
				laserActive = true;
				}
			result.add(r);
		}
		
		if(hitShips.isEmpty()) {
			Result r = new Result(s, x, y, shipsLeft);
			result.add(r);
		}
		return result;
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
		for(int i = 0; i < totalShips; i++)
		{			
			if(ships.get(i).isVertical())
			{
				if(ships.get(i).checkMove("North"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
				}
			}
			else
			{
				if(ships.get(i).checkMove("North"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
				}
			}	
		}	
		undoStack.push("North");
	}
	
	public void moveEast() {
		
		for(int i = 0; i < totalShips; i++)
		{
			if(ships.get(i).isVertical())
			{
				if(ships.get(i).checkMove("East"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() + 1)); 
					}
				}
			}
			else
			{
				if(ships.get(i).checkMove("East"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() + 1)); 
					}
				}
			}
		}
		undoStack.push("East");
	}
	
	public void moveWest() {
		for(int i = 0; i < totalShips; i++)
		{
			if(ships.get(i).isVertical())
			{
				if(ships.get(i).checkMove("West"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() - 1)); 
					}
				}
			}
			else
			{
				if(ships.get(i).checkMove("West"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() - 1)); 
					}
				}
			}
		}
		undoStack.push("West");
	}
	
	public void moveSouth() {
		for(int i = 0; i < totalShips; i++)
		{			
			if(ships.get(i).isVertical())
			{
				if(ships.get(i).checkMove("South"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() - 1); 
					}
				}
			}
			else
			{
				if(ships.get(i).checkMove("South"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() - 1); 
					}
				}
			}
		}
		undoStack.push("South");
	}
	
	public void undoMove() {
		String move = undoStack.pop();
		switch (move) {
		case "North":
			this.moveSouth();
			redoStack.push("South");
			break;
		case "South":
			this.moveNorth();
			redoStack.push("North");
			break;
		case "East":
			this.moveWest();
			redoStack.push("West");
			break;
		case "West":
			this.moveEast();
			redoStack.push("East");
			break;
		default:
			break;
		
			
		}
		//Perform the opposite of that move (for North, perform south etc.)
		//add to undo stack
		
	}
	
	public void redoMove() {
		String move = redoStack.pop();
		switch (move) {
		case "North":
			this.moveSouth();
			undoStack.push("South");
			break;
		case "South":
			this.moveNorth();
			undoStack.push("North");
			break;
		case "East":
			this.moveWest();
			undoStack.push("West");
			break;
		case "West":
			this.moveEast();
			undoStack.push("East");
			break;
		default:
			break;
		
		}
		
	}

}