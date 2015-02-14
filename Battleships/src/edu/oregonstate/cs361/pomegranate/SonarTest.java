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
		Coordinates c = new Coordinates('E', 3);
		Coordinates c2 = new Coordinates('E', 4);
		Coordinates c3 = new Coordinates('E', 5);
		Ship s = new Destroyer("Submarine", 3, 'E', 5, true);
		Ship s2 = new Minesweeper("Minesweeper", 2, 'A', 1, false);
		b.placeShip(s);
		b.placeShip(s2);
		b.attack('A', 1);
		List<Coordinates> sonarResult = b.sonarPulse('E', 5);
		assertEquals(sonarResult.get(0).getX(), c.getX());
		assertEquals(sonarResult.get(0).getY(), c.getY());
		assertEquals(sonarResult.get(1).getX(), c2.getX());
		assertEquals(sonarResult.get(1).getY(), c2.getY());
		assertEquals(sonarResult.get(2).getX(), c3.getX());
		assertEquals(sonarResult.get(2).getY(), c3.getY());
	}
	

}
