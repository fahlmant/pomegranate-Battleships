package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

public abstract class Ship {
	
	private String kind;
	private int size;
	private int health;
	private List<Coordinates> location;
	private boolean valid;
	private boolean isVertical;
	protected boolean armor;
	protected Coordinates cq;

	public Ship(String kind, int size, char x, int y, boolean isVertical) {
		this.kind = kind;
		this.health = size;
		this.size = size;
		this.valid = checkInput(x, y, isVertical);
		this.isVertical = isVertical;
		this.location = setLocation(x, y);
	}
	
	public void takeDamage() {
		health--;
	}

	public String getKind() {
		return kind;
	}

	public int getHealth() {
		return health;
	}

	public List<Coordinates> getLocation() {
		return location;
	}
	
	private boolean checkInput(char x, int y, boolean isVertical) {
		boolean valid = true;
		
		if (y > 10 || y < 1) {
			valid = false;
		}
		
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if(y - size < 0 && isVertical == true) {
			valid = false;
		}
		
		if(x + size > 'J' && isVertical == false) {
			valid = false;
		}
		
		return valid;
	}
	
	private List<Coordinates> setLocation(char x, int y) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		
		if(this.isVertical == true) {
			for(int i = 0; i < size; i++) {
				location.add(new Coordinates(x, y-i));
			}
		} else {
			for(int i = 0; i < size; i++) {
				location.add(new Coordinates((char) (x+i), y));
			}
		}
		return location;
	}

	public int getSize() {
		return size;
	}

	public boolean isValid() {
		return valid;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void cqDestroyed() {
		health = 0;
	}
	
	public void destroyArmor() {
		armor = false;
	}
	
	public boolean isArmor() {
		return armor;
	}
	
	public Coordinates getCq() {
		return cq;
	}
}
