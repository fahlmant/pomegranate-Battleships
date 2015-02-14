package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testValidPlacement() {
		Ship s = new Destroyer("Submarine", 3, 'A', 10, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(true, valid);
	}
	
	@Test
	public void testYMax() {
		Ship s = new Destroyer("Submarine", 3, 'A', 11, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testYMin() {
		Ship s = new Destroyer("Submarine", 3, 'A', 0, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMax() {
		Ship s = new Destroyer("Submarine", 3, 'K', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMin() {
		Ship s = new Destroyer("Submarine", 3, 'z', 1, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Destroyer("Submarine", 3, 'A', 2, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidVertical() {
		Ship s = new Destroyer("Submarine", 3, 'A', 3, true);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(true, valid);
	}
	
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Destroyer("Submarine", 3, 'H', 1, false);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidHorizontal() {
		Ship s = new Destroyer("Submarine", 3, 'A', 1, false);
		Board b = new Board();
		boolean valid = b.placeShip(s);
		assertEquals(true, valid);
	}
	
	@Test
	public void testPlaceShipVertical() {
		Ship s = new Battleship("Battleship", 4, 'A', 4, true);
		Board b = new Board();
		b.placeShip(s);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 4);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(2).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(2).getY(), 2);
		assertEquals(b.getShip(0).getLocation().get(3).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(3).getY(), 1);
	}

	@Test
	public void testPlaceShipHorizontal() {
		Ship s = new Minesweeper("Minesweeper", 2, 'A', 3, false);
		Board b = new Board();
		b.placeShip(s);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
	}
	
	@Test
	public void testAttackInvalid(){
		Ship s = new Destroyer("Submarine", 3, 'B', 10, true);
		Board b = new Board();
		b.placeShip(s);
		Result r = new Result(s, 'B', 3, 3);
		r = b.attack('Z', 10);
		assertEquals(r.getResult(), Status.INVALID);
		r = b.attack('B', 11);
		assertEquals(r.getResult(), Status.INVALID);

	}
	
	@Test
	public void testAttackValid(){
		Ship s = new Destroyer("Submarine", 3, 'B', 10, true);
		Board b = new Board();
		assertEquals(s.isValid(), true);
		b.placeShip(s);
		Result r = b.attack('B', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('C', 5);
		assertEquals(r.getResult(), Status.MISS);

	}
	
	@Test
	public void testDestroyed() {
		Ship s = new Destroyer("Submarine", 3, 'B', 10, true);
		Ship s2 = new Minesweeper("Minesweeper", 2, 'A', 1, false);
		Board b = new Board();
		Result r = null;
		b.placeShip(s);
		b.placeShip(s2);
		r = b.attack('B', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('B', 8);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('B', 9);
		assertEquals(r.getResult(), Status.MISS);
		r = b.attack('B', 9);
		assertEquals(r.getResult(), Status.SUNK);
	}
	
	@Test
	public void testSurrender() {
		Ship s = new Destroyer("Submarine", 3, 'A', 10, true);
		Ship s2 = new Minesweeper("Minesweeper", 2, 'A', 1, false);
		Ship s3 = new Battleship("Battleship", 4, 'J', 10, true);
		assertEquals(s3.getLocation().get(0).getX(), 'J');
		assertEquals(s3.getLocation().get(0).getY(), 10);
		Board b = new Board();
		Result r;
		b.placeShip(s);
		b.placeShip(s2);
		b.placeShip(s3);
		r = b.attack('A', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('A', 9);
		assertEquals(r.getResult(), Status.MISS);
		r = b.attack('A', 8);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('A', 9);
		assertEquals(r.getResult(), Status.SUNK);
		r = b.attack('B', 1);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('A', 1);
		assertEquals(r.getResult(), Status.SUNK);
		r = b.attack('J', 10);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('J', 9);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('J', 8);
		assertEquals(r.getResult(), Status.MISS);
		r = b.attack('J', 7);
		assertEquals(r.getResult(), Status.HIT);
		r = b.attack('J', 8);	
		assertEquals(b.getShipsLeft(), 0);
		assertEquals(r.getResult(), Status.SURRENDER);
		}
	@Test
	public void testHitCQ() {
		Ship s = new Destroyer("Destroyer", 3, 'A', 4, false);
		Ship s2 = new Minesweeper("Minesweeper", 2, 'J', 10, true);
		Board b = new Board();
		Result r;
		assertEquals(s.isArmor(), true);
		b.placeShip(s);
		b.placeShip(s2);
		r = b.attack('B', 4);
		assertEquals(r.getResult(), Status.MISS);
		assertEquals(r.getShip().isArmor(), false);
		r = b.attack('B', 4);
		assertEquals(r.getResult(), Status.SUNK);
	}
	
}
