package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	
	@Test
	public void testShipKind() {
		Ship ship = new Ship("Submarine");
		String k = ship.getKind();
		assertEquals("Submarine", k);
	}
}