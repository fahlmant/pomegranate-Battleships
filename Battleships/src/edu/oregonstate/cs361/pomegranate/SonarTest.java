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
		Result r = null;
		Coordinates c = new Coordinates('E', 5);
		Ship s = new Ship("Submarine", 3, 'E', 5, true);
		List<Coordinates> sonarResult; 
		sonarResult = b.sonarPulse('E', 5);
		assertEquals(sonarResult.get(0),  c);
		
	}
	

}