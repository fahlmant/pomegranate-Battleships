package edu.oregonstate.cs361.pomegranate;

public class Move {

	protected Move undoType;
	protected Board b;
	
	public Move(Board b) {
		this.b = b;
	}

	public void moveIt()
	{
		
	}
	
	public Move undo()
	{
		return undoType;
		
	}
}
