package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    final int tileSize = 48; //Size of a block
    final Game game;;

    private int tileQuantity = 8; // How much blocks are there in a row/column

    KeyHandler keyHandler = new KeyHandler(this); // Creating keyhandler object to handle input
    Thread gameThread; //Thread for starting the game

    public GamePanel()
    {
        game = new Game(this);

        this.setPreferredSize(new Dimension(getScreenSize(), getScreenSize())); // Set gamepanel size
        this.setBackground(Color.black); // Set background to black color
        this.setDoubleBuffered(true); // Offscreen drawing, basically faster gameplay
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // So the keyListener works
    }

    // - - - - - [Functions for GamePanel] - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Start the game!
     */
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start(); // Calls the run function
    }

    /**
     * If e is a key that should be handled, this function calls game's connection function with it.
     * Ignored otherwise.
     * @param e
     */
    public void keyTyped(KeyEvent e)
    {
        // Handle key events
    }

    /**
     * If e is a key that should be handled, this function calls game's connection function with it.
     * Ignored otherwise.
     * @param e
     */
    public void keyPressed(KeyEvent e)
    {
        // Handle key events
    }

    /**
     * If e is a key that should be handled, this function calls game's connection function with it.
     * Ignored otherwise.
     * @param e
     */
    public void keyReleased(KeyEvent e)
    {
        // Handle key events
    }

    // - - - - - [Getter/Setter Functions] - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Return tileSize variable
     * @return tileSize
     */
    public int getTileSize()        {return this.tileSize;}

    /**
     * Return tileQuantity variable
     * @return tileQuantity
     */
    public int getTileQuantity()      {return this.tileQuantity;}

    /**
     * Returns the size of screen in pixels as in tileQuantity*tileSize
     * @param screenSize
     */
    public int getScreenSize()      {return this.tileQuantity*this.tileSize;}

    //------------------------------------

    /**
     * Sets GamePanel's tileQuantity variable to tileQuantity param's value
     * @param tileQuantity
     */
    public void setTileQuantity(int tileQuantity)      {this.tileQuantity = tileQuantity;}

    // - - - - - [Overrided Functions] - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * When game starts this function runs.
     * Basically the loop for the game.
     */
    @Override
    public void run()
    {
        while(gameThread != null) // While the game is running
        {
            game.update(); // Update values

            game.repaint(); // Repaint the frame
        }
    }

    /**
     * This function is called for every component that must be painted in every frame by the epaint() function
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Call JPanel paintComponent

        Graphics2D g2 = (Graphics2D)g; // More functions

        game.paintComponent(g2); // Giving the game object the components to handle them.

        g2.dispose();
    }
}
