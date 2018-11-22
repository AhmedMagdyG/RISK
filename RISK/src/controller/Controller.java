package controller;

import java.util.ArrayList;

import agent.AgentFactory;
import agent.AgentType;
import agent.IAgent;
import graph.Graph;
import graph.IGraph;
import graph.INode;

public class Controller implements IController{

	final static boolean PLAYER1_TYPE = false, PLAYER2_TYPE = true;
	private IAgent player1, player2, curPlayer;
	private IGraph graph;
	
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
	public void initializeGame(AgentType player1AgentType, AgentType player2AgentType) {
		player1 = AgentFactory.getInstance().getAgent(player1AgentType, PLAYER1_TYPE);
		player2 = AgentFactory.getInstance().getAgent(player2AgentType, PLAYER2_TYPE);
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
	public boolean humanAttack(int fromId, int toId, int soldiers) {
		INode from = graph.getNodeById(fromId), to = graph.getNodeById(toId);
		if(from == null || to == null) return false;
		
		if(!from.isNeighbour(to)) return false;
		
		if(from.getSoldiers() <= soldiers || soldiers <= to.getSoldiers()) return false; 
		
		
		
		return true;
	}

	@Override
	public void attack() {
		Attack attackObject = curPlayer.attack(graph);
		int newFromSoldiers = attackObject.getFrom().getSoldiers()-attackObject.getSoldiers();
		attackObject.getFrom().setSoldiers(newFromSoldiers);
		int newToSoldiers = attackObject.getSoldiers()-attackObject.getTo().getSoldiers();
		attackObject.getTo().setSoldiers(newToSoldiers);
		attackObject.getTo().setOwnerType(curPlayer.getPlayer());
		// set view values
	}
	
	@Override
	public IAgent getCurAgent() {
		return curPlayer;
	}

	@Override
	public void distributeBonus() {
		curPlayer.deploy(graph, graph.calculateBonus(curPlayer.getPlayer(), curPlayer.lastTurnAttack()));
	}

	
}





