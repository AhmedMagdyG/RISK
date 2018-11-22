package graph;

import java.util.List;

public class Continent implements IContinent {
	private List<INode> nodes;
	private int bonus;
	private int continentId;
	
	public Continent(List<INode> nodes, int bonus, int continentId) {
		this.nodes = nodes;
		this.bonus = bonus;
		this.continentId = continentId;
	}

	@Override
	public List<INode> getNodes() {
		return this.nodes;
	}

	@Override
	public int getBouns() {
		return this.bonus;
	}

	@Override
	public int getContinentId() {
		return this.continentId;
	}
	
}
