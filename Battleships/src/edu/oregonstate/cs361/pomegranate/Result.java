package edu.oregonstate.cs361.pomegranate;

public class Result {
	

	private Status status;
	private Ship ship;
	
	Result(Ship ship, Status status){
		this.status = status;	
		this.ship = ship;
	}

	public Status getResult() {
		return status;
	}
	
	public Ship getShip() {
		return ship;
	}
}
