package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import snake.GamePanel;

class GamePanelTest2 {

	@Test
	static void test()
	{
		JFrame jframe = new JFrame();
		GamePanel gamePanel = new GamePanel(jframe);

		gamePanel.setTileQuantity(56);

		assertEquals(56, gamePanel.getTileQuantity());
	}

}
