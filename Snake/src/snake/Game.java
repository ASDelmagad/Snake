package snake;

import java.awt.Graphics2D;

public class Game
{
    protected final int[] mapSizes = new int[]{8, 16, 24, 32, 40};
    protected final String[] difficulties = new String[]{"Easy", "Normal", "Hard"};

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
     * This function is called in every frame of the game to calculate new values
     */
    public void update()
    {

    }

    /**
     * This function is called in every frame of the game to repaint the game
     */
    public void repaint()
    {
        gamePanel.repaint();
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
