package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Battleship extends Ship{

	private Coordinates cq;
	private boolean armor;

	public Battleship(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		if (isVertical){
			setCq(new Coordinates(x, y-2));
		}
		else {
			setCq(new Coordinates((char) (x+2), y));
		}
		
		this.armor = true;
	}

	public Coordinates getCq() {
		return this.cq;
	}

	public void setCq(Coordinates cq) {
		this.cq = cq;
	}

	public void destroyArmor() {
		armor = false;
	}
	
	public boolean isArmor() {
		return armor;
	}
}
