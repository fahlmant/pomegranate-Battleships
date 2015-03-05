package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

public class MoveEast extends Move{

	public MoveEast(Stack<Move> undoStack, List<Ship> ships, int totalShips) {
		super(undoStack, ships, totalShips);
		undoType = null;
	}
	
	public Stack<Move> moveIt() {
		for(int i = 0; i < totalShips; i++) {
			if(ships.get(i).isVertical()) {
				if(ships.get(i).checkMove("East")) {
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() + 1)); 
					}
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() + 1), ships.get(i).getCq().getY());
				}
			} else {
				if(ships.get(i).checkMove("East")) {
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setX((char) (ships.get(i).getLocation().get(j).getX() + 1)); 
					}
					ships.get(i).setCQ((char) (ships.get(i).getCq().getX() + 1), ships.get(i).getCq().getY());
				}
			}
		}
		undoStack.push(this);
		return undoStack;
	}
	
	public Move undo() {
		undoType = new MoveWest(undoStack, ships, totalShips);
		return undoType;
	}

}
