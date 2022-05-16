package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import snake.Settings;

class SettingsTest3 {

	@Test
	void test()
	{
		Settings settings = new Settings();

		settings.setSetting("mapSize", new ArrayList<Integer>(){{ add(24); add(24); }});

		assertEquals("24x24", settings.getSettingString("mapSize"));
	}

}
