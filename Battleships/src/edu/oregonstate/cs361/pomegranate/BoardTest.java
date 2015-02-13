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
		Ship s = new Ship("Submarine", 3, 'A', 10, true);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 10, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testYMax() {
		Ship s = new Ship("Submarine", 3, 'A', 11, true);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 11, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testYMin() {
		Ship s = new Ship("Submarine", 3, 'A', 0, true);
		Board b = new Board();
		boolean valid;
		valid = b.placeShip(s, 'A', 0, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMax() {
		Ship s = new Ship("Submarine", 3, 'K', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'K', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMin() {
		Ship s = new Ship("Submarine", 3, 'z', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'z', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Ship("Submarine", 3, 'A', 2, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 2, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidVertical() {
		Ship s = new Ship("Submarine", 3, 'A', 3, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 3, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Ship("Submarine", 3, 'H', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'H', 1, false);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidHorizontal() {
		Ship s = new Ship("Submarine", 3, 'A', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 1, false);
		assertEquals(true, valid);
	}
	
	@Test
	public void testPlaceShipVertical() {
		Ship s = new Ship("Battleship", 4, 'A', 4, true);
		Board b = new Board();
		b.placeShip(s, 'A', 4, true);
		int[][] g = b.getGrid();
		assertEquals(g[0][3], 0);
		assertEquals(g[0][2], 0);
		assertEquals(g[0][1], 0);
		assertEquals(g[0][0], 0);
	}

	@Test
	public void testPlaceShipHorizontal() {
		Ship s = new Ship("Minesweeper", 2, 'A', 3, false);
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		int[][] g = b.getGrid();
		assertEquals(g[0][2], 0);
		assertEquals(g[1][2], 0);
	}
	
	@Test
	public void testAttackInvalid(){
		Ship s = new Ship("Submarine", 3, 'B', 10, true);
		Board b = new Board();
		b.placeShip(s, 'B', 10, true);
		Result r = new Result(s, 'B', 3, 3);
		r = b.attack('Z', 10);
		assertEquals(r.getResult(), Status.INVALID);
		r = b.attack('B', 11);
		assertEquals(r.getResult(), Status.INVALID);

	}
	
	@Test
	public void testAttackValid(){
		Ship s = new Ship("Submarine", 3, 'B', 10, true);
		Board b = new Board();
		Result r = null;
		assertEquals(s.isValid(), true);
		b.placeShip(s, 'B', 10, true);
		r = b.attack('B', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('C', 5);
		assertEquals(r.getResult(), Status.MISS);

	}
	
	@Test
	public void testDestroyed() {
		Ship s = new Ship("Submarine", 3, 'B', 10, true);
		Ship s2 = new Ship("Minesweeper", 2, 'A', 1, false);
		Board b = new Board();
		Result r = null;
		b.placeShip(s, 'B', 10, true);
		b.placeShip(s2, 'A', 1, false);
		r = b.attack('B', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('B', 9);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('B', 8);
		assertEquals(r.getResult(), Status.SUNK);
	}
	
	@Test
	public void testSurrender() {
		Ship s = new Ship("Submarine", 3, 'A', 10, true);
		Ship s2 = new Ship("Minesweeper", 2, 'A', 1, false);
		Ship s3 = new Ship("Battleship", 4, 'J', 10, true);
		Board b = new Board();
		Result r = null;
		b.placeShip(s, 'A', 10, true);
		b.placeShip(s2, 'A', 1, false);
		b.placeShip(s3, 'J', 10, true);
		r = b.attack('A', 10);
		r = b.attack('A', 9);
		r = b.attack('A', 8);
		r = b.attack('A', 1);
		r = b.attack('B', 1);
		r = b.attack('J', 10);
		r = b.attack('J', 9);
		r = b.attack('J', 8);
		r = b.attack('J', 7);
		assertEquals(r.getResult(), Status.SURRENDER);
	}
	
}
