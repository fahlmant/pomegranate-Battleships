package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

import edu.oregonstate.cs361.api.Coordinates;
/**Moves all ships east if possible*/
public class MoveEast extends Move{

	/**Creates a moveEast object
	 * @param undoStack		The stack of coords from the last move
	 * @param ships			The list of ships to move
	 * @param totalShips	The number of totalships on the board
	 */
	public MoveEast(Stack<List<Coordinates>> undoStack, List<Ship> ships, int totalShips) {
		super(undoStack, ships, totalShips);
	}
	
	protected boolean checkMove(Ship s) {
		if(s.isVertical()) {
			if(s.getLocation().get(s.getSize() - 1).getX() <= 'I') {
				s.movedTrue();
			} else {
				s.movedFalse();
			}
		} else {
			if(s.getLocation().get(s.getTail() - 1).getX() <= 'I') {
				s.movedTrue();
			} else {
				s.movedFalse();
			}
		}
		return s.hasMoved();
	}
	
	protected void moveShip(Ship s) {
		if(checkMove(s)) {
			for(int j = 0; j < s.getSize(); j++) {
				s.getLocation().get(j).setX((char) (s.getLocation().get(j).getX() + 1)); 
			}
			s.setCQ((char) (s.getCq().getX() + 1), s.getCq().getY());
		}
	}

}
