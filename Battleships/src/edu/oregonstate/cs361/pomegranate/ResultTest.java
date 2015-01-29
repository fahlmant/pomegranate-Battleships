package edu.oregonstate.cs361.pomegranate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest {

	@Test
	public void testGetResultMiss() {
		Result result = new Result();
		Status valid = result.getResult();
		assertEquals(valid, Status.MISS);
	}

}
