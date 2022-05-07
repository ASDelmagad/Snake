package snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public abstract class Menu
{
    protected String[] menuOptions; // An array for menu options
    protected int playerOption;
    protected Game game;
    protected Menu previousMenu;

    protected boolean ignorePressedKeys = false;

    public Menu(Game game, Menu previousMenu)
    {
        this.game = game;
        this.previousMenu = previousMenu;
    }

    // - - - - - [Menu Methods]
    /**
     * Switch-case for playeroption cases.
     */
    public abstract void handleMenuOption();
    /**
     * KeyTyped events for menus that use them.
     * @param key
     */
    public abstract void keyTyped(char key);

    /**
     * -
     * Draw the default menu type, gets called from Game object
     * @param g2
     */
    public void draw(Graphics2D g2)
    {
        // Bold and italic green Snake title
        g2.setColor(Color.green);
        g2.setFont(new Font("Serif", Font.ITALIC, 55));
        g2.drawString("SNAKE", 15, 70);

        for(int i = 0; i < menuOptions.length; i++)
        {
            this.addVisibleOption(g2, i, i);
        }
    }

    /**
     * Created a menuoption on g2
     * @param g2 the graphics object to create the option on to
     * @param i the id of the menuChoice from list menuOptions
     * @param slot which slot to put into (place on y coordinate)
     */
    protected void addVisibleOption(Graphics2D g2, int i, int slot)
    {
        final int offset_ysize = 100; // The y coordinate to start drawing options from
        final int offset_ycorrector = 16; // The rectangle and menuoptions are at different positions. With this size we can correct it.

        if(playerOption == i) // Background for selected menu, and black color for text
        {
            g2.setColor(new Color(194, 194, 194));
            g2.fillRect(28, offset_ysize+23*slot, 200, 20); // x_start, y_start, x_size, y_size
            g2.setColor(Color.black);
        }
        else
        {
            g2.setColor(Color.white);
        }

        g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 15));
        g2.drawString(menuOptions[i], 28, offset_ycorrector+offset_ysize+23*slot);
    }

    /**
     * Handle pressed key events, gets from Game object
     * @param keyCode
     */
    public void keyPressed(int keyCode)
    {
        if(this.ignorePressedKeys)
        {
            switch(keyCode)
            {
                case KeyEvent.VK_ENTER:         {this.handleMenuOption(); break;} // Handle current choice
                case KeyEvent.VK_SPACE:         {this.handleMenuOption(); break;} // Handle current choice
            }

            return;
        }

        switch(keyCode)
        {
            case KeyEvent.VK_ENTER:         {this.handleMenuOption(); break;} // Handle current choice
            case KeyEvent.VK_SPACE:         {this.handleMenuOption(); break;} // Handle current choice

            case KeyEvent.VK_UP:            {this.previousPlayerOption(); break;} // Go up in menu
            case KeyEvent.VK_W:             {this.previousPlayerOption(); break;} // Go up in menu
            case KeyEvent.VK_A:             {this.previousPlayerOption(); break;} // Go up in menu
            case KeyEvent.VK_LEFT:          {this.previousPlayerOption(); break;} // Go up in menu

            case KeyEvent.VK_DOWN:          {this.nextPlayerOption(); break;} // Go down in menu
            case KeyEvent.VK_S:             {this.nextPlayerOption(); break;} // Go down in menu
            case KeyEvent.VK_D:             {this.nextPlayerOption(); break;} // Go down in menu
            case KeyEvent.VK_RIGHT:         {this.nextPlayerOption(); break;} // Go down in menu
        }
    }

    /**
     * Handle released key events, gets from Game object
     * @param keyCode
     */
    public void keyReleased(int keyCode)
    {
        return; // Released keys are not used here
    }

    // - - - - - [Get/Set Methods]
    /**
     * Returns menuOptions string array
     * @return menuOptions
     */
    public String[] getMenuOptions()        {return this.menuOptions;}

    /**
     * Returns playerOption variable
     * @return playerOption
     */
    public int getPlayerOption()            {return this.playerOption;}

    /**
     * Returns the menu opened before it or null if non had been opened before.
     * @return previousMenu
     */
    public Menu getPreviusMenu()            {return this.previousMenu;}

    /**
     * Returns ignorePressedKeys. true if keypressed are ignore, false if not
     * @return ignorePressedKeys
     */
    public boolean getIgnorePressedKeys()   {return this.ignorePressedKeys;}

    //------------------------------------------------------

    /**
     * Sets playerOption variable. If it's not between zero and the highest menu option id, it's set to 0
     * @param playerOption
     */
    public void setPlayerOption(int playerOption)
    {
        if(playerOption < 0 || playerOption >= menuOptions.length)
        {
            this.playerOption = 0;
            return;
        }

        this.playerOption = playerOption;
    }

    /**
     * Adds 1 to playerOption int. If it exceeds or equals to menuOptions' length, it's set to 0.
     */
    public void nextPlayerOption()
    {
        this.playerOption++;

        if(this.playerOption >= this.menuOptions.length)
            this.playerOption = 0;
        
        this.game.playSound("menu_select.wav");
    }

    /**
     * Subtracts 1 from playerOption int. If it gets under 0, it's set to menuOptions' length-1.
     */
    public void previousPlayerOption()
    {
        this.playerOption--;

        if(this.playerOption < 0)
            this.playerOption = this.menuOptions.length - 1;
        
        this.game.playSound("menu_select.wav");
    }

    /**
     * Sets ignorePressedKeys bollean value to ignorePressedKeys
     * @param ignorePressedKeys
     */
    public void setIgnorePressedKeys(boolean ignorePressedKeys)
    {
        this.ignorePressedKeys = ignorePressedKeys;
    }
}
