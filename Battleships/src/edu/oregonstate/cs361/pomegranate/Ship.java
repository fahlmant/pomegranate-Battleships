package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

public abstract class Ship {
	
	private String kind;
	protected int size;
	protected int health;
	protected List<Coordinates> location;
	protected boolean valid;
	protected boolean isVertical;
	protected boolean armor;
	protected Coordinates cq;
	protected boolean moved; 

	public Ship(String kind) {
		this.kind = kind;
		moved = false;
	}
	
	/**Decrements the health of a ship*/
	public Ship takeDamage(int j) {
		health--;
		Coordinates c = location.get(j);
		c.hit();
		location.set(j, c);
		return this;
	}

	/**Returns the kind of ship*/
	public String getKind() {
		return kind;
	}

	/**Returns the health of ship*/
	public int getHealth() {
		return health;
	}

	/**Returns the list of coords the ship is at*/
	public List<Coordinates> getLocation() {
		return location;
	}
	
	protected boolean checkInput(char x, int y, boolean isVertical) {
		boolean valid = true;
		
		if (y > 10 || y < 1) {
			valid = false;
		}
		
		if (x > 'J' || x < 'A') {
			valid = false;
		}
		
		if(y + size > 11 && isVertical == true) {
			valid = false;
		}
		
		if(x + size > 'K' && isVertical == false) {
			valid = false;
		}
		
		return valid;
	}
	
	/**Sets the location of the ships*/
	public void setLocation(char x, int y, boolean isVertical) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		this.isVertical = isVertical;
		valid = checkInput(x, y, isVertical);
		
		initCQ(x, y);
		
		if(this.isVertical == true) {
			for(int i = 0; i < size; i++) {
				location.add(new Coordinates(x, y+i));
			}
		} else {
			for(int i = 0; i < size; i++) {
				location.add(new Coordinates((char) (x+i), y));
			}
		}
		this.location = location;
	}

	/**Returns the size of the ship*/
	public int getSize() {
		return size;
	}
	
	/**Returns if ths ship placement is valid*/
	public boolean isValid() {
		return valid;
	}

	/**Returns if the ship is vertical*/
	public boolean isVertical() {
		return isVertical;
	}

	/**Checks that the captain's quarters was destroyed*/
	public Ship cqDestroyed() {
		health = 0;
		for(int i = 0; i < size; i++) {
			Coordinates c = location.get(i);
			c.hit();
			location.set(i, c);
		}
		return this;
	}
	
	/**Destroys a ships armor*/
	public Ship destroyArmor() {
		armor = false;
		return this;
	}
	
	/**Returns armor status*/
	public boolean isArmor() {
		return armor;
	}
	
	/**Returns coords of CQ*/
	public Coordinates getCq() {
		return cq;
	}
	
	/**Sets coords of CQ*/
	public void setCQ(char x, int y) {
		cq = new Coordinates(x, y);
	}
	
	protected void initCQ(char x, int y) {
		if (isVertical) {
			cq = new Coordinates(x, y+1);
		}
		else {
			cq = new Coordinates((char) (x+1), y);
		}
	}
	
	/**Check is two ships overlap. It's ok if one is submerged*/
	public boolean checkOverlap(List<Ship> ships, int totalShips) {

		for(int i = 0; i < totalShips; i++) {
			for(int j = 0; j < ships.get(i).getSize(); j++) {
				if(j == this.size) {
					break;
				}
				if(ships.get(i).getLocation().get(j).getX() == location.get(j).getX()
						   && ships.get(i).getLocation().get(j).getY() == location.get(j).getY()) {
					if(checkForSubmerged(ships, i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	protected boolean checkForSubmerged(List<Ship> ships, int i, int j) {
		if((ships.get(i).getLocation().get(j).isSubmerged() && (!location.get(j).isSubmerged()))
				|| ((!ships.get(i).getLocation().get(j).isSubmerged()) && location.get(j).isSubmerged())) {
			return false;
		}
		return true;
	}
	
	/**Checks that a given move is valid*/
	public boolean checkMove(String direction) {
		boolean valid = false;
		switch(direction) {
		case "North":
			if(this.getLocation().get(0).getY() >= 2) {
				valid = true;
			}
			break;
		case "South":
			if(this.getLocation().get(this.size - 1).getY() <= 9) {
				valid = true;
			}
			break;
		case "West":
			if(this.getLocation().get(0).getX() >= 'B') {
				valid = true;
			}
			break;
		case "East":
			if(this.getLocation().get(this.size - 1).getX() <= 'I') {
				valid = true;
			}
			break;
		default: break;
		
		}
		
		return valid;
	}
	
	/**Returns if the ship moved on the last move*/
	public boolean hasMoved()
	{
		return moved;
	}
	
	/**Sets the move status to true*/
	public void movedTrue()
	{
		moved = true;
	}
	/**Sets the move status to false*/
	public void movedFalse() {
		moved = false;
	}
	
	/**Returns the tail coord*/
	public int getTail() {
		return size;
	}
}
