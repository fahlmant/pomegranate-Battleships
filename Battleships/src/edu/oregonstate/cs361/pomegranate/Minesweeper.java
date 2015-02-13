package edu.oregonstate.cs361.pomegranate;

import edu.oregonstate.cs361.api.Coordinates;

public class Minesweeper extends Ship {

	private Coordinates cq;
	private boolean armor;
	
	public Minesweeper(String kind, int size, char x, int y, boolean isVertical) {
		super(kind, size, x, y, isVertical);
		setCq(new Coordinates(x, y));
		this.armor = false;
	}

	public Coordinates getCq() {
		return cq;
	}

	public void setCq(Coordinates cq) {
		this.cq = cq;
	}
	
	public boolean isArmor() {
		return this.armor;
	}
}
