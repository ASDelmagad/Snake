package snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings
{
    Map<String, Object> settings = new HashMap<String, Object>(){{
        // Sound Settings
        put("backgroundVolume", (float)0.3);
        put("effectsVolume", (float)0.8);

        // New Game Settings
        put("playerName", "");
        put("mapSize", new ArrayList<Integer>(){{ add(16); add(16); }});
        put("difficulty", "");
    }};
}
