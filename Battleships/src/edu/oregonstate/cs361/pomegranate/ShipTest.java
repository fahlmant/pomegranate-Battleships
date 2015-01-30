package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

	//Submarine
	@Test
	public void testShipSubmarine() {
		Ship ship = new Ship("Submarine");
		String k = ship.getKind();
		assertEquals("Submarine", k);
		ship.setHealth(3);
		assertEquals(3, ship.getHealth());
	}
	
	//Battleship
	@Test
	public void testShipBattleship() {
		Ship ship = new Ship("Battleship");
		String k = ship.getKind();
		assertEquals("Battleship", k);
		ship.setHealth(4);
		assertEquals(4, ship.getHealth());
	}
	
	//Destroyer 
	//I don't think we have a destroyer ship type
	@Test
	public void testShipDestroyer() {
		Ship ship = new Ship("Destroyer");
		String k = ship.getKind();
		assertEquals("Destroyer", k);
		ship.setHealth(3);
		assertEquals(3, ship.getHealth());
	}
	
	//Minesweeper
	@Test
	public void testShipMinesweeper() {
		Ship ship = new Ship("Minesweeper");
		String k = ship.getKind();
		assertEquals("Minesweeper", k);
		ship.setHealth(2);
		assertEquals(2, ship.getHealth());
	}	
}

