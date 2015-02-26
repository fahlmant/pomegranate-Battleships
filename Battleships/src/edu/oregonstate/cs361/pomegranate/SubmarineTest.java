package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubmarineTest {

	@Test
	public void testPlaceShipVertical() {
		Ship s = new Submarine("Sumbarine",'A', 10, true);
		Board b = new Board();
		b.placeShip(s);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 10);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(2).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(2).getY(), 8);
		assertEquals(b.getShip(0).getLocation().get(3).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(3).getY(), 7);
		assertEquals(b.getShip(0).getLocation().get(4).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(4).getY(), 9);
	}
}
