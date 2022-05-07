package snake;

import java.awt.Graphics2D;

public class Game
{
    private GamePanel gamePanel;
    private SoundPlayer soundPlayer;

    private Menu displayMenu; // The Menu object being displayed

    public Game(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        this.soundPlayer = new SoundPlayer();

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

    public void playSound(String fileName)
    {
        this.soundPlayer.playSound("/sound/" + fileName);
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
