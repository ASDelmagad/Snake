package snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Settings
{
    private Map<String, Object> settings = new HashMap<String, Object>(){{
        // Sound Settings
        put("backgroundVolume", (float)0.3);
        put("effectsVolume", (float)0.8);

        // New Game Settings
        put("playerName", "Játékos");
        put("mapSize", new ArrayList<Integer>(){{ add(16); add(16); }});
        put("difficulty", "Könnyű");
    }};

    /**
     * Returns the keyset of this object
     * @return settings.keySet() 
     * */
    public Set<String> getSettingKeys()
    {
        return settings.keySet();
    }

    /**
     * If key is available, return the value connected to it. null otherwise
     * @param key
     * @return key data if available, null otherwise
     */
    public Object getSetting(String key)
    {
        if(this.settings.containsKey(key))
            return this.settings.get(key);

        return null;
    }

    /**
     * If key is available, return the string value connected to it. null otherwise
     * This function handles value to string conversion that normally are not able to be handled.
     * @param key
     * @return key data if available, null otherwise
     */
    @SuppressWarnings("unchecked")
    public String getSettingString(String key)
    {
        if(this.settings.containsKey(key))
        {
            // Handle mapsize text value
            if(key.equals("mapSize"))
            {
                ArrayList<Integer> coordinates = (ArrayList<Integer>)this.settings.get(key);
                return Integer.toString(coordinates.get(0)) + "x" + Integer.toString(coordinates.get(1));
            }

            return this.settings.get(key).toString();
        }

        return null;
    }

    /**
     * Return settings variable
     * @return settings
     */
    public HashMap<String, Object> getSettings()
    {
        return (HashMap<String, Object>)settings;
    }

    /**
     * Sets the available settings in settings param to this object's settings
     * @param settings
     */
    public void setSettings(HashMap<String, Object> settings)
    {
        for(String key : settings.keySet())
        {
            this.setSetting(key, settings.get(key));
        }
    }

    /**
     * Sets value to the key key in settings variable if key is available
     * @param key
     * @param value
     */
    public void setSetting(String key, Object value)
    {
        if(this.settings.containsKey(key))
            this.settings.put(key, value);
    }
}
