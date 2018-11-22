package graph;

import java.util.ArrayList;

public class Graph implements IGraph{
	static final String INITIALIZE_FILE_PATH = "graph.txt";
	
	ArrayList<INode> nodes;
	ArrayList<IContinent> continents;
	ArrayList<IEdge> edges;
	
	public Graph() {
		// initialize graph
	}


	@Override
	public ArrayList<INode> getNodes() {
		return nodes;
	}

	@Override
	public ArrayList<IEdge> getEdges() {
		return edges;
	}

	@Override
	public ArrayList<IContinent> getContinents() {
		return continents;
	}


	@Override
	public int calculateBonus(boolean owner, boolean madeLastRoleAttack) {
		int bonus = madeLastRoleAttack ? 2: 0;
		
		for(IContinent continent: continents) {
			boolean hasContinent = true;
			for(INode node: continent.getNodes()) {
				if(node.getOwnerType() != owner) {
					hasContinent = false;
					break;
				}
			}
			if(hasContinent) 
				bonus += continent.getBonus();
			
		}
		
		return bonus;
	}


	@Override
	public INode getNodeById(int id) {
		if(id <= 0)
			return null;
		return nodes.get(id-1);
	}


	
	
	
}
