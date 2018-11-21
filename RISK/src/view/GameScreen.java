package view;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.*;
import org.graphstream.ui.view.*;

@SuppressWarnings("serial")
public class GameScreen extends JPanel {
	
	private Controller controller;
	private JLabel log, playerOneLabel, playerTwoLabel;
	private Graph graph;
	private JButton playerOneAttack, playerOneSkip;
	private JButton playerTwoAttack, playerTwoSkip; 
	private Clicks clicksListener;
	
	public GameScreen(Controller controller) {
		this.setController(controller);

		setLayout(null);
		setPreferredSize(new Dimension(1200, 600));

		log = new JLabel("Welcome to RISK.");
		log.setBounds(10, 560, 790, 40);
		add(log);
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		graph = new SingleGraph("RISK");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		ViewPanel view = viewer.addDefaultView(false);
		view.setBounds(0, 0, 800, 560);
		clicksListener = new Clicks(graph, viewer);
		view.addMouseListener(clicksListener);
		add(view);
		
		initButtons();
	}

	private void initButtons() {
		playerOneLabel = new JLabel();
		playerOneLabel.setBounds(950, 30, 250, 40);
		add(playerOneLabel);
		
		playerTwoLabel = new JLabel();
		playerTwoLabel.setBounds(950, 300, 250, 40);
		add(playerTwoLabel);
		
		playerOneAttack = new JButton("Attack");
		playerOneAttack.setBounds(960, 80, 80, 80);
		add(playerOneAttack);
		
		playerTwoAttack = new JButton("Attack");
		playerTwoAttack.setBounds(960, 350, 80, 80);
		add(playerTwoAttack);
		
		playerOneSkip = new JButton("Skip");
		playerOneSkip.setBounds(960, 180, 80, 80);
		add(playerOneSkip);
		
		playerTwoSkip = new JButton("Skip");
		playerTwoSkip.setBounds(960,  450, 80, 80);
		add(playerTwoSkip);
	}
	
	/**
	 * Something like: Player 1 : Human
	 */
	public void setPlayerOneLabel(String text) {
		playerOneLabel.setText(text);
	}
	
	public void setPlayerTwoLabel(String text) {
		playerTwoLabel.setText(text);
	}
	
	public void setEnabledPlayerOne(boolean enabled) {
		playerOneAttack.setEnabled(enabled);
		playerOneSkip.setEnabled(enabled);
	}
	
	public void setEnabledPlayerTwo(boolean enabled) {
		playerTwoAttack.setEnabled(enabled);
		playerTwoSkip.setEnabled(enabled);
	}
	
	public void setLogMessage(String message) {
		log.setText(message);
	}
	
	public void addNode(String id) {
		graph.addNode(id);
	}
	
	public void addEdge(String id, String u, String v) {
		graph.addEdge(id, u, v);
	}
	
	public void setSoldiersInNode(String id, boolean playerID, int soldiers) {
		String color = playerID ? "white" : "black";
		Node node = graph.getNode(id);
		node.setAttribute("ui.style", "stroke-mode: plain;" + 
				  "text-style: bold; text-size: 15;" +
				  "fill-color: red; size: 30;" +
				  "text-color: " + color);
		node.setAttribute("ui.label", String.valueOf(soldiers));
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/*
	 * TODO
	 * attack / skip actions
	 */
}
