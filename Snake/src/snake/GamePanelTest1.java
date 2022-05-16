package snake;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import org.junit.Test;

public class GamePanelTest1 {

	@Test
	public static void test()
	{
		JFrame jframe = new JFrame();
		GamePanel gamePanel = new GamePanel(jframe);

		gamePanel.setExtraWidth(20);

		assertEquals(20, 20);
	}

}
