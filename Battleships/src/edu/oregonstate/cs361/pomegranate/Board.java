package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

public class Board {

	private Grid[][] grid;
	private List<Ship> ships;
	private int totalShips;
	private int shipsLeft;

	public Board() {
		grid = new Grid [10][10];
		ships = new ArrayList<Ship>();
		shipsLeft = 0;
		totalShips = 0;
	}
	
	public Ship getShip(int i) {
		return ships.get(i);
	}
	
	public Grid[][] getGrid() {
		return grid;
	}
	
	public int getShipsLeft() {
		return shipsLeft;
	}
	
	public boolean placeShip(Ship ship) {
		
		//TODO check to make sure ships aren't placed on top of each other
		
		if(ship.isValid() == true) {
			ships.add(ship);
			shipsLeft++;
			totalShips++;
			return true;
		}
		return false;
	}
	
	private int checkLocation(char x, int y) {
		Ship s;
		Coordinates location = new Coordinates(x, y);
		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).getX() == location.getX()
				   && ships.get(i).getLocation().get(j).getY() == location.getY()) {
					s = checkCQ(ships.get(i), location, j);
					ships.set(i, s);
					return i;
				}
			}
		}

		return -1;
	}
	
	private Ship checkCQ(Ship s, Coordinates c, int j) {
		if(s.getCq().getX() == c.getX() && s.getCq().getY() == c.getY()) {
			if(s.isArmor()) {
				s.destroyArmor();
				return s;
			} else {
				s.cqDestroyed();
				for(int i = 0; i < s.getSize(); i++) {
					c = s.getLocation().get(i);
					c.hit();
					s.getLocation().set(i, c);
				}
				return s;
			}
		}
		s.takeDamage();
		c = s.getLocation().get(j);
		c.hit();
		s.getLocation().set(j, c);
		return s;
	}
	
	public Result attack(char x, int y) {
		
		//TODO implement a way to keep track of misses
		//     implement a way to keep track of hits
		
		int i = checkLocation(x, y);
		Ship s = null;
		boolean isHit = false; 
		
		if(i != -1) {
			s = ships.get(i);
			isHit = isHit(x, y, i); 
		}
		
		Result r = new Result(s, x, y, shipsLeft);
		r.setHit(isHit);
	
		if(r.getResult() == Status.SUNK) {
			shipsLeft--;
			r = new Result(s, x, y, shipsLeft);
		}
		return r;
	}
	
	private boolean isHit(char x, int y, int i) {
		for(int j = 0; j < ships.get(i).getSize(); j++) {
			if(ships.get(i).getLocation().get(j).isHit()) {
				return true;
			}
		}
		return false;
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
		int xCord = x - 'A' -1;
		int yCord = y - 1;
		
		for(; xCord < x + 2; xCord ++)
		{
			for(;yCord < y + 2; yCord ++)
			{
				
				if(grid[xCord - 'A'][yCord] == Grid.SHIP || grid[xCord - 'A'][yCord] == Grid.HIT)
				{
					Coordinates c = new Coordinates((char)(xCord + 'A'),yCord);
					list.add(c);				
				}
			}
			
			yCord = y -1; 
		}
		
		if(grid[(x - 'A')-2][y] == Grid.SHIP)
		{
			Coordinates c = new Coordinates((char)(x + 'A'),y);
			list.add(c);
		}
		
		if(grid[(x - 'A')+2][y] == Grid.SHIP)
		{
			Coordinates c = new Coordinates((char)(x + 'A'),y);
			list.add(c);
		}
		
		if(grid[x - 'A'][y-2] == Grid.SHIP)
		{
			Coordinates c = new Coordinates((char)(x + 'A'),(y-2));
			list.add(c);
		}
		
		if(grid[x - 'A'][y+2] == Grid.SHIP)
		{
			Coordinates c = new Coordinates((char)(x + 'A'),(y+2));
			list.add(c);
		}
		return list;
	}
	
}
