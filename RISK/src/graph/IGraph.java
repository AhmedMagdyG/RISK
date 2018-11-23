package graph;

import java.util.ArrayList;

public interface IGraph {
		
	public ArrayList<INode> getNodes();

	public ArrayList<IEdge> getEdges();
	
	public ArrayList<IContinent> getContinents();
	
	public int calculateBonus(boolean owner, boolean madeLastRoleAttack);

	public INode getNodeById(int id);

}
