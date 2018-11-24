package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import controller.IController;

@SuppressWarnings("serial")
public class GameScreen extends JPanel {

	private IController controller;
	private JLabel log, playerOneLabel, playerTwoLabel;
	private Graph graph;
	private JButton skipButton;
	private GraphListener clicksListener;

	public GameScreen(IController controller) {
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
		clicksListener = new GraphListener(graph, viewer, controller);
		view.addMouseListener(clicksListener);
		view.setBorder(BorderFactory.createLineBorder(Color.black));
		add(view);

		initButtons();
	}

	private void initButtons() {
		playerOneLabel = new JLabel();
		playerOneLabel.setBounds(955, 60, 250, 40);
		add(playerOneLabel);

		playerTwoLabel = new JLabel();
		playerTwoLabel.setBounds(955, 100, 250, 40);
		add(playerTwoLabel);

		skipButton = makeButton("Skip");
		skipButton.setBounds(960, 200, 80, 80);
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.skipAttack();
			}
		});
		add(skipButton);
	}

	private JButton makeButton(String name) {
		JButton ret = new JButton(
				new ImageIcon(new ImageIcon(getClass().getResource("/images/" + name + ".jpg")).getImage()));
		return ret;
	}

	public void setPlayerOneLabel(String text) {
		playerOneLabel.setText(text);
	}

	public void setPlayerTwoLabel(String text) {
		playerTwoLabel.setText(text);
	}

	public void setSkipEnabled(boolean enabled) {
		skipButton.setEnabled(enabled);
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
		node.setAttribute("ui.label", String.valueOf(soldiers));
		node.setAttribute("ui.style", "stroke-mode: plain;" + "text-style: bold; text-size: 15;" + "fill-color: "
				+ continentColor + "; size: 30;" + "text-color: " + playerColor + ";");
	}

	public int readAttackSoldiers() {
		String ret = JOptionPane.showInputDialog("How many soldiers to attack with ?");
		try {
			return Integer.parseInt(ret);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
}
