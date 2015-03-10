package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

import edu.oregonstate.cs361.api.Coordinates;

public class MoveWest extends Move {

	public MoveWest(Stack<List<Coordinates>> undoStack, List<Ship> ships, int totalShips) {
		super(undoStack, ships, totalShips);
	}   
	
	protected boolean checkMove(Ship s) {
		if(s.getLocation().get(0).getX() >= 'B') {
			s.movedTrue();
		} else {
			s.movedFalse();
		}
		return s.hasMoved();
	}
	
	protected void moveShip(Ship s) {
		if(checkMove(s)) {
			for(int j = 0; j < s.getSize(); j++) {
				s.getLocation().get(j).setX((char) (s.getLocation().get(j).getX() - 1)); 
			}
			s.setCQ((char) (s.getCq().getX() - 1), s.getCq().getY());
		}
	}

}
