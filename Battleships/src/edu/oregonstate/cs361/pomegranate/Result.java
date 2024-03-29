package edu.oregonstate.cs361.pomegranate;

/**Holds the various results of an attack*/
public class Result {
	

	private Status status;
	private Ship ship;
	private boolean isHit;
	
	/**Creates a result object*/
	Result(Ship ship, char x, int y, int shipsLeft){
		this.ship = ship;
		this.status = checkStatus(x, y, shipsLeft);
		if(ship != null) {
			isHit = isHit();
		}
	}

	/** Returns the status of an attack*/
	public Status getResult() {
		return status;
	}
	
	/** Returns the ship that got attacked*/
	public Ship getShip() {
		return ship;
	}
	
	private Status checkStatus(char x, int y, int shipsLeft) {
		if(isInvalid(x, y)) {
			return Status.INVALID;
		} else if(isHit) {
			return Status.INVALID;
		} else if(shipsLeft == 0) {
			return Status.SURRENDER;
		} else if(ship == null){
			return Status.MISS;
		} else if(ship.getHealth() == 0) {
			return Status.SUNK;
		} else if(isCQ(x, y)) {
			return Status.MISS;
		} else {
			return Status.HIT;
		}
	}
	
	private boolean isInvalid(char x, int y) {
		if(x < 'A' || x > 'J') {
			return true;
		} else if( y < 1  || y > 10) {
			return true;
		}
		return false;
	}
	
	private boolean isCQ(char x, int y) {
		if(ship.getCq().getX() == x
				&& ship.getCq().getY() == y) {
			return true;
		}
		return false;
	}

	private boolean isHit() {
		for(int i = 0; i < ship.getSize(); i++) {
			if(ship.getLocation().get(i).isHit()) {
				return true;
			}
		}
		return false;
	}
}