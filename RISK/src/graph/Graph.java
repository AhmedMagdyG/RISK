package graph;

import java.util.ArrayList;

public class Graph implements IGraph{
	static final String INITIALIZE_FILE_PATH = "graph.txt";
	
	ArrayList<INode> nodes;
	
	public Graph() {
		// initialize graph
	}


	@Override
	public ArrayList<INode> getNodes() {
		return nodes;
	}
	
	
}
