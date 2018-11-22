package controller;

import agent.IAgent;


public interface IController {
	
	public void showMainMenu();
	
	public boolean checkGameOver();
			
	public void startGame();
	
	public void skipAttack();
	
	public void gamePlay();
	
	public boolean humanAttack();
	
	public void nonhumanAttack();
	
	public void attackHandler();
		
	public IAgent getCurAgent();
	
	public void nonhumanDistributeBonus();
	
	public void humanDistributeBonus();
	
	public void distributeBonusHandler();
	
}
