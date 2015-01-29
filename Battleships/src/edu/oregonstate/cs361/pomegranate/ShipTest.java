package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

	@Test
	public void testShipSize() {
		Ship ship = new Ship("Submarine",3);
		int s = ship.getSize();
		assertEquals(3, s);
	}
	
	@Test
	public void testShipKind() {
		Ship ship = new Ship("Submarine",3);
		String k = ship.getKind();
		assertEquals("Submarine", k);
	}
}
