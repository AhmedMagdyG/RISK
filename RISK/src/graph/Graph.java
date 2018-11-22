package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph implements IGraph{
	static final String INITIALIZE_FILE_PATH = "graph.txt";
	
	ArrayList<INode> nodes;
	ArrayList<IContinent> continents;
	ArrayList<IEdge> edges;
	
	public Graph() {
		nodes = new ArrayList<INode>();
		continents = new ArrayList<IContinent>();
		edges = new ArrayList<IEdge>();
		
		try {
			Scanner in = new Scanner(new File(INITIALIZE_FILE_PATH));
			
			int n = in.nextInt(), m = in.nextInt();
			for(int i = 0; i < n; ++i)
				nodes.add(new Node(i, 1));
			for(int i = 0; i < m; ++i) {
				int u = in.nextInt(), v = in.nextInt();
				nodes.get(u).addNeighbour(nodes.get(v));
				nodes.get(v).addNeighbour(nodes.get(u));
				edges.add(new Edge(nodes.get(u), nodes.get(v), i));
			}
			int continentCount = in.nextInt();
			for(int i = 0; i < continentCount; ++i) {
				ArrayList<INode> continentNodes = new ArrayList<INode>();
				n = in.nextInt();
				while(n-- > 0) {
					m = in.nextInt();
					continentNodes.add(nodes.get(m));
				}
				int bonus = in.nextInt();
				continents.add(new Continent(continentNodes, bonus));
			}
			n = in.nextInt();
			while(n-- > 0) {
				m = in.nextInt();
				nodes.get(m).setOwnerType(false);
			}
			n = in.nextInt();
			while(n-- > 0) {
				m = in.nextInt();
				nodes.get(m).setOwnerType(true);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Failed to load game map.");
			System.exit(0);
		}
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
		if(id < 0 || id >= nodes.size())
			return null;
		return nodes.get(id);
	}
	
}
