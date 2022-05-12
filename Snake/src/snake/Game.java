package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Game
{
    protected final int[] mapSizes = new int[]{8, 16, 24, 32, 40};
    protected final String[] difficulties = new String[]{"Könnyű", "Normál", "Nehéz"};
    protected Map<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>(){{
        put("tail_up", getImage("/textures/tail_up.png"));
        put("tail_right", getImage("/textures/tail_right.png"));
        put("tail_left", getImage("/textures/tail_left.png"));
        put("tail_down", getImage("/textures/tail_down.png"));

        put("head_up", getImage("/textures/head_up.png"));
        put("head_right", getImage("/textures/head_right.png"));
        put("head_left", getImage("/textures/head_left.png"));
        put("head_down", getImage("/textures/head_down.png"));

        put("body_vertical", getImage("/textures/body_vertical.png"));
        put("body_topright", getImage("/textures/body_topright.png"));
        put("body_topleft", getImage("/textures/body_topleft.png"));
        put("body_horizontal", getImage("/textures/body_horizontal.png"));
        put("body_bottomright", getImage("/textures/body_bottomright.png"));
        put("body_bottomleft", getImage("/textures/body_bottomleft.png"));

        put("grass", getImage("/textures/grass.png"));
        put("tree", getImage("/textures/tree.png"));
        put("apple", getImage("/textures/apple.png"));
    }};// Szöveget UTF-8 kódolásban ideírni

    protected GamePanel gamePanel;
    private SoundPlayer soundPlayer;
    private FileManager fileManager;
    private Settings settings;

    private Menu displayMenu; // The Menu object being displayed

    public Game(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        this.soundPlayer = new SoundPlayer();

        this.fileManager = new FileManager();
        this.settings = fileManager.loadSettings();

        this.displayMenu = new MainMenu(this);

        this.soundPlayer.playMusic("/sound/background_music.wav"); // Eric Skiff - A Night Of Dizzy Spells ♫ NO COPYRIGHT 8-bit Music + Background
    }

    // - - - - - [Game Functions] - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * To be called from a menu, with a predefined difficulty and mapsize
     * @param menu
     * @param mapDifficulty
     * @param mapSize
     */
    public void startGame(Menu menu, String mapDifficulty, int mapSize)
    {
        String playerName = (String)this.settings.getSetting("playerName");

        GameMap gameMap = new GameMap(this, menu, playerName);
        gameMap.generateMap(mapDifficulty, mapSize);

        this.setMenu(gameMap);
    }
    /**
     * This function is called in every frame of the game to calculate new values
     */
    public void update(long milisecs)
    {
        if(displayMenu.getIsMap())
        {
            GameMap gameMap = (GameMap)displayMenu;

            if(gameMap.getMapSize() != this.gamePanel.getTileQuantity())
            {
                this.gamePanel.setTileQuantity(gameMap.getMapSize());
            }

            if(this.gamePanel.getExtraWidth() == 0)
            {
                this.gamePanel.setExtraWidth(40); // For status bar
            }
        }
        else
        {
            if(this.gamePanel.getTileQuantity() != this.gamePanel.coreTileQuantity)
                this.gamePanel.setTileQuantity(this.gamePanel.coreTileQuantity);

            if(this.gamePanel.getExtraWidth() != 0)
                this.gamePanel.setExtraWidth(0);
        }

        this.displayMenu.update(milisecs);
    }

    /**
     * This function is called in every frame of the game to repaint the game
     */
    public void repaint()
    {
        this.gamePanel.repaint();
    }

    public void saveMap(GameMap map)
    {
        Date date = new Date();
        this.fileManager.saveMap(map, map.getPlayerName() + "_" + date.getTime());
    }

    /**
     * This function sets the current menu to a new one
     * @param menu
     */
    public void setMenu(Menu menu)
    {
        this.displayMenu = menu;
    }

    /**
     * This function is called in every frame with the components being repainted. g2 is the component.
     * @param g2
     */
    public void paintComponent(Graphics2D g2)
    {
        displayMenu.draw(g2);
    }

    /**
     * Plays a sound from the sound folder with the given filename
     * @param fileName
     */
    public void playSound(String fileName)
    {
        this.soundPlayer.playSound("/sound/" + fileName);
    }

    /**
     * Sets the settings object's key's variable to value if available
     * @param key
     * @param value
     */
    public void setSetting(String key, Object value)
    {
        settings.setSetting(key, value);
    }

    /**
     * Gets setings object's key's variable if available
     * @param key
     * @return object, if available. null otherwise
     */
    public Object getSetting(String key)
    {
        return settings.getSetting(key);
    }
    /**
    * Gets setings object's key's variable if available. Handle errors with string conversion and modifies the text so it's more logical
    * @param key
    * @return string, if available. null otherwise
    */
   public String getSettingString(String key)
   {
       return settings.getSettingString(key);
   }

   /**
    * Returns a BufferedImage object identified by its path
    * @param imageSource
    * @return the Buffered Image
    */
   public BufferedImage getImage(String imageSource)
   {
       BufferedImage bufferedImage = null;

       try
       {
           bufferedImage = ImageIO.read(getClass().getResourceAsStream(imageSource));
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }

       return bufferedImage;
   }

   /**
    * Return a given string as a utf 8 coded string
    * @param string
    * @return the utf8 coded string
    */
   public String toUTF8(String string)
   {
       byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

       return new String(bytes, StandardCharsets.UTF_8);
   }

    // - - - - - [Key Functions] - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    /**
     * This function gets called when keys are typed by gamepanel
     * @param key
     */
    public void keyTyped(char key)
    {
        displayMenu.keyTyped(key);
    }
    
    /**
     * This function gets called when keys are pressed by gamepanel
     * @param key
     */
    public void keyPressed(int keyCode)
    {
        displayMenu.keyPressed(keyCode);
    }

    /**
     * This function gets called when keys are released by gamepanel
     * @param key
     */
    public void keyReleased(int keyCode)
    {
        displayMenu.keyReleased(keyCode);
    }
}
