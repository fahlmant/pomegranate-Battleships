package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Destroyer extends Ship{

	private Coordinates cq;
	private boolean armor;

	public Destroyer(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		if (isVertical){
			setCq(new Coordinates(x, y-1));
		}
		else {
			setCq(new Coordinates((char) (x+1), y));
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

