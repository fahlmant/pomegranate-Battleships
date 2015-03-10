package edu.oregonstate.cs361.pomegranate;

import java.util.List;
import java.util.Stack;

import edu.oregonstate.cs361.api.Coordinates;

/**Moves all ships south*/
public class MoveSouth extends Move{

	/**Creates a moveSouth object
	 * @param undoStack		The stack of coords from the last move
	 * @param ships			The list of ships to move
	 * @param totalShips	The number of totalships on the board
	 */
	public MoveSouth(Stack<List<Coordinates>> undoStack, List<Ship> ships, int totalShips) {
		super(undoStack, ships, totalShips);
	}
	
	protected boolean checkMove(Ship s) {
		if(s.getLocation().get(s.getTail() - 1).getY() <= 9) {
			s.movedTrue();
		} else {
			s.movedFalse();
		}
		return s.hasMoved();
	}
	
	protected void moveShip(Ship s) {
		if(checkMove(s)) {	
			for(int j = 0; j < s.getSize(); j++) {
				s.getLocation().get(j).setY(s.getLocation().get(j).getY() + 1); 
			}
			s.setCQ(s.getCq().getX(), s.getCq().getY() + 1);
		}
	}
}
