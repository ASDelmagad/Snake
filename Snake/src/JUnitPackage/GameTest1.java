package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;


import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import snake.Game;
import snake.GamePanel;
import snake.MainMenu;

class GameTest1 {

	@Test
	static void test()
	{
		JFrame jframe = new JFrame();
		GamePanel gamePanel = new GamePanel(jframe);
		Game game = new Game(gamePanel);
		MainMenu mainMenu = new MainMenu(game);

		game.setMenu(mainMenu);

		assertEquals(mainMenu, game.getDisplayMenu());
	}

}
