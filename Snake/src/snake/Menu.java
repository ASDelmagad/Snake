package snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public abstract class Menu
{
    protected String[] menuOptions; // An array for menu options
    protected int playerOption;

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
        final int offset_ysize = 100; // The y coordinate to start drawing options from
        final int offset_ycorrector = 16; // The rectangle and menuoptions are at different positions. With this size we can correct it.

        // Bold and italic green Snake title
        g2.setColor(Color.green);
        g2.setFont(new Font("Serif", Font.ITALIC, 55));
        g2.drawString("SNAKE", 15, 70);

        for(int i = 0; i < menuOptions.length; i++)
        {
            if(playerOption == i) // Background for selected menu, and black color for text
            {
                g2.setColor(new Color(194, 194, 194));
                g2.fillRect(28, offset_ysize+23*i, 200, 20); // x_start, y_start, x_size, y_size
                g2.setColor(Color.black);
            }
            else
            {
                g2.setColor(Color.white);
            }

            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 15));
            g2.drawString(menuOptions[i], 28, offset_ycorrector+offset_ysize+23*i);
        }
    }

    /**
     * Handle pressed key events, gets from Game object
     * @param keyCode
     */
    public void keyPressed(int keyCode)
    {
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
    }

    /**
     * Subtracts 1 from playerOption int. If it gets under 0, it's set to menuOptions' length-1.
     */
    public void previousPlayerOption()
    {
        this.playerOption--;

        if(this.playerOption < 0)
            this.playerOption = this.menuOptions.length - 1;
    }
}
