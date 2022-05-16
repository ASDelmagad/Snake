package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import snake.Settings;

class SettingsTest1 {

	@Test
	void test()
	{
		Settings settings = new Settings();

		settings.setSetting("backgroundVolume", (float)0.12);

		assertEquals((float)0.12, (float)settings.getSetting("backgroundVolume"));
	}

}
