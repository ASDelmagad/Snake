package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    // - - - - - [Overrided functions]

    /**
     * This function gives gamePanel object the events to handle them
     * e is the event.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        gamePanel.keyPressed(e);
    }

    /**
     * This function gives gamePanel object the events to handle them.
     * e is the event.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        gamePanel.keyReleased(e);
    }

    /**
     * This function gives gamePanel object the events to handle them
     * e is the event.
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        gamePanel.keyTyped(e);
    }
    
}
