package agent;

import controller.Attack;
import graph.IContinent;
import graph.IGraph;
import graph.INode;

public class PassiveAgent implements IAgent {
	private AgentType agentType;
	private boolean player;
	private boolean lastTurnAttack;

	public PassiveAgent(AgentType agentType, boolean player, IGraph graph) {
		this.agentType = agentType;
		this.player = player;
		this.lastTurnAttack = false;
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
		INode ret = graph.getContinents().get(0).getNodes().get(0); // what if this node is not mine ? :D
		for (IContinent continent : graph.getContinents()) {
			for (INode node : continent.getNodes()) {
				if (player != node.getOwnerType())
					continue;
				if (node.getSoldiers() < ret.getSoldiers()) {
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
		return this.player;
	}

	@Override
	public boolean lastTurnAttack() {
		return this.lastTurnAttack;
	}

}