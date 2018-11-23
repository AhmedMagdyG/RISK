package controller;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import agent.AgentFactory;
import agent.AgentType;
import agent.IAgent;
import graph.Graph;
import graph.IEdge;
import graph.IGraph;
import graph.INode;
import view.GameScreen;
import view.MainMenu;

public class Controller extends JFrame implements IController {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final static boolean PLAYER1_ID = false, PLAYER2_ID = true;
	private final String MAIN_MENU = "Main Menu";
	private final String GAME_SCREEN = "Game Screen";

	private IAgent player1, player2, curPlayer;
	private IGraph graph;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;

	private boolean gameRunning, roleRunning;

	// remainging agents
	// "Greedy", "A*", "Real-time A*"
	private AgentType getStringMappingToAgentType(String agentType) {
		switch (agentType) {
		case "Human":
			return AgentType.HUMAN;
		case "Passive":
			return AgentType.PASSIVE;
		case "Aggressive":
			return AgentType.AGGRESSIVE;
		case "Nearly Pacifist":
			return AgentType.PACIFIST;
		case "Greedy":
			return AgentType.GREEDY;
		case "A Star":
			return AgentType.A_STAR;
		case "Real-time A*":
			return AgentType.RTA_STAR;
		default:
			return null;
		}
	}

	public Controller() {
		super("RISK");

		cardLayout = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cardLayout);

		mainMenu = new MainMenu(this);
		gameScreen = new GameScreen(this);

		cardPanel.add(mainMenu, MAIN_MENU);
		cardPanel.add(gameScreen, GAME_SCREEN);
		add(cardPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);

