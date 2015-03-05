package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

abstract public class Move {

	protected Move undoType;
	protected Stack<Move> undoStack;
	protected List<Ship> ships;
	protected int totalShips;
	
	public Move(Stack<Move> undoStack, List<Ship> ships, int totalShips) {
		this.undoStack = undoStack;
		this.ships = ships;
		this.totalShips = totalShips;
	}

	abstract public Stack<Move> moveIt();
	
	abstract public Move undo();
}
