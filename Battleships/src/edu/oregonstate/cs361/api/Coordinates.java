package edu.oregonstate.cs361.api;

public class Coordinates {
	
	private char x;
	private int y;
	private boolean isVertical;

	public Coordinates(char x,int y) {
		this.x = x;
		this.y = y;
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
}
