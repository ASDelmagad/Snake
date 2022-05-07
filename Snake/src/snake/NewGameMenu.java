package snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class NewGameMenu extends Menu
{
    public NewGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
            "Játékosnév: ",
            "Pályaméret: ",
            "Nehézség: ",
            "Játék Kezdése",
            "Visszalépés"
        };
    }

    public NewGameMenu(Game game)
    {
        this(game, null);
    }

    /**
     * -
     * Draw the new game menutype
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2)
    {
        // Bold and italic green Snake title
        g2.setColor(Color.green);
        g2.setFont(new Font("Serif", Font.ITALIC, 55));
        g2.drawString("SNAKE", 15, 70);

        for(int i = 0; i < 3; i++)
        {
            this.addVisibleOption(g2, i, i);
        }

        this.addVisibleOption(g2, 3, 4);
        this.addVisibleOption(g2, 4, 7);
    }

    @Override
    public void handleMenuOption()
    {
        if(this.ignorePressedKeys)
            this.setIgnorePressedKeys(false);
    
        if(this.playerOption < 3)
        {
            this.ignorePressedKeys = true;
        }

        if(this.playerOption == 4)
        {
            // START GAME
        }

        if(this.playerOption == 5)
            this.game.setMenu(this.previousMenu);
    }

    @Override
    public void keyTyped(char key)
    {
        
    }

}
