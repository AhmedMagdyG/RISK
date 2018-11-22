import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
	private GraphListener clicksListener;
	
	public GameScreen(Controller controller) {
		this.controller = controller;

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
		clicksListener = new GraphListener(graph, viewer);
		view.addMouseListener(clicksListener);
		add(view);
		
		initButtons();
	}

	private void initButtons() {
		playerOneLabel = new JLabel("Player 1 : SET THIS");
		playerOneLabel.setBounds(950, 30, 250, 40);
		add(playerOneLabel);
		
		playerTwoLabel = new JLabel("Player 2 : SET THIS");
		playerTwoLabel.setBounds(950, 300, 250, 40);
		add(playerTwoLabel);
		
		playerOneAttack = makeButton("Attack");
		playerOneAttack.setBounds(960, 80, 80, 80);
		add(playerOneAttack);
		
		playerTwoAttack = makeButton("Attack");
		playerTwoAttack.setBounds(960, 350, 80, 80);
		add(playerTwoAttack);
		
		playerOneSkip = makeButton("Skip");
		playerOneSkip.setBounds(960, 180, 80, 80);
		playerOneSkip.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    controller.skipAttack();
			  }
		} );
		add(playerOneSkip);
		
		playerTwoSkip = makeButton("Skip");
		playerTwoSkip.setBounds(960,  450, 80, 80);
		playerTwoSkip.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    controller.skipAttack();
			  }
		} );
		add(playerTwoSkip);
	}
	
	private JButton makeButton(String name) {
		JButton ret = new JButton(new ImageIcon(new ImageIcon(getClass()
				.getResource("/images/" + name + ".jpg")).getImage()));
		return ret;
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
	
	public void setSoldiersInNode(String id, String continentColor, String playerColor, int soldiers) {
		Node node = graph.getNode(id);
		node.setAttribute("ui.style", "stroke-mode: plain;" + 
				  "text-style: bold; text-size: 15;" +
				  "fill-color: " + continentColor + "; size: 30;" +
				  "text-color: " + playerColor);
		node.setAttribute("ui.label", String.valueOf(soldiers));
	}
}
