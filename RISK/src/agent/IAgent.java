package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public interface IAgent {
	public AgentType getAgentType();
	
	public Attack attack(IGraph graph);
	
	public INode deploy(IGraph graph, int soldiers);
}