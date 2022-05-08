package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    /**
     * Need to add to this the key you want to use in binding.
     */
    private Set<Integer> keysToHandle = new HashSet<Integer>() {{
        add(KeyEvent.VK_A); // Snake/Menu left/up
        add(KeyEvent.VK_W); // Snake/Menu up
        add(KeyEvent.VK_S); // Snake/Menu down
        add(KeyEvent.VK_D); // Snake/Menu right/down
        add(KeyEvent.VK_LEFT); // Snake/Menu left/up
        add(KeyEvent.VK_UP); // Snake/Menu up
        add(KeyEvent.VK_DOWN); // Snake/Menu down
        add(KeyEvent.VK_RIGHT); // Snake/Menu right/down

        add(KeyEvent.VK_P); // Pause ingame

        add(KeyEvent.VK_ENTER); // Enter for menu choosing
        add(KeyEvent.VK_SPACE); // Space for menu choosing
    }};
    final int tileSize = 24; //Size of a block
    final int coreTileQuantity = 16;
    final Game game;;

    private int tileQuantity = coreTileQuantity; // How much blocks are there in a row/column

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
    public void keyTyped(KeyEvent e) // typed uses keyChar insted of keyCode
    {
        game.keyTyped(e.getKeyChar());
    }

    /**
     * If e is a key that should be handled, this function calls game's connection function with it.
     * Ignored otherwise.
     * @param e
     */
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if(keysToHandle.contains(keyCode))
            game.keyPressed(keyCode);
    }

    /**
     * If e is a key that should be handled, this function calls game's connection function with it.
     * Ignored otherwise.
     * @param e
     */
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if(keysToHandle.contains(keyCode))
            game.keyReleased(keyCode);
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
