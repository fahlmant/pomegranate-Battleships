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
	List<Ship> ships;
	int totalShips;
	private int shipsLeft;
	private boolean isSunk;
	private int sonarCounter;
	Stack<Move> undoStack = new Stack<Move>();
	Stack<Move> redoStack = new Stack<Move>();
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
		
		Coordinates c;
		List<Coordinates> list = new ArrayList<Coordinates>();
		
		if(!isSunk) {
			throw new WeaponUnavailableException();
		}
		
		if(sonarCounter == 0) {
			throw new AmmoExhaustedException();
		}
		
		for(int i = -2; i <= 2; i++) {
			for(int j = 0; j < 5 - (2 * Math.abs(i)); j++) {
				if(grid[x - 'A' + i][y + j - 3 + Math.abs(i)] == Grid.SHIP) {
					c = new Coordinates((char) (x + i), y + j - 2 + Math.abs(i));
					list.add(c);
				}
			}
		}
		sonarCounter--;
		return list;
	}

	public void moveNorth() {
		MoveNorth north = new MoveNorth(this);
		north.moveIt();
	}
	
	public void moveEast() {	
		MoveEast east = new MoveEast(this);
		east.moveIt();
	}
	
	public void moveWest() {
		MoveWest west = new MoveWest(this);
		west.moveIt();
	}
	
	public void moveSouth() {
		MoveSouth south = new MoveSouth(this);
		south.moveIt();

	}
	
	public void undoMove() {
		if(undoStack.isEmpty()) {
			return;
		}
		Move move = undoStack.pop();
		Move undo = move.undo();
		undo.moveIt();
		undoStack.pop();
		redoStack.push(undo);
	}
	
	public void redoMove() {
		if(redoStack.isEmpty()) {
			return;
		}
		Move move = redoStack.pop();
		Move redo = move.undo();
		redo.moveIt();
		
	}

}