package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void testValidPlacement() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 10, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testYMax() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 11, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testYMin() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 0, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMax() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'K', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testXMin() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'z', 1, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 2, true);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidVertical() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 3, true);
		assertEquals(true, valid);
	}
	
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'H', 1, false);
		assertEquals(false, valid);
	}
	
	@Test
	public void testValidHorizontal() {
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		boolean valid = b.placeShip(s, 'A', 1, false);
		assertEquals(true, valid);
	}
	
	@Test
	public void testPlaceShipVertical() {
		Ship s = new Battleship("Battleship");
		Board b = new Board();
		b.placeShip(s, 'A', 4, true);
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
		Ship s = new Minesweeper("Minesweeper");
		Board b = new Board();
		b.placeShip(s, 'A', 3, false);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 3);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 3);
	}
	
	@Test
	public void testAttackInvalid(){
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		b.placeShip(s, 'B', 10, true);
		List<Result> r = new ArrayList<Result>();
		r = b.attack('Z', 10);
		assertEquals(r.get(0).getResult(), Status.INVALID);
		r = b.attack('B', 11);
		assertEquals(r.get(0).getResult(), Status.INVALID);

	}
	
	@Test
	public void testAttackValid(){
		Ship s = new Destroyer("Destroyer");
		Board b = new Board();
		b.placeShip(s, 'B', 10, true);
		List<Result> r = new ArrayList<Result>();
		r = b.attack('B', 10);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('C', 5);
		assertEquals(r.get(0).getResult(), Status.MISS);

	}
	
	@Test
	public void testDestroyed() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Minesweeper("Minesweeper");
		Board b = new Board();
		List<Result> r = new ArrayList<Result>();
		b.placeShip(s, 'B', 10, true);
		b.placeShip(s2, 'A', 1, false);
		r = b.attack('B', 10);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('B', 8);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('B', 9);
		assertEquals(r.get(0).getResult(), Status.MISS);
		r = b.attack('B', 9);
		assertEquals(r.get(0).getResult(), Status.SUNK);
	}
	
	@Test
	public void testSurrender() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Minesweeper("Minesweeper");
		Ship s3 = new Battleship("Battleship");
		Board b = new Board();
		List<Result> r = new ArrayList<Result>();
		b.placeShip(s, 'A', 10, true);
		b.placeShip(s2, 'A', 1, false);
		b.placeShip(s3, 'J', 10, true);
		r = b.attack('A', 10);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('A', 9);
		assertEquals(r.get(0).getResult(), Status.MISS);
		r = b.attack('A', 8);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('A', 9);
		assertEquals(r.get(0).getResult(), Status.SUNK);
		r = b.attack('B', 1);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('A', 1);
		assertEquals(r.get(0).getResult(), Status.SUNK);
		r = b.attack('J', 10);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('J', 9);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('J', 8);
		assertEquals(r.get(0).getResult(), Status.MISS);
		r = b.attack('J', 7);
		assertEquals(r.get(0).getResult(), Status.HIT);
		r = b.attack('J', 8);	
		assertEquals(b.getShipsLeft(), 0);
		assertEquals(r.get(0).getResult(), Status.SURRENDER);
		}
	@Test
	public void testHitCQ() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Minesweeper("Minesweeper");
		Board b = new Board();
		List<Result> r = new ArrayList<Result>();
		assertEquals(s.isArmor(), true);
		b.placeShip(s, 'A', 4, false);
		b.placeShip(s2, 'J', 10, true);
		r = b.attack('B', 4);
		assertEquals(r.get(0).getResult(), Status.MISS);
		assertEquals(r.get(0).getShip().isArmor(), false);
		r = b.attack('B', 4);
		assertEquals(r.get(0).getResult(), Status.SUNK);
	}
	
	@Test
	public void testOverlapfailure() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Submarine("Submarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 4, false);
		assertEquals(false, b.placeShip(s2, 'A', 4, false));
	}
	
	@Test
	public void testOverlapSuccess() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Submarine("Submarine", true);
		Board b = new Board();
		b.placeShip(s, 'A', 4, false);
		assertEquals(true, b.placeShip(s2, 'A', 4, false));
		assertEquals(true, s2.getLocation().get(0).isSubmerged());
	}
}
