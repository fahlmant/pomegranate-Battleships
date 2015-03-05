package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

public class RegularAttack {
	
	protected Ship s;
	protected List<Ship> hitShips;
	protected Coordinates location;
	protected Board b;
	protected List<Ship> ships;
	protected int totalShips;
	
	public RegularAttack(char x, int y, List<Ship> ships, int totalShips) {
		hitShips = new ArrayList<Ship>();
		location = new Coordinates(x, y);
		this.ships = ships;
		this.totalShips = totalShips;		
	}
	
	public List<Ship> hitShips() {
		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).isEqual(location)) {
					if(!ships.get(i).getLocation().get(j).isSubmerged()) {
						s = checkCQ(ships.get(i), j);
						ships.set(i, s);
						hitShips.add(s);
					}
				}
			}
		}
		return hitShips;
	}
	
	protected Ship checkCQ(Ship s, int j) {
		if(s.getCq().isEqual(location)) {
			if(s.isArmor()) {
				return s.destroyArmor();
			} else {
				return s.cqDestroyed();
			}
		} else {
			return s.takeDamage(j);
		}
	}
}
