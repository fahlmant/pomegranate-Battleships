package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	
	//TODO fix failing tests

	//Submarine
	@Test
	public void testShipSubmarine() {
		Ship ship = new Ship("Submarine", 3, 'A', 1, true);
		String k = ship.getKind();
		assertEquals("Submarine", k);
		assertEquals(3, ship.getHealth());
	}
	
	//Destroyer 
	//I don't think we have a destroyer ship type
	@Test
	public void testShipDestroyer() {
		Ship ship = new Ship("Destroyer",4 , 'A', 1, true);
		String k = ship.getKind();
		assertEquals("Destroyer", k);
		assertEquals(4, ship.getHealth());
	}
	
	//Minesweeper
	@Test
	public void testShipMinesweeper() {
		Ship ship = new Ship("Minesweeper", 2, 'A', 1, true);
		String k = ship.getKind();
		assertEquals("Minesweeper", k);
		assertEquals(2, ship.getHealth());
	}	
}

