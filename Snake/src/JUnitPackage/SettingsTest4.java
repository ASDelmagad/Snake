package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import snake.Settings;

class SettingsTest4 {

	@Test
	void test()
	{
		Settings settings = new Settings();

		settings.setSetting("playerName", "JánosÚr");

		assertEquals("JánosÚr", settings.getSetting("playerName"));
	}

}
