package edu.oregonstate.cs361.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.oregonstate.cs361.pomegranate.Board;
import edu.oregonstate.cs361.pomegranate.Minesweeper;
import edu.oregonstate.cs361.pomegranate.Result;
import edu.oregonstate.cs361.pomegranate.Status;
import edu.oregonstate.cs361.pomegranate.Submarine;

public class Milestone4Tests {
	
	private Board board;

	@Before
	public void setUp() {
		board = new Board();
		board.placeShip(new Minesweeper(""), 'B', 3, true);
		placeSurfaceSubmarine();
	}

	private Submarine createSurfaceSubmarine() {
		return new Submarine("", true);
	}
	
	private Submarine createSubmergedSubmarine() {
		return new Submarine("", true);
	}
	
	private boolean placeSurfaceSubmarine() {
		return board.placeShip(createSurfaceSubmarine(), 'D', 5, true);
	}
	
	private boolean placeSubmergedSubmarine(char column, int row, boolean isVertical) {
		return board.placeShip(createSubmergedSubmarine(), column, row, isVertical);		
	}

	@Test
	public void testLaserNotEnabled() {
		assertTrue(placeSubmergedSubmarine('G', 7, true));
		List<Result> results = board.attack('G', 7);
		assertEquals(1, results.size());
		Result result = results.iterator().next();
		assertEquals(Status.MISS, result.getResult());
	}
	
	@Test
	public void testLaserEnabled() {
		sinkMinesweeper();
		placeSubmergedSubmarine('F', 5, true);
		board.attack('F', 5);
		List<Result> results = board.attack('F', 5);
		assertEquals(1, results.size());
		Result result = results.iterator().next();
		assertEquals(Status.SUNK, result.getResult());
	}

	private void sinkMinesweeper() {
		board.attack('B', 4);
	}
	
	@Test
	public void testSubmergedSubmarineUnderShip() {
		assertTrue(placeSubmergedSubmarine('B',3, true));
	}
	
	@Test
	public void testSubmarineCaptainsQuarters() {
		sinkMinesweeper();
		List<Result> results = board.attack('D', 5);
		assertEquals(1, results.size());
		Result result = results.iterator().next();
		Status status = result.getResult();
		assertTrue(status.equals(Status.MISS) || status.equals(Status.SURRENDER));
		if (status.equals(Status.MISS)) {
			List<Result> armoredResult = board.attack('D', 5);
			assertEquals(Status.SURRENDER, armoredResult.iterator().next().getResult());
		}
	}
	
	@Test
	public void testMoveNorth() {
		board.moveNorth();
		assertEquals(Status.SUNK, board.attack('B', 3).iterator().next().getResult());
	}
	
	@Test
	public void testMoveSouth() {
		board.moveSouth();
		assertEquals(Status.SUNK, board.attack('B',5).iterator().next().getResult());
	}
	
	@Test
	public void testMoveEast() {
		board.moveEast();
		assertEquals(Status.SUNK, board.attack('C', 4).iterator().next().getResult());
	}
	
	@Test
	public void testMoveWest() {
		board.moveWest();
		assertEquals(Status.SUNK, board.attack('A', 4).iterator().next().getResult());
	}
	
	@Test
	public void test3MovesNorthWithEdge() {
		board.moveNorth();
		board.moveNorth();
		board.moveNorth();
		assertEquals(Status.SUNK, board.attack('B', 2).iterator().next().getResult());
	}
	
	@Test
	public void testUndoMoveNorth() {
		board.moveNorth();
		board.undoMove();
		assertEquals(Status.SUNK, board.attack('B', 4).iterator().next().getResult());
	}
	
	@Test
	public void testUndoMoveSouth() {
		board.moveSouth();
		board.undoMove();
		assertEquals(Status.SUNK, board.attack('B', 4).iterator().next().getResult());
	}
	
	@Test
	public void testUndoMoveEast() {
		board.moveEast();
		board.undoMove();
		assertEquals(Status.SUNK, board.attack('B', 4).iterator().next().getResult());
	}
	
	@Test
	public void testUndoMoveWest() {
		board.moveWest();
		board.undoMove();
		assertEquals(Status.SUNK, board.attack('B', 4).iterator().next().getResult());
	}
	
	@Test
	public void testUndoWithEdge() {
		board.moveNorth();
		board.moveNorth();
		board.moveNorth();
		board.undoMove();
		assertEquals(Status.SUNK, board.attack('B', 2).iterator().next().getResult());
	}
	
	@Test
	public void testRedo() {
		board.moveNorth();
		board.undoMove();
		board.redoMove();
		assertEquals(Status.SUNK, board.attack('B', 3).iterator().next().getResult());
	}
}
