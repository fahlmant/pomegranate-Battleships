package edu.oregonstate.cs361.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.oregonstate.cs361.pomegranate.Board;
import edu.oregonstate.cs361.pomegranate.Result;
import edu.oregonstate.cs361.pomegranate.Ship;
import edu.oregonstate.cs361.pomegranate.Status;

public class BoardTest {

	private Board board;

	private Ship createMinesweeper() {
		return new Ship("Minesweeper");
	}
	
	private Ship createDestroyer() {
		return new Ship("Submarine");
	}
	
	private Ship createBattleship() {
		return new Ship("Battleship");
	}
	
	@Before
	public void before() {
		board = new Board();
	}
	
	@Test
	public void testShipPlacement() {
		assertTrue(board.placeShip(createMinesweeper(), 'A', 2, true));
	}
	
	@Test
	public void testPlaceShipOutsideGrid() {
		assertFalse(board.placeShip(createMinesweeper(), 'Z', 20, true));
	}
	
	@Test
	public void testPlaceTwoShipsOneOnTopOfAnother() {
		assertTrue(board.placeShip(createMinesweeper(), 'A', 2, true));
		assertFalse(board.placeShip(createMinesweeper(), 'A', 2, true));
	}
	
	@Test
	public void testAttackMinesweeper() {
		placeDummyShip();
		board.placeShip(createMinesweeper(), 'A', 9, true);
		Result result = board.attack('A', 9);
		assertEquals(Status.HIT, result.getResult());
		result = board.attack('A', 8);
		assertEquals(Status.SUNK, result.getResult());
	}
	
	@Test
	public void testAttackDestroyer() {
		placeDummyShip();
		board.placeShip(createDestroyer(), 'A', 9, true);
		assertEquals(Status.HIT, board.attack('A',9).getResult());
		assertEquals(Status.HIT, board.attack('A',8).getResult());
		assertEquals(Status.SUNK, board.attack('A',7).getResult());
	}
	
	@Test
	public void testAttackBattleship() {
		placeDummyShip();
		board.placeShip(createBattleship(), 'A', 9, true);
		assertEquals(Status.HIT, board.attack('A',9).getResult());
		assertEquals(Status.HIT, board.attack('A',8).getResult());
		assertEquals(Status.HIT, board.attack('A',7).getResult());
		assertEquals(Status.SUNK, board.attack('A',6).getResult());
	}

	@Test
	public void testSurrender() {
		board.placeShip(createMinesweeper(), 'A', 9, true);
		assertEquals(Status.HIT, board.attack('A', 9).getResult());
		assertEquals(Status.SURRENDER, board.attack('A', 8).getResult());
	}
	
	@Test
	public void testMiss() {
		assertEquals(Status.MISS, board.attack('A', 1).getResult());
	}
	
	
	@Test
	public void testHitSameSquareTwiceWithShip() {
		board.placeShip(createMinesweeper(), 'A', 2, true);
		board.attack('A',2);
		Result result = board.attack('A', 2);
		assertTrue(result.getResult().equals(Status.INVALID) || result.getResult().equals(Status.MISS));
	}
	
	@Test
	public void testHitSameEmptySquareTwice() {
		board.attack('A', 2);
		Result result = board.attack('A', 2);
		assertTrue(result.getResult().equals(Status.INVALID) || result.getResult().equals(Status.MISS));
	}
	
	@Test
	public void testAttackOutsideGrid() {
		Result result = board.attack('Z', 15);
		assertTrue(result.getResult().equals(Status.INVALID) || result.getResult().equals(Status.MISS));
	}

	private void placeDummyShip() {
		board.placeShip(createMinesweeper(), 'F', 2, true);
	}

}
