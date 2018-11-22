package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public class PassiveAgent implements IAgent {
	private AgentType agentType;
	
	public PassiveAgent(AgentType agentType) {
		this.agentType = agentType;
	}
	
	@Override
	public AgentType getAgentType() {
		return this.agentType;
	}

	@Override
	public Attack attack(IGraph graph) {
		return null;
	}

	@Override
	public INode deploy(IGraph graph, int soldiers) {
		// TODO Auto-generated method stub
		return null;
	}

}
