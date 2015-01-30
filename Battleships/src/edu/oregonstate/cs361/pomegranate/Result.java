package edu.oregonstate.cs361.pomegranate;

public class Result {
	
	Status status;
	Ship ship;
	
	Result(Ship ship, Status status){
		this.status = status;	
	}

	public Status getResult() {
		return status;
	}
	
	public Ship getShip() {
		return null;
	}
}
