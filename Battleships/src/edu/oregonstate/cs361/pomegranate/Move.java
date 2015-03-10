package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.oregonstate.cs361.api.Coordinates;

abstract public class Move {

	protected Stack<List<Coordinates>> undoStack;
	protected List<Ship> ships;
	protected int totalShips;
	
	public Move(Stack<List<Coordinates>> undoStack, List<Ship> ships, int totalShips) {
		this.undoStack = undoStack;
		this.ships = ships;
		this.totalShips = totalShips;
	}

	public Stack<List<Coordinates>> moveIt() {
		for(int i = 0; i < totalShips; i++) {
			undoStack.push(copyLocation(ships.get(i)));
			moveShip(ships.get(i));
		}
		return undoStack;
	}
	
	abstract protected void moveShip(Ship s);
	
	protected List<Coordinates> copyLocation(Ship s) {
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
