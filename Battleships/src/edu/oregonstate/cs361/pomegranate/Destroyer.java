package edu.oregonstate.cs361.pomegranate;

public class Destroyer extends Ship{

	public Destroyer(String kind) {
		super(kind);
		size = 3;
		health = size;
		armor = true;
	}
}

