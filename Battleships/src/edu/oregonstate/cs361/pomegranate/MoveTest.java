package edu.oregonstate.cs361.pomegranate;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

	@Test
	public void testMoveNorthVertical() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, true);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 2);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveNorthHorizontal() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 2);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 2);
		
	}
	
	@Test
	public void testMoveEastVertical() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, true);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);
		
	}
	
	@Test
	public void testMoveEastHorizontal() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveWestVertical() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 3, true);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);
		
	}
	
	@Test
	public void testMoveWestHorizontal() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 3, false);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		
	}
	
	@Test
	public void testMoveSouthVertical() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, true);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);
		
	}
	
	@Test
	public void testMoveSouthHorizontal() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);
		
	}
	
	@Test
	public void testMoveFailure() {
		Ship s = new Minesweeper("Minespweepr");
		Board b = new Board();
		b.placeShip(s, 'A', 1, true);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 1);
	}
	
	@Test
	public void testMoveSubNorthVertical() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 4, true);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 4);	
	}
	
	@Test
	public void testMoveSubSouthVertical() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 5, true);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 6);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 7);		
	}
	
	@Test
	public void testMoveSubEastVertical() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 5, true);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 6);	
	}
	
	@Test
	public void testMoveSubWestVertical() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'B', 5, true);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 6);	
	}

	@Test
	public void testMoveSubEastHoriz() {
		Ship s2 = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s2, 'A', 5, false);
		b.moveEast();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);
	}
	
	@Test
	public void testMoveSubWestHoriz() {
		Ship s2 = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s2, 'B', 5, false);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);
	}
	
	@Test
	public void testMoveSubNorthHoriz() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 4, false);
		b.moveNorth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);	
	}
	@Test
	public void testMoveSubSouthHoriz() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 4, false);
		b.moveSouth();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 5);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 5);	
	}
	
	@Test
	public void undoOnce(){
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.moveNorth();
		b.undoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		
	}
	@Test
	public void undoTwice(){
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.moveNorth();
		b.moveNorth();
		b.undoMove();
		b.undoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);	
	}
	
	@Test
	public void redo(){
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.moveNorth();
		b.undoMove();
		b.redoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);

	}
	
	@Test
	public void redoTwice() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.moveNorth();
		b.moveNorth();
		b.undoMove();
		b.undoMove();
		b.redoMove();
		b.redoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 2);
	}
	
	@Test
	public void redoTwiceThenUndoOnce() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.moveNorth();
		b.moveNorth();
		b.undoMove();
		b.undoMove();
		b.redoMove();
		b.redoMove();
		b.undoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
	}
	
	@Test
	public void undoRedoBeforeMove() {
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'B', 4, true);
		b.undoMove();
		b.redoMove();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		b.moveWest();
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
	}

}
