package snake;

import javax.swing.JFrame;

public class Main {

	/**
	 * Opens a JFrame window, creates a GamePanel object. Starts the game!
	 * - On close close the window
	 * - Not resizable
	 * - Title is Snake
	 * 
	 * @param args Default input option
	 */
	public static void main(String[] args)
	{
		// Create JFrame window for the game
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Snake");

		GamePanel gamePanel = new GamePanel(); // Create gamepanel game view
		window.add(gamePanel); // Add gamepanel to window

		window.pack(); // Fits the window to gamePanel

		window.setLocationRelativeTo(null); // Puts the window into the middle of the display
		window.setVisible(true); // Window to be visible

		gamePanel.startGameThread(); // Start game finally
	}
}
