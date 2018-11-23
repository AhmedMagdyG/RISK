package agent;

import graph.IGraph;

public class AgentFactory implements IAgentFactory {

	private static AgentFactory singleAgentFactor = new AgentFactory();

	private AgentFactory() {

	}

	public static AgentFactory getInstance() {
		return singleAgentFactor;
	}

	@Override
	public IAgent getAgent(AgentType type, boolean player, IGraph graph) {
		switch (type) {
		case HUMAN:
			return new HumanAgent(type, player, graph);
		case PASSIVE:
			return new PassiveAgent(type, player, graph);
		case AGGRESSIVE:
			return new AggressiveAgent(type, player, graph);
		case PACIFIST:
			return new PacifistAgent(type, player, graph);
		case GREEDY:
			return new GreedyAgent(type, player, graph);
		case A_STAR:
			return new AStarAgent(type, player, graph);
		case RTA_STAR:
			return new RTAStarAgent(type, player, graph);
		default:
			return null;
		}
	}

}
