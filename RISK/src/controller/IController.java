package controller;

import agent.AgentType;
import agent.IAgent;

public interface IController {
	
	public boolean checkGameOver();
	
	public void initializeGame(AgentType player1Type, AgentType player2Type);
	
	public Attack attack();
		
	public IAgent getCurAgent();
	
	public void gamePlay();
	
}
