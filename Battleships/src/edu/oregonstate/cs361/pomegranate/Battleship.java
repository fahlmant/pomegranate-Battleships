package edu.oregonstate.cs361.pomegranate;

public class Battleship extends Ship{

	public Battleship(String kind) {
		super(kind);
		size = 4;
		health = size;
		armor = true;
	}
}
