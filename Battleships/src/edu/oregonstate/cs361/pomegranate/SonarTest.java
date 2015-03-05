package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.oregonstate.cs361.api.AmmoExhaustedException;
import edu.oregonstate.cs361.api.Coordinates;
import edu.oregonstate.cs361.api.WeaponUnavailableException;

public class SonarTest {
	@Test
	
	public void testSonar() throws WeaponUnavailableException, AmmoExhaustedException{
		Board b = new Board();
		Coordinates c = new Coordinates('E', 5);
		Coordinates c2 = new Coordinates('E', 6);
		Coordinates c3 = new Coordinates('E', 7);
		Ship s = new Destroyer("Destroyer");
		Ship s2 = new Minesweeper("Minesweeper");
		b.placeShip(s, 'E', 5, true);
		b.placeShip(s2, 'A', 1, false);
		b.attack('B', 1);
		assertEquals(b.getGrid()[4][4], Grid.SHIP);
		List<Coordinates> sonarResult = b.sonarPulse('E', 5);
		assertEquals(sonarResult.size(), 3);
		assertEquals(sonarResult.get(0).getX(), c.getX());
		assertEquals(sonarResult.get(0).getY(), c.getY());
		assertEquals(sonarResult.get(1).getX(), c2.getX());
		assertEquals(sonarResult.get(1).getY(), c2.getY());
		assertEquals(sonarResult.get(2).getX(), c3.getX());
		assertEquals(sonarResult.get(2).getY(), c3.getY());
	}
	

}
