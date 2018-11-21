package agent;

import controller.Attack;
import graph.IGraph;

public interface IAgent {
	public AgentType getAgentType();
	
	public Attack attack(IGraph graph);
}
