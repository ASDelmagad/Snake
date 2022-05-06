package snake;

import java.awt.Graphics2D;

public class Game
{
    private GamePanel gamePanel;

    public Game(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
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
     * This function is called in every frame with the components being repainted. g2 is the component.
     * @param g2
     */
    public void paintComponent(Graphics2D g2)
    {
        
    }

    // - - - - - [Key Functions] - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void keyTyped(char key)
    {
        
    }
    
    public void keyPressed(int keyCode)
    {

    }

    public void keyReleased(int keyCode)
    {

    }
}
