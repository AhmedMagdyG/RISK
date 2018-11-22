package graph;

import java.util.List;

public class Graph implements IGraph{
	static final String INITIALIZE_FILE_PATH = "graph.txt";
	private List<IContinent> continents; 
	
	public Graph() {
		// initialize graph
	}


	@Override
	public List<IContinent> getContinents() {
		return continents;
	}
	
	
}
