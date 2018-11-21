package controller;

import java.util.ArrayList;

import agent.AgentFactory;
import agent.AgentType;
import agent.IAgent;
import graph.Graph;
import graph.IGraph;
import graph.INode;

public class Controller implements IController{

	IAgent player1, player2, curPlayer;
	IGraph graph;
	
	@Override
	public boolean checkGameOver() {
		ArrayList<INode> nodes = graph.getNodes();
		for(INode node: nodes) {
			if(node.getOwnerType() != nodes.get(0).getOwnerType())
				return false;
		}
		
		return true;
	}

	@Override
	public void initializeGame(AgentType player1Type, AgentType player2Type) {
		player1 = AgentFactory.getInstance().getAgent(player1Type);
		player2 = AgentFactory.getInstance().getAgent(player2Type);
		graph = new Graph();
	}
	

	@Override
	public void gamePlay() {
		
		for(boolean role = false; !checkGameOver() ;role ^= true) {
			curPlayer = role ? player2: player1;
			// it may be in gui
		}
		
	}

	@Override
	public Attack attack() {
		return curPlayer.attack(graph);
	}


	@Override
	public IAgent getCurAgent() {
		return curPlayer;
	}
	
}





