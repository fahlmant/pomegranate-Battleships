package edu.oregonstate.cs361.pomegranate;

import java.util.ArrayList;
import java.util.List;

import edu.oregonstate.cs361.api.Coordinates;

public class Submarine extends Ship{
	
	public Submarine(String kind, char x, int y, boolean isVertical) {
		super(kind, x, y, isVertical);
		size = 5;
		health = size;
		valid = checkInput(x, y, isVertical);
		location = setLocation(x, y);
		if (isVertical){
			cq = new Coordinates(x, y-3);
		}
		else {
			cq = new Coordinates((char) (x+3), y);
		}	
		armor = true;
	}
	
	protected List<Coordinates> setLocation(char x, int y) {
		List<Coordinates> location = new ArrayList<Coordinates>();
		
		if(this.isVertical == true) {
			for(int i = 0; i < size -1; i++) {
				location.add(new Coordinates(x, y-i));
			}
			
		} else {
			for(int i = 0; i < size; i++) {
				location.add(new Coordinates((char) (x+i), y));
			}
		}
		return location;
	}
}
