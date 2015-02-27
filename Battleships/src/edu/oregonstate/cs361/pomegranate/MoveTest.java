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
	
	@Test
	public void testMoveSubNorthVertical() {
		Ship s = new Submarine("Submarine", 'A', 4, true, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);	
	}
	
	@Test
	public void testMoveSubSouthVertical() {
		Ship s = new Submarine("Submarine", 'A', 5, true, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);		
	}
	
	@Test
	public void testMoveSubEastVertical() {
		Ship s = new Submarine("Submarine", 'A', 5, true, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);	
	}
	
	@Test
	public void testMoveSubWestVertical() {
		Ship s = new Submarine("Submarine", 'B', 5, true, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);	
	}

	@Test
	public void testMoveSubEastHoriz() {
		Ship s2 = new Submarine("Submarine", 'A', 5, false, false);
		Board b = new Board();
		b.placeShip(s2);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);
	}
	
	@Test
	public void testMoveSubWestHoriz() {
		Ship s2 = new Submarine("Submarine", 'B', 5, false, false);
		Board b = new Board();
		b.placeShip(s2);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);
	}
	
	@Test
	public void testMoveSubNorthHoriz() {
		Ship s = new Submarine("Submarine", 'A', 4, false, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);	
	}
	@Test
	public void testMoveSubSouthHoriz() {
		Ship s = new Submarine("Submarine", 'A', 4, false, false);
		Board b = new Board();
		b.placeShip(s);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);	
	}
	
	@Test
	public void undoOnce(){
		Ship s = new Minesweeper("Minesweeper", 'B', 4, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		b.undoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		
	}
	@Test
	public void undoTwice(){
		Ship s = new Minesweeper("Minesweeper", 'B', 4, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		b.moveNorth();
		b.undoMove();
		b.undoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);	
	}
	
	public void redo(){
		Ship s = new Minesweeper("Minesweeper", 'B', 4, true);
		Board b = new Board();
		b.placeShip(s);
		b.moveNorth();
		b.undoMove();
		b.redoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);

	}

}
