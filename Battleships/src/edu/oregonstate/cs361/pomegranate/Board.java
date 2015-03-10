package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

import java.lang.Math;

public class Board {
	
	private Grid[][] grid;
	private List<Ship> ships;
	private int totalShips;
	private int shipsLeft;
	private boolean isSunk;
	private int sonarCounter;
	private boolean laserActive;
	private Stack<List<Coordinates>> undoStack;
	private Stack<List<Coordinates>> redoStack;


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
		undoStack = new Stack<List<Coordinates>>();
		redoStack = new Stack<List<Coordinates>>();
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
		
		Coordinates c;
		List<Coordinates> list = new ArrayList<Coordinates>();
		
		if(!isSunk) {
			throw new WeaponUnavailableException();
		}
		
		if(sonarCounter == 0) {
			throw new AmmoExhaustedException();
		}
		
		if(checkSonar(x, y)) {
			for(int i = -2; i <= 2; i++) {
				for(int j = 0; j < 5 - (2 * Math.abs(i)); j++) {
					if(grid[x - 'A' + i][y + j - 3 + Math.abs(i)] == Grid.SHIP) {
						c = new Coordinates((char) (x + i), y + j - 2 + Math.abs(i));
						list.add(c);
					}
				}
			}
		}
		sonarCounter--;
		return list;
	}
	
	private boolean checkSonar(char x, int y) {
		if(x < 'C' || x > 'H') {
			return false;
		}
		if(y < 3 || y > 8) {
			return false;
		}
		return true;
	}

	public void moveNorth() {
		MoveNorth north = new MoveNorth(undoStack, ships, totalShips);
		undoStack = north.moveIt();
	}
	
	public void moveEast() {	
		MoveEast east = new MoveEast(undoStack, ships, totalShips);
		undoStack = east.moveIt();
	}
	
	public void moveWest() {
		MoveWest west = new MoveWest(undoStack, ships, totalShips);
		undoStack = west.moveIt();
	}
	
	public void moveSouth() {
		MoveSouth south = new MoveSouth(undoStack, ships, totalShips);
		undoStack = south.moveIt();

	}
	
	public void undoMove() {
		List<Coordinates> move;
		char x;
		int y;
		if(undoStack.isEmpty()) {
			return;
		}
		for(int i = totalShips - 1; i >= 0; i--) {
			move = undoStack.pop();
			x = move.get(0).getX();
			y = move.get(0).getY();
			redoStack.push(copyLocation(ships.get(i)));
			ships.get(i).setLocation(x, y, ships.get(i).isVertical());
		}
	}
	
	public void redoMove() {
		List<Coordinates> move;
		char x;
		int y;
		if(redoStack.isEmpty()) {
			return;
		}
		for(int i = 0; i < totalShips; i++) {
			move = redoStack.pop();
			x = move.get(0).getX();
			y = move.get(0).getY();
			undoStack.push(copyLocation(ships.get(i)));
			ships.get(i).setLocation(x, y, ships.get(i).isVertical());
		}
	}
	
	private List<Coordinates> copyLocation(Ship s) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		char x;
		int y;
		for(int i = 0; i < s.getSize(); i++) {
			x = s.getLocation().get(i).getX();
			y = s.getLocation().get(i).getY();
			location.add(new Coordinates(x, y));
		}
		return location;
	}
}