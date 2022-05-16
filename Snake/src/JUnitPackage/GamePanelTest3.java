package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import snake.GamePanel;

class GamePanelTest3 {

	@Test
	static void test()
	{
		JFrame jframe = new JFrame();
		GamePanel gamePanel = new GamePanel(jframe);

		gamePanel.setBackground(Color.CYAN);

		assertEquals(Color.CYAN, gamePanel.getBackground());
	}

}
