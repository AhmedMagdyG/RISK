package graph;

import java.util.List;

public interface INode {
	
	public boolean getOwnerType();
	
	public void setOwnerType(boolean ownerType);
	
	public int getSoldiers();
	
	public void setSoldiers(int soldiers);
	
	public int getNodeId();
	
	public int getLastOccupied();
	
	public void setLastOccupied(int lastOccupied);
	
	public List<INode> getNeighbors();
	
	public int getContinentId();
}