		showMainMenu();
		gamePlay();
	}

	@Override
	public void showMainMenu() {
		cardLayout.show(cardPanel, MAIN_MENU);
		gameRunning = false;
		while (!gameRunning) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		requestFocus();
	}

	@Override
	public boolean checkGameOver() {
		ArrayList<INode> nodes = graph.getNodes();
		for (INode node : nodes) {
			if (node.getOwnerType() != nodes.get(0).getOwnerType())
				return false;
		}
		return true;
	}

	private void initializeGamePlayers(AgentType player1AgentType, AgentType player2AgentType) {
		player1 = AgentFactory.getInstance().getAgent(player1AgentType, PLAYER1_ID, graph);
		player2 = AgentFactory.getInstance().getAgent(player2AgentType, PLAYER2_ID, graph);
	}

	private void buildGraph() {
		graph = new Graph();
		for (INode node : graph.getNodes()) {
			gameScreen.addNode(String.valueOf(node.getId()));
			String playerColor = node.getOwnerType() ? "black" : "white";
			gameScreen.setSoldiersInNode(String.valueOf(node.getId()), node.getContinent().getColor(), playerColor,
					node.getSoldiers());
		}

		for (IEdge e : graph.getEdges()) {
			gameScreen.addEdge(String.valueOf(e.getId()), String.valueOf(e.getU().getId()),
					String.valueOf(e.getV().getId()));
		}
	}

	@Override
	public void startGame() {
		String player1AgentTypeName = mainMenu.getPlayerOneName();
		String player2AgentTypeName = mainMenu.getPlayerTwoName();

		buildGraph();

		AgentType player1AgentType = getStringMappingToAgentType(player1AgentTypeName);
		AgentType player2AgentType = getStringMappingToAgentType(player2AgentTypeName);
		initializeGamePlayers(player1AgentType, player2AgentType);

		gameScreen.setPlayerOneLabel("White : " + player1AgentTypeName);
		gameScreen.setPlayerTwoLabel("Black : " + player2AgentTypeName);

		if (player1.getAgentType() != AgentType.HUMAN)
			gameScreen.setEnabledPlayerOne(false);

		if (player2.getAgentType() != AgentType.HUMAN)
			gameScreen.setEnabledPlayerTwo(false);

		cardLayout.show(cardPanel, GAME_SCREEN);
		gameRunning = true;
	}

	@Override
	public void gamePlay() {
		boolean role;
		for (role = false; !checkGameOver(); role ^= true) {
			curPlayer = role ? player2 : player1;

			if (player1.getAgentType() == AgentType.HUMAN)
				gameScreen.setEnabledPlayerOne(!role);
			if (player2.getAgentType() == AgentType.HUMAN)
				gameScreen.setEnabledPlayerTwo(role);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			roleRunning = true;
			if (curPlayer.getAgentType() != AgentType.HUMAN) {
				nonhumanDistributeBonus();
				nonhumanAttack();
			}

			while (roleRunning) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		gameScreen.setLogMessage("Player " + String.valueOf(role ? 1 : 2) + " won!");
		gameScreen.setEnabledPlayerOne(false);
		gameScreen.setEnabledPlayerTwo(false);
	}

	@Override
	public void skipAttack() {
		// hide input fields or end waiting for user click
		roleRunning = false;
	}

	@Override
	public boolean humanAttack() {
		int fromId = 0, toId = 0, soldiers = 0; // show input fields and read data
		gameScreen.setLogMessage("Select attacker node.");
		// get fromId
		// gameScreen.setLogMessage("Select node to attack.");
		// get toId
		// gameScreen.setLogMessage("Enter number of soldiers.");
		// get soldiers

		INode from = graph.getNodeById(fromId), to = graph.getNodeById(toId);
		if (from == null || to == null)
			return false;

		if (!from.isNeighbour(to))
			return false;

		if (from.getSoldiers() <= soldiers || soldiers <= to.getSoldiers())
			return false;
		from.setSoldiers(from.getSoldiers() - soldiers);
		to.setSoldiers(soldiers - to.getSoldiers());

		roleRunning = false;
		return true;
	}

	@Override
	public void nonhumanAttack() {
		Attack attackObject = curPlayer.attack(graph);
		if (attackObject == null) {
			curPlayer.setLastTurnAttack(false);
			roleRunning = false;
			return;
		}
		curPlayer.setLastTurnAttack(true);

		int newFromSoldiers = attackObject.getFrom().getSoldiers() - attackObject.getSoldiers();
		attackObject.getFrom().setSoldiers(newFromSoldiers);
		int newToSoldiers = attackObject.getSoldiers() - attackObject.getTo().getSoldiers();
		attackObject.getTo().setSoldiers(newToSoldiers);
		attackObject.getTo().setOwnerType(curPlayer.getPlayer());

		String playerColor = attackObject.getFrom().getOwnerType() ? "black" : "white";
		gameScreen.setSoldiersInNode(String.valueOf(attackObject.getFrom().getId()),
				attackObject.getFrom().getContinent().getColor(), playerColor, attackObject.getFrom().getSoldiers());

		playerColor = attackObject.getTo().getOwnerType() ? "black" : "white";
		gameScreen.setSoldiersInNode(String.valueOf(attackObject.getTo().getId()),
				attackObject.getTo().getContinent().getColor(), playerColor, attackObject.getTo().getSoldiers());
		roleRunning = false;
	}

	@Override
	public void attackHandler() {
		if (curPlayer.getAgentType() != AgentType.HUMAN)
			nonhumanAttack();
		else
			humanAttack();
	}

	@Override
	public IAgent getCurAgent() {
		return curPlayer;
	}

	@Override
	public void nonhumanDistributeBonus() {
		int bonus = graph.calculateBonus(curPlayer.getPlayer(), curPlayer.lastTurnAttack());
		INode node = curPlayer.deploy(graph, bonus);
		node.setSoldiers(node.getSoldiers() + bonus);
		String playerColor = node.getOwnerType() ? "black" : "white";
		gameScreen.setSoldiersInNode(String.valueOf(node.getId()), node.getContinent().getColor(), playerColor,
				node.getSoldiers());
	}

	@Override
	public void humanDistributeBonus() {
		// get bonus node
		int bonus = graph.calculateBonus(curPlayer.getPlayer(), curPlayer.lastTurnAttack());
		gameScreen.setLogMessage("Select node to deploy" + String.valueOf(bonus) + " on.");
		// add bonus on this node
	}

	@Override
	public void distributeBonusHandler() {
		if (curPlayer.getAgentType() == AgentType.HUMAN)
			humanDistributeBonus();
		else
			nonhumanDistributeBonus();
	}

	public static void main(String[] args) {
		new Controller();
	}

}
