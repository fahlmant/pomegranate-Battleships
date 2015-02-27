package edu.oregonstate.cs361.pomegranate;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

	@Test
	public void testMoveNorthVertical() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveNorthHorizontal() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);
		
	}
	
	@Test
	public void testMoveEastVertical() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 2);
		
	}
	
	@Test
	public void testMoveEastHorizontal() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveWestVertical() {
		Ship s = new Minesweeper("Minesweeper", 'B', 3, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 2);
		
	}
	
	@Test
	public void testMoveWestHorizontal() {
		Ship s = new Minesweeper("Minesweeper", 'B', 3, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveSouthVertical() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 2);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 1);
		
	}
	
	@Test
	public void testMoveSouthHorizontal() {
		Ship s = new Minesweeper("Minesweeper", 'A', 3, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 2);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 2);
		
	}
	
	@Test
	public void testMoveFailure() {
		Ship s = new Minesweeper("Minespweepr", 'A', 10, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 10);
	}

}
