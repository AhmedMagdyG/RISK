package graph;

import java.util.List;

public class Continent implements IContinent {
	private List<INode> nodes;
	private int bonus;
	
	public Continent(List<INode> nodes, int bonus) {
		this.nodes = nodes;
		this.bonus = bonus;
	}

	@Override
	public List<INode> getNodes() {
		return nodes;
	}

	@Override
	public int getBouns() {
		return bonus;
	}
	
}
