package edu.oregonstate.cs361.pomegranate;


//todo create stuff
public class Ship {
	
	private String kind;
	private int health;

	public Ship(String kind) {
		this.setKind(kind);
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
