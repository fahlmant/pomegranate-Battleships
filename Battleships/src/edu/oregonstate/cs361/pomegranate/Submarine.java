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
		this.isSubmerged = isSubmerged;
		valid = checkInput(x, y, isVertical);
		location = this.setLocation(x, y);
		if (isVertical) {
			cq = new Coordinates(x, y-3);
		}
		else {
			cq = new Coordinates((char) (x+3), y);
		}
		armor = true;
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
			c = new Coordinates((char) (x+1), y-2);
			if(isSubmerged) {
				c.setSubmerged();
			}
			location.add(c);
			
		} else {
			for(int i = 0; i < size - 1; i++) {
				c = new Coordinates((char) (x + i), y);
				if(isSubmerged) {
					c.setSubmerged();
				}
				location.add(c);
			}
			c = new Coordinates((char) (x+2), y+1);
			if(isSubmerged) {
				c.setSubmerged();
			}
			location.add(c);
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
	
	public boolean checkMove(String direction) {
		boolean valid = false;
		switch(direction) {
		case "North":
			if(this.getLocation().get(0).getY() <= 9 && this.isVertical) {
				valid = true;
			}
			else if(!this.isVertical && this.getLocation().get(this.size - 1).getY() <= 9){
				valid = true;
			}
			break;
		case "South":
			if(this.getLocation().get(this.size - 2).getY() >= 2) {
				valid = true;
			}
			break;
		case "West":
			if(this.getLocation().get(0).getX() >= 'B') {
				valid = true;
			}
			break;
		case "East":
			if(this.isVertical && this.getLocation().get(this.size - 1).getX() <= 'I') {
				valid = true;
			}
			else if(!this.isVertical && this.getLocation().get(this.size - 2).getX() <= 'I'){
				valid = true;
			}
			break;
		default: break;
		
		}
		
		return valid;
	}
}
