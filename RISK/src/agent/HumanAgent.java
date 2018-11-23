package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public class HumanAgent implements IAgent{

	private AgentType agentType;
	private boolean player;
	private boolean lastTurnAttack;
	
	public HumanAgent(AgentType agentType, boolean player) {
		this.agentType = agentType;
		this.player = player;
		this.lastTurnAttack = false;
		
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
		return player;
	}

	@Override
	public boolean lastTurnAttack() {
		return lastTurnAttack;
	}

}
