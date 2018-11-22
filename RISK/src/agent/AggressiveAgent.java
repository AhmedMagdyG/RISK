package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public class AggressiveAgent implements IAgent {
	private AgentType agentType;
	
	public AggressiveAgent(AgentType agentType) {
		this.agentType = agentType;
	}
	
	@Override
	public AgentType getAgentType() {
		return agentType;
	}

	@Override
	public Attack attack(IGraph graph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INode deploy(IGraph graph, int soldiers) {
		// TODO Auto-generated method stub
		return null;
	}

}
