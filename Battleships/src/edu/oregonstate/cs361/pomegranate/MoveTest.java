package edu.oregonstate.cs361.pomegranate;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

	@Test
	public void testMoveNorth() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);
		
	}

}
