package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public class PacifistAgent implements IAgent {
	private AgentType agentType;
	
	public PacifistAgent(AgentType agentType) {
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
