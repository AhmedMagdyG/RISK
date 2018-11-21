package view;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controller extends JFrame {
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private final String MAIN_MENU = "Main Menu";
	private final String GAME_SCREEN = "Game Screen";
	private boolean gameRunning;

	public static void main(String[] args) {
		new Controller();
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
	}

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
		
		//gameScreen.startGame();
	}
	
	public void startGame() {
//		String playerOne = mainMenu.getPlayerOneName();
//		String playerTwo = mainMenu.getPlayerTwoName();
		// inits
		
		cardLayout.show(cardPanel, GAME_SCREEN);
		gameRunning = true;
	}
	
}
