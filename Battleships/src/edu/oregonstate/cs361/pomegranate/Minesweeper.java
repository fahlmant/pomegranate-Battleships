package edu.oregonstate.cs361.pomegranate;

public class Minesweeper extends Ship {
	
	public Minesweeper(String kind) {
		super(kind);
		size = 2;
		health = size;
		armor = false;
	}
}
