package edu.oregonstate.cs361.pomegranate;

public class MoveNorth extends Move {

	public MoveNorth(Board b) {
		super(b);
		undoType = null;
	}
	
	public void moveIt()
	{
		for(int i = 0; i < b.totalShips; i++)
		{			
			if(b.ships.get(i).isVertical())
			{
				if(b.ships.get(i).checkMove("North"))
				{
					for(int j = 0; j < b.ships.get(i).getSize(); j++) {
						b.ships.get(i).getLocation().get(j).setY(b.ships.get(i).getLocation().get(j).getY() - 1); 
					}
					b.ships.get(i).setCQ(b.ships.get(i).getCq().getX(), b.ships.get(i).getCq().getY() - 1);
				}
			}
			else
			{
				if(b.ships.get(i).checkMove("North"))
				{
					for(int j = 0; j < b.ships.get(i).getSize(); j++) {
						b.ships.get(i).getLocation().get(j).setY(b.ships.get(i).getLocation().get(j).getY() - 1); 
					}
					b.ships.get(i).setCQ(b.ships.get(i).getCq().getX(), b.ships.get(i).getCq().getY() - 1);
				}
			}	
		}
		b.undoStack.push(this);
	}
	
	public Move undo()
	{
		undoType = new MoveSouth(b);
		return undoType;
	}

}
