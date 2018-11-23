package agent;

import graph.IGraph;

public interface IAgentFactory {
	IAgent getAgent(AgentType type, boolean player, IGraph graph);
}
