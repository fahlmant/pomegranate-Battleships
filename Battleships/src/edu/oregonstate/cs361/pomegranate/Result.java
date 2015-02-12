package edu.oregonstate.cs361.pomegranate;

public class Result {
	

	private Status status;
	private Ship ship;
	
	Result(Ship ship, char x, int y, int shipsLeft){
		this.ship = ship;
		this.status = checkStatus(x, y, shipsLeft);
	}

	public Status getResult() {
		return status;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	private Status checkStatus(char x, int y, int shipsLeft) {
		if(x < 'A' || x > 'J') {
			return Status.INVALID;
		} else if( y < 1  || y > 10) {
			return Status.INVALID;		
		} else if(ship != null) {
			if(shipsLeft == 0) {
				return Status.SURRENDER;
			} else if(ship.getHealth() == 0) {
				return Status.SUNK;
			} else {
				return Status.HIT;
			}
		}
		return Status.MISS;
	}
}
