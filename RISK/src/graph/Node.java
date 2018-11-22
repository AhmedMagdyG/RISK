package graph;

import java.util.List;

public class Node implements INode{
	private boolean ownerType;
	private int lastOccupied;
	private int soldiers;
	private int id;
	private List<INode> neighbors;
	
	public Node(int id) {
		this.id = id;
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
	public void setSoldiers(int value) {
		this.soldiers = soldiers;
	}
	@Override
	public int getId() {
		return this.id;
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
	
	
	
}
