package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

public class MoveSouth extends Move{

	public MoveSouth(Stack<Move> undoStack, List<Ship> ships, int totalShips) {
		super(undoStack, ships, totalShips);
		undoType = null;
	}
	
	public Stack<Move> moveIt() {
		for(int i = 0; i < totalShips; i++) {			
			if(ships.get(i).isVertical()) {
				if(ships.get(i).checkMove("South")) {
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() + 1);
					ships.get(i).movedTrue();
				}
			} else {
				if(ships.get(i).checkMove("South")) {
					for(int j = 0; j < ships.get(i).getSize(); j++) {
						ships.get(i).getLocation().get(j).setY(ships.get(i).getLocation().get(j).getY() + 1); 
					}
					ships.get(i).setCQ(ships.get(i).getCq().getX(), ships.get(i).getCq().getY() + 1);
					ships.get(i).movedTrue();
				}
			}
		}
		undoStack.push(this);
		return undoStack;
	}
	
	public Move undo() {
		undoType = new MoveNorth(undoStack, ships, totalShips);
		return undoType;
	}

}
