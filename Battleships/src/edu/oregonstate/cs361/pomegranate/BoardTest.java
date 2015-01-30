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
	public void testValidHorizontal() {
		Ship s = new Ship("Submarine");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 1, false);
		assertEquals(true, valid);
	}
	
	@Test
	public void testPlaceShipVertical() {
		Ship s = new Ship("Battleship");
		Board b = new Board();
		b.placeShip(s, 'A', 4, true);
		int[][] g = b.getGrid();
		assertEquals(g[0][3], 4);
		assertEquals(g[0][2], 4);
		assertEquals(g[0][1], 4);
		assertEquals(g[0][0], 4);
	}

	@Test
	public void testPlaceShipHorizontal() {
		Ship s = new Ship("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		int[][] g = b.getGrid();
		assertEquals(g[0][2], 2);
		assertEquals(g[1][2], 2);
	}
}