package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubmarineTest {

	@Test
	public void testPlaceShipVertical() {
		Ship s = new Submarine("Sumbarine",'A', 10, true, false);
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
		assertEquals(b.getShip(0).getLocation().get(4).getY(), 8);
	}
	
	@Test
	public void testPlaceShipHorizontal() {
		Ship s = new Submarine("Sumbarine",'A', 9, false, false);
		Board b = new Board();
		b.placeShip(s);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(2).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(2).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(3).getX(), 'D');
		assertEquals(b.getShip(0).getLocation().get(3).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(4).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(4).getY(), 10);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Submarine("Submarine", 'J', 10, true, false);
		Board b = new Board();
		assertEquals(false, b.placeShip(s));
	}
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Submarine("Submarine", 'A', 10, false, false);
		Board b = new Board();
		assertEquals(false, b.placeShip(s));
	}
	
}
