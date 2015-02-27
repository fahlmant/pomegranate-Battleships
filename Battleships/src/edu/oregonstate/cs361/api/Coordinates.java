package edu.oregonstate.cs361.api;

public class Coordinates {
	
	private char x;
	private int y;
	private boolean isHit;
	private boolean submerged;

	public Coordinates(char x,int y) {
		this.x = x;
		this.y = y;
		isHit = false;
		submerged = false;
	}

	public char getX() {
		return x;
	}
	
	public void setX(char x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void hit() {
		isHit = true;
	}
	
	public boolean isHit() {
		return isHit;
	}
	
	public boolean isEqual(Coordinates c) {
		if(c.getX() != this.x || c.getY() != this.y) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isSubmerged() {
		return submerged;
	}

	public void setSubmerged() {
		submerged = true;
	}
}
