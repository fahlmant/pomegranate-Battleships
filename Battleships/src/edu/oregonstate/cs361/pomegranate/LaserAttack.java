package edu.oregonstate.cs361.pomegranate;

import java.util.List;

/**Space Laser attack, both submerged and normal ships*/
public class LaserAttack extends RegularAttack{
	
	/** Creates a laser attack object*/
	public LaserAttack(char x, int y, List<Ship> ships, int totalShips) {
		super(x, y, ships, totalShips);
	}
	
	/** Hits all ships, regardless of submersion status*/
	public List<Ship> hitShips() {
		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(ships.get(i).getLocation().get(j).isEqual(location)) {
					s = checkCQ(ships.get(i), j);
					ships.set(i, s);
					hitShips.add(s);
				}
			}
		}
		return hitShips;
	}
}
