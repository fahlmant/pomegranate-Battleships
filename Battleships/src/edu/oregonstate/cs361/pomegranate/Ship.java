package edu.oregonstate.cs361.pomegranate;

public class Ship {
	
	private int size;
	private String kind;

	public Ship(String kind,int size) {
		this.setKind(kind);
		this.setSize(size);
	}
	
	public int getSize() {
		return size;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
