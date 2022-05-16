package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class NewGameMenu extends Menu
{
    final int menuTextMaxLength = 20;

    private String menuText = "";
    private int menuTextKey;
    private int[] optionKey = new int[2]; // This is used for the mapsize line, identifiing the mapsize with this integer.

    @SuppressWarnings("unchecked")
    public NewGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
            this.game.getText("NEWM_PLAYERNAME"),
            this.game.getText("NEWM_MAPSIZE"),
            this.game.getText("NEWM_DIFFICULTY"),
            this.game.getText("NEWM_STARTGAME"),
            this.game.getText("STEPBACK")
        };

        ArrayList<Integer> mapSize = (ArrayList<Integer>)this.game.getSetting("mapSize");

        // Setting mapsize optionkey to current set mapsize key
        for(int i = 0; i < game.mapSizes.length; i++)
        {
            if(game.mapSizes[i] == mapSize.get(0))
            {
                optionKey[0] = i;
                break;
            }
        }

        // Setting difficulty optionkey to current set difficulty key
        for(int i = 0; i < game.difficulties.length; i++)
        {
            if(game.difficulties[i].equals(this.game.getSettingString("difficulty")))
            {
                optionKey[1] = i;
                break;
            }
        }
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

        this.addVisibleOption(g2, 0, 0, (String)this.game.getSettingString("playerName"));
        this.addVisibleOption(g2, 1, 1, (String)this.game.getSettingString("mapSize"));
        this.addVisibleOption(g2, 2, 2, (String)this.game.getSettingString("difficulty"));
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
    @SuppressWarnings("unchecked")
    public void handleMenuOption()
    {
        // Finished with writing text
        if(this.ignorePressedKeys)
        {
            this.setIgnorePressedKeys(false);
            return;
        }
    
        // Starts writing text
        if(this.playerOption < 3)
        {
            if(this.playerOption == 0)
                menuText = "";

            menuTextKey = playerOption;
            this.ignorePressedKeys = true;
            return;
        }

        // Start game
        if(this.playerOption == 3)
        {
            ArrayList<Integer> mapSizeSetting = (ArrayList<Integer>)this.game.getSetting("mapSize");
            
            this.game.startGame(this, this.game.getSettingString("difficulty"), mapSizeSetting.get(0));
        }

        if(this.playerOption == 4)
            this.game.setMenu(this.previousMenu);
    }

    @Override
    public void keyPressed(int keyCode)
    {
        if(!this.ignorePressedKeys)
        {
            super.keyPressed(keyCode);
            return;
        }

        if(menuTextKey == 1)
        {
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(optionKey[0] > 0)
                    optionKey[0]--;
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(optionKey[0] < this.game.mapSizes.length-1)
                    optionKey[0]++;

            ArrayList<Integer> mapSize = new ArrayList<Integer>();

            mapSize.add(this.game.mapSizes[optionKey[0]]);
            mapSize.add(this.game.mapSizes[optionKey[0]]);

            this.game.setSetting("mapSize", mapSize);
        }

        if(menuTextKey == 2)
        {
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(optionKey[1] > 0)
                    optionKey[1]--;
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(optionKey[1] < this.game.difficulties.length-1)
                    optionKey[1]++;

            this.game.setSetting("difficulty", this.game.difficulties[optionKey[1]]);
        }

        if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE)
            this.handleMenuOption();
    }

    @Override
    public void keyTyped(char key)
    {
        if(!this.ignorePressedKeys || menuTextKey != 0) // If exceeds maximum size or it's mapsize option or it's difficulty option
            return;
        
        if(menuText.length() >= menuTextMaxLength)
            return;

        menuText += key;
        this.game.setSetting("playerName", menuText);
    }
}
