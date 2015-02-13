package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

public class Board {

	private Grid[][] grid;
	private List<Ship> ships;
	private int shipsLeft;

	public Board() {
		grid = new Grid [10][10];
		ships = new ArrayList<Ship>();
		shipsLeft = 0;
	}
	
	public Ship getShip(int i) {
		return ships.get(i);
	}
	
	public Grid[][] getGrid() {
		return grid;
	}
	
	public boolean placeShip(Ship ship) {
		
		//TODO check to make sure ships aren't placed on top of each other
		
		if(ship.isValid() == true) {
			ships.add(ship);
			shipsLeft++;
			return true;
		}
		return false;
	}
	
	private Ship checkLocation(char x, int y) {
		Ship s = null;
		Coordinates location = new Coordinates(x, y);
		for(int i = 0; i < shipsLeft; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).getX() == location.getX()
				   && ships.get(i).getLocation().get(j).getY() == location.getY()) {
					s = checkCQ(ships.get(i), location);
					ships.set(i, s);
					return s;
				}
			}
		}

		return s;
	}
	
	private Ship checkCQ(Ship s, Coordinates c) {
		if(s.getCq().getX() == c.getX() && s.getCq().getY() == c.getY()) {
			if(s.isArmor()) {
				s.destroyArmor();
				return s;
			} else {
				s.cqDestroyed();
				return s;
			}
		}
		s.takeDamage();
		return s;
	}
	
	public Result attack(char x, int y) {
		
		//TODO implement a way to keep track of misses
		//     implement a way to keep track of hits
		
		Ship s = checkLocation(x, y);
		
		Result r = new Result(s, x, y, this.shipsLeft);
		
		if(r.getResult() == Status.SUNK) {
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
		
		List<Coordinates> list = new ArrayList<Coordinates>();
		Coordinates c = new Coordinates(x,y);
		
		if(grid[x - 'A'][y] == Grid.SHIP || grid[x - 'A'][y] == Grid.HIT)
		{
			list.add(c);
			
		}
			
		return list;
	}
	
}
