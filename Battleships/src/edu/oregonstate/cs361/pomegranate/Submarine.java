package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

/**Creates a Submarine object*/
public class Submarine extends Ship{
	
	private boolean isSubmerged;
	
	/**Creates a submarine object
	 * @param kind    			The string of the name of the ship ("Submarine")
	 * @param isSubmerged   	If the submarine is submerged or not
	 */
	public Submarine(String kind, boolean isSubmerged) {
		super(kind);
		size = 5;
		health = size;
		this.isSubmerged = isSubmerged;
		armor = true;
	}
	
	protected void initCQ(char x, int y) {
		cq = new Coordinates(x, y);
	}
	
	/** Set the location of the submarine
	 * @param x   			The x coord
	 * @param y  			The y coord
	 * @param isVertical    If the ship is vertical or not 
	 * 	 */
	public void setLocation(char x, int y, boolean isVertical) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		this.isVertical = isVertical;
		valid = checkInput(x, y, isVertical);
		Coordinates c;
		initCQ(x, y);
		
		if(isVertical == true) {
			for(int i = 0; i < size -1; i++) {
				c = new Coordinates(x, y+i);
				if(isSubmerged) {
					c.setSubmerged();
				}
				location.add(c);
			}
			c = new Coordinates((char) (x+1), y+2);
			if(isSubmerged) {
				c.setSubmerged();
			}
			location.add(c);
			
		} else {
			for(int i = 0; i < size - 1; i++) {
				c = new Coordinates((char) (x+i), y);
				if(isSubmerged) {
					c.setSubmerged();
				}
				location.add(c);
			}
			c = new Coordinates((char) (x+2), y-1);
			if(isSubmerged) {
				c.setSubmerged();
			}
			location.add(c);
		}
		this.location = location;
	}
	
	protected boolean checkInput(char x, int y, boolean isVertical) {
		boolean valid = true;
		
		if (y > 10 || y < 1) {
			valid = false;
		}
	
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if((y + (size - 1) > 11 || x == 'J') && isVertical == true) {
			valid = false;
		}
		
		if((x + (size - 1) > 'K' || y == 1) && isVertical == false) {
			valid = false;
		}
		
		return valid;
	}

	/** Returns the submerged status*/
	public boolean isSubmerged() {
		return isSubmerged;
	}
	
	/** Returns the extra location on a Submarine*/
	public int getTail() {
		return size - 1;
	}
}
