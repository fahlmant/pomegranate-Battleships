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
	
	public boolean placeShip(Ship ship, char x, int y, boolean isVertical) {
		
		ship.setLocation(x, y, isVertical);
		
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
		List<Ship> hitShips;
		if(laserActive) {
			LaserAttack l = new LaserAttack(x, y, ships, totalShips);
			hitShips = l.hitShips();
		} else {
			RegularAttack r = new RegularAttack(x, y, ships, totalShips);
			hitShips = r.hitShips();
		}
		return hitShips;
	}
	
	public List<Result> attack(char x, int y) {
		
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
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() - 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() - 1);
				}
			}
			else
			{
				if(ships.get(i).checkMove("North"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() - 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() - 1);
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
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() + 1), ships.get(i).getCq().getY());
				}
			}
			else
			{
				if(ships.get(i).checkMove("East"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() + 1)); 
					}
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() + 1), ships.get(i).getCq().getY());
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
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() - 1), ships.get(i).getCq().getY());
				}
			}
			else
			{
				if(ships.get(i).checkMove("West"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() - 1)); 
					}
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() - 1), ships.get(i).getCq().getY());
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
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() + 1);
				}
			}
			else
			{
				if(ships.get(i).checkMove("South"))
				{
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() + 1);
				}
			}
		}
		undoStack.push("South");
	}
	
	public void undoMove() {
		if(undoStack.isEmpty()) {
			return;
		}
		String move = undoStack.pop();
		switch (move) {
		case "North":
			this.moveSouth();
			undoStack.pop();
			redoStack.push("South");
			break;
		case "South":
			this.moveNorth();
			redoStack.push("North");
			undoStack.pop();
			break;
		case "East":
			this.moveWest();
			redoStack.push("West");
			undoStack.pop();
			break;
		case "West":
			this.moveEast();
			redoStack.push("East");
			undoStack.pop();
			break;
		default:
			break;
		
			
		}
		//Perform the opposite of that move (for North, perform south etc.)
		//add to undo stack
		
	}
	
	public void redoMove() {
		if(redoStack.isEmpty()) {
			return;
		}
		String move = redoStack.pop();
		switch (move) {
		case "North":
			this.moveSouth();
			break;
		case "South":
			this.moveNorth();
			break;
		case "East":
			this.moveWest();
			break;
		case "West":
			this.moveEast();
			break;
		default:
			break;
		
		}
		
	}

}