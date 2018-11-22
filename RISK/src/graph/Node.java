package graph;

import java.util.List;

public class Node implements INode{
	private boolean ownerType;
	private int lastOccupied;
	private int soldiers;
	private int nodeId;
	private int continentId;
	private List<INode> neighbors;
	
	public Node(int nodeId, int continentId) {
		this.nodeId = nodeId;
		this.continentId = continentId;
	}
	
	@Override
	public boolean getOwnerType() {
		return this.ownerType;
	}
	@Override
	public void setOwnerType(boolean ownerType) {
		this.ownerType = ownerType;
	}
	@Override
	public int getSoldiers() {
		return soldiers;
	}
	@Override
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	@Override
	public int getNodeId() {
		return this.nodeId;
	}

	@Override
	public int getLastOccupied() {
		return this.lastOccupied;
	}

	@Override
	public void setLastOccupied(int lastOccupied) {
		this.lastOccupied = lastOccupied;
	}

	@Override
	public List<INode> getNeighbors() {
		return this.neighbors;
	}

	@Override
	public int getContinentId() {
		return this.continentId;
	}
	
	
	
}
