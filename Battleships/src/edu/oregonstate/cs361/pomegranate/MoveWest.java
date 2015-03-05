package edu.oregonstate.cs361.pomegranate;

public class MoveWest extends Move {

	public MoveWest(Board b) {
		super(b);
		undoType = null;
	}   

	public void moveIt()
	{
		for(int i = 0; i < b.totalShips; i++)
		{
			if(b.ships.get(i).isVertical())
			{
				if(b.ships.get(i).checkMove("West"))
				{
					for(int j = 0; j < b.ships.get(i).getSize(); j++) {
						b.ships.get(i).getLocation().get(j).setX((char) (b.ships.get(i).getLocation().get(j).getX() - 1)); 
					}
					b.ships.get(i).setCQ((char) (b.ships.get(i).getCq().getX() - 1), b.ships.get(i).getCq().getY());
				}
			}
			else
			{
				if(b.ships.get(i).checkMove("West"))
				{
					for(int j = 0; j < b.ships.get(i).getSize(); j++) {
						b.ships.get(i).getLocation().get(j).setX((char) (b.ships.get(i).getLocation().get(j).getX() - 1)); 
					}
					b.ships.get(i).setCQ((char) (b.ships.get(i).getCq().getX() - 1), b.ships.get(i).getCq().getY());
				}
			}
		}
		b.undoStack.push(this);
		
	}
	
	public Move undo() {
		undoType = new MoveEast(b);
		return undoType;
	}

}
