package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	private JButton play;
	private JComboBox<String> playerOneSelector, playerTwoSelector;
	private String[] playerNames = { "Human", "Passive", "Aggressive", "Nearly Pacifist",
			"Greedy", "A*", "Real-time A*" };
	private JPanel body;
	private Controller controller;

	public MainMenu(final Controller controller) {
		this.controller = controller;

		createObjects();
		
		body.setLayout(new FlowLayout());
		body.setOpaque(false);
				
		body.add(playerOneSelector);
		body.add(play);
		body.add(playerTwoSelector);

		playerOneSelector.setSelectedIndex(0);
		playerTwoSelector.setSelectedIndex(1);

		setPreferredSize(new Dimension(1200, 600));
		setLayout(new GridBagLayout());
		add(body, new GridBagConstraints());
		setOpaque(false);

		repaint();
	}

	void createObjects() {
		body = new JPanel();
		
		play = makeButton("play");
		play.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    controller.startGame();
			  }
		} );
		
		playerOneSelector = new JComboBox<String>(playerNames);
		playerTwoSelector = new JComboBox<String>(playerNames);
	}
	
	private JButton makeButton(String name) {
		JButton ret = new JButton(new ImageIcon(new ImageIcon(getClass()
				.getResource("/images/" + name + ".png")).getImage()
				.getScaledInstance(90, 40, Image.SCALE_DEFAULT)));

		ret.setBorderPainted(false);
		ret.setContentAreaFilled(false);
		return ret;
	}

	public String getPlayerOneName() {
		return playerNames[playerOneSelector.getSelectedIndex()];
	}

	public String getPlayerTwoName() {
		return playerNames[playerTwoSelector.getSelectedIndex()];
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Image img = new ImageIcon(getClass().getResource("/images/risk.jpg"))
				.getImage();
		g2.drawImage(img, 0, 0, 1200, 600, null, null);
	}
	
}
