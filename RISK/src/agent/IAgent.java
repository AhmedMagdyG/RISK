package agent;

import controller.Attack;
import graph.IGraph;
import graph.INode;

public interface IAgent {
	public AgentType getAgentType();
	
	/**
	 * Decides which neighbor to attack.
	 * @param graph representing the game state.
	 * @return Attack object with the info needed for attack or null if no attack to be done.
	 */
	public Attack attack(IGraph graph);
	
	public INode deploy(IGraph graph, int soldiers);
	
	public boolean getPlayer();
	
	public boolean lastTurnAttack();
}
