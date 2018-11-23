package agent;

import controller.Attack;
import graph.IContinent;
import graph.IGraph;
import graph.INode;

public class AggressiveAgent implements IAgent {
	private AgentType agentType;
	private boolean player;
	private boolean lastTurnAttack;

	public AggressiveAgent(AgentType agentType, boolean player, IGraph graph) {
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
		// TODO - update last turn attack
		return null;
	}

	@Override
	public INode deploy(IGraph graph, int soldiers) {
		INode ret = graph.getContinents().get(0).getNodes().get(0);
		for (IContinent continent : graph.getContinents()) {
			for (INode node : continent.getNodes()) {
				if (node.getSoldiers() > ret.getSoldiers()) {
					ret = node;
				} else if ((node.getSoldiers() == ret.getSoldiers()) && (node.getId() < ret.getId())) {
					ret = node;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean getPlayer() {
		return player;
	}

	@Override
	public boolean lastTurnAttack() {
		return this.lastTurnAttack;
	}

}