package edu.oregonstate.cs361.pomegranate;

import java.util.List;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

public class Board {

	private int[][] grid;
	private Ship[] ships;
	private int shipsLeft;

	public Board() {
		grid = new int [10][10];
		ships = new Ship [3];
		shipsLeft = 0;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public boolean placeShip(Ship ship, char x, int y, boolean isVertical) {
		
		//TODO check to make sure ships aren't placed on top of eachother
		
		if(ship.isValid() == true) {
			ships[shipsLeft] = ship;
			shipsLeft++;
		}
		return ship.isValid();
	}
	
	private int checkLocation(char x, int y) {
		Coordinates location = new Coordinates(x, y);
		for(int i = 0; i < shipsLeft; i++) {
			for(int j = 0; j < ships[i].getSize(); j++) {
				if(ships[i].getLocation().get(j) == location) {
					return i;
				}
			}
		}

		return -1;
	}
	
	public Result attack(char x, int y) {
		
		//TODO implement a way to keep track of misses
		//     implement a way to keep track of hits
		
		int i = checkLocation(x, y);
		Ship s;
		if(i == -1) {
			s = null;
		} else {
			s = ships[i];
		}
		
		Result r = new Result(s, x, y, this.shipsLeft);
		
		if(r.getResult() == Status.HIT) {
			s.takeDamage();
			ships[i] = s;
		} else if(r.getResult() == Status.SUNK) {
			shipsLeft--;
		}
		return r;
	}
	
	/**
	 * This method returns the result of a sonar pulse
	 * @param x the column of the attack cell
	 * @param y the row of the attack cell
	 * @return a list of coordinates revealed as containing enemy ships
	 * @throws WeaponUnavailableException if the weapon is not available for use
	 * @throws AmmoExhaustedException if the ammo is exhausted
	 */
	public List<Coordinates> sonarPulse(char x, int y) throws WeaponUnavailableException, AmmoExhaustedException {
		return null;
	}
}
