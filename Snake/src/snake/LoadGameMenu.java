package snake;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

public class LoadGameMenu extends Menu
{
    ArrayList<File> loadableMaps = new ArrayList<File>();

    public LoadGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        loadableMaps = this.game.getLoadableMaps();

        this.menuOptions = new String[]{
        };
    }

    public LoadGameMenu(Game game)
    {
        this(game, null);
    }

    /**
     * -
     * Draw the load game menutype
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2)
    {
        // Bold and italic green Snake title
        g2.setColor(Color.green);
        g2.setFont(new Font("Serif", Font.ITALIC, 55));
        g2.drawString("SNAKE", 15, 70);

        this.addVisibleOption(g2, 0, 0);
        this.addVisibleOption(g2, 1, 1);
        this.addVisibleOption(g2, 2, 2);
        this.addVisibleOption(g2, 3, 4);
        this.addVisibleOption(g2, 4, 7);

        if(this.ignorePressedKeys)
        {
            if(menuTextKey == 1)
            {
                this.addVisibleArrow(g2, 1, optionKey[0] == 0 ? false:true, optionKey[0] == this.game.mapSizes.length - 1 ? false:true);
            }
            if(menuTextKey == 2)
            {
                this.addVisibleArrow(g2, 2, optionKey[1] == 0 ? false:true, optionKey[1] == this.game.difficulties.length - 1 ? false:true);
            }
        }
    }

    @Override
    public void handleMenuOption() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }

}
