package edu.oregonstate.cs361.pomegranate;

public class Ship {
	
	private int size;
	private String kind;

	public Ship(String kind) {
		this.setKind(kind);
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}
