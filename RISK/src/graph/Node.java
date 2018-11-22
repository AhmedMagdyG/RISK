package graph;

import java.util.ArrayList;
import java.util.List;

public class Node implements INode{
	private boolean ownerType;
	private int lastOccupied;
	private int soldiers;
	private int id;
	private List<INode> neighbours;
	private IContinent continent;
	
	public Node(int id) {
		this.id = id;
		this.neighbours = new ArrayList<INode>();
	}
	
	public Node(int id, int soldiers) {
		this.id = id;
		this.soldiers = soldiers;
		this.neighbours = new ArrayList<INode>();
	}
	
	@Override
	public IContinent getContinent() {
		return continent;
	}
	
	@Override
	public void setContinent(IContinent continent) {
		this.continent = continent;
	}
	
	@Override
	public void addNeighbour(INode node) {
		neighbours.add(node);
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