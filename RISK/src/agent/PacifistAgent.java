package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public class PacifistAgent implements IAgent {
	private AgentType agentType;
	private boolean player;
	
	public PacifistAgent(AgentType agentType, boolean player) {
		this.agentType = agentType;
		this.player = player;
		
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

	@Override
	public boolean getPlayer() {
		return this.player;
	}

}
