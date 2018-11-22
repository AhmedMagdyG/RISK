package graph;

import java.util.ArrayList;

public class Continent implements IContinent {
	
	private ArrayList<INode> nodes;
	private int bonus;
	
	public Continent(ArrayList<INode> nodes, int bonus) {
		this.nodes = nodes;
		this.bonus = bonus;
	}
	
	@Override
	public ArrayList<INode> getNodes() {
		return nodes;
	}

	@Override
	public int getBonus() {
		return bonus;
	}

}
