package controller;

import agent.AgentType;
import agent.IAgent;

public interface IController {
	
	public boolean checkGameOver();
	
	public void initializeGame(AgentType player1AgentType, AgentType player2AgentType);
	
	public boolean humanAttack(int fromId, int toId, int soldiers);
	
	public void attack();
		
	public IAgent getCurAgent();
	
	public void gamePlay();
	
	public void distributeBonus();
	
}
