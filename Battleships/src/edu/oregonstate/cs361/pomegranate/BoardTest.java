package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testValidPlacement()
	{
		Ship s = new Ship("Submarine",3);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 1, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testYMax()
	{
		Ship s = new Ship("Submarine",3);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 11, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testYMin()
	{
		Ship s = new Ship("Submarine",3);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 0, true);
		assertEquals(false, valid);
	}
	

}
