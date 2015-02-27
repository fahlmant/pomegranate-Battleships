package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

public class Submarine extends Ship{
	
	private boolean isSubmerged;
	
	public Submarine(String kind, char x, int y, boolean isVertical, boolean isSubmerged) {
		super(kind, x, y, isVertical);
		size = 5;
		health = size;
		valid = checkInput(x, y, isVertical);
		location = setLocation(x, y);
		if (isVertical) {
			cq = new Coordinates(x, y-3);
		}
		else {
			cq = new Coordinates((char) (x+3), y);
		}	
		armor = true;
		this.isSubmerged = isSubmerged;
	}
	
	protected List<Coordinates> setLocation(char x, int y) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		Coordinates c;
		
		if(this.isVertical == true) {
			for(int i = 0; i < size -1; i++) {
				c = new Coordinates(x, y-i);
				if(isSubmerged) {
					c.setSubmerged();
				}
				location.add(c);
			}
			
			location.add(new Coordinates((char) (x+1), y-2));
			
		} else {
			for(int i = 0; i < size - 1; i++) {
				location.add(new Coordinates((char) (x+i), y));
			}
			
			location.add(new Coordinates((char) (x+2), y+1));
		}
		return location;
	}
	
	protected boolean checkInput(char x, int y, boolean isVertical) {
		boolean valid = true;
		
		if (y > 10 || y < 1) {
			valid = false;
		}
	
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if((y - size + 1 < 0 || x == 'J') && isVertical == true) {
			valid = false;
		}
		
		if((x + size - 1 > 'J' || y == 10) && isVertical == false) {
			valid = false;
		}
		
		return valid;
	}

	public boolean isSubmerged() {
		return isSubmerged;
	}
}
