package graph;

import java.util.List;

public class Node implements INode{
	private boolean ownerType;
	private int lastOccupied;
	private int soldiers;
	private int id;
	private List<INode> neighbours;
	
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
	public void setSoldiers(int soldiers) {
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
	public List<INode> getNeighbours() {
		return this.neighbours;
	}

	@Override
	public boolean isNeighbour(INode node) {
		if(node == null)
			return false;
		
		for(INode neighbour: neighbours) {
			if(neighbour.getId() == node.getId())
				return true;
		}
		return false;
	}
		
	
}