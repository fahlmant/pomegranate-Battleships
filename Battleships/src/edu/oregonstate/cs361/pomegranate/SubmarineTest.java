package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SubmarineTest {

	@Test
	public void testPlaceShipVertical() {
		Ship s = new Submarine("Sumbarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 10, true);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 10);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(2).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(2).getY(), 8);
		assertEquals(b.getShip(0).getLocation().get(3).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(3).getY(), 7);
		assertEquals(b.getShip(0).getLocation().get(4).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(4).getY(), 8);
	}
	
	@Test
	public void testPlaceShipHorizontal() {
		Ship s = new Submarine("Sumbarine", false);
		Board b = new Board();
		b.placeShip(s, 'A', 9, false);
		assertEquals(b.getShip(0).getLocation().get(0).getX(), 'A');
		assertEquals(b.getShip(0).getLocation().get(0).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(1).getX(), 'B');
		assertEquals(b.getShip(0).getLocation().get(1).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(2).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(2).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(3).getX(), 'D');
		assertEquals(b.getShip(0).getLocation().get(3).getY(), 9);
		assertEquals(b.getShip(0).getLocation().get(4).getX(), 'C');
		assertEquals(b.getShip(0).getLocation().get(4).getY(), 10);
	}
	
	@Test
	public void testInvalidVertical() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		assertEquals(false, b.placeShip(s, 'J', 10, true));
	}
	
	@Test
	public void testInvalidHorizontal() {
		Ship s = new Submarine("Submarine", false);
		Board b = new Board();
		assertEquals(false, b.placeShip(s, 'A', 10, false));
	}
	
	@Test
	public void testHitSubmergedSub() {
		Ship s = new Minesweeper("Minesweeper");
		Ship s2 = new Destroyer("Destroyer");
		Ship s3 = new Submarine("Submarine", true);
		Board b = new Board();
		List<Result> r;
		b.placeShip(s, 'A', 1, false);
		b.placeShip(s2, 'D', 4, false);
		b.placeShip(s3, 'D', 4, false);
		b.attack('A', 1);
		r = b.attack('D', 4);
		assertEquals(r.get(0).getResult(), Status.HIT);
		assertEquals(r.get(1).getResult(), Status.HIT);
	}
	
	@Test
	public void testSubmergedSubNoHit() {
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Submarine("Submarine", true);
		Board b = new Board();
		List<Result> r;
		b.placeShip(s, 'D', 4, false);
		b.placeShip(s2, 'D', 4, false);
		b.attack('A', 1);
		r = b.attack('D', 4);
		assertEquals(r.size(), 1);
		assertEquals(r.get(0).getResult(), Status.HIT);
		assertEquals(r.get(0).getShip().getKind(), "Destroyer");
	}
	
}
