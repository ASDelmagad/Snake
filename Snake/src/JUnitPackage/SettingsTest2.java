package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import snake.Settings;

class SettingsTest2 {

	@Test
	void test()
	{
		Settings settings = new Settings();

		settings.setSetting("effectsVolume", (float)0.12);

		assertEquals((float)0.12, (float)settings.getSetting("effectsVolume"));
	}

}
