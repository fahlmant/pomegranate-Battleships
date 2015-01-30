package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testBoard() {
		Board b = new Board();
		int[][] s = new int [10][10];
		assertArrayEquals(s, b.getGrid());
	}
	
	@Test
	public void testValidPlacement() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 10, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testYMax() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 11, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testYMin() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 0, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMax() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'K', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMin() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'z', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 2, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidVertical() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 3, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'H', 1, false);
		assertEquals(false, valid);
	}
	
	@Test
	public void testPlaceShip() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		b.placeShip(s, 'B', 10, true);
		int[][] g = b.getGrid();
		assertEquals(g[1][9], 3);
		assertEquals(g[1][8], 3);
		assertEquals(g[1][7], 3);
	}
	
	@Test
	public void testAttackInvalid(){
		Ship s = new Ship("Submarine");
		Board b = new Board();
		Result r = new Result();
		b.placeShip(s, 'B', 10, true);
		r = b.attack('Z', 10);
		assertEquals(r.status, Status.INVALID);
		r = b.attack('B', 11);
		assertEquals(r.status, Status.INVALID);
	}
	
	@Test
	public void testAttackValid(){
		Ship s = new Ship("Submarine");
		Board b = new Board();
		Result r = new Result();
		b.placeShip(s, 'B', 10, true);
		r = b.attack('B', 10);
		assertEquals(r.status, Status.HIT);
		r = b.attack('D', 9);
		assertEquals(r.status, Status.MISS);
		r = b.attack('B', 9);
		assertEquals(r.status, Status.HIT);
	}
	
}
