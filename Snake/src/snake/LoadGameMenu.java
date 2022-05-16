package snake;

import java.util.ArrayList;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class LoadGameMenu extends Menu
{
    ArrayList<GameMap> loadableMaps = new ArrayList<GameMap>();
    int menuPage = 0;
    int previousPlayerOption = this.playerOption;

    public LoadGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        loadableMaps = this.game.getLoadableMaps();

        //BUGFIX - Loaded maps had not loadable menus in them
        for(int i = 0; i < loadableMaps.size(); i++)
        {
            loadableMaps.get(i).previousMenu = this.previousMenu;
        }

        this.menuOptions = new String[]{
            "",
            "",
            "",
            "",
            "",
            "",
            this.game.getText("STEPBACK")
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

        refreshMenuOptions(); // So other page is being shwown

        for(int i = getMenuState(); i < getMenuState()+5 && i < loadableMaps.size(); i++)
        {
            this.addVisibleOption(g2, i-getMenuState(), i-getMenuState());
        }

        this.addVisibleOption(g2, 5, 6, "                              " + this.game.getText("NEXTPAGE"));
        this.addVisibleOption(g2, 6, 8);

        if(this.ignorePressedKeys)
        {
            if(this.playerOption == 5)
            {
                this.addVisibleArrow(g2, 6, menuPage == 0 ? false:true, getMenuState()+5 < loadableMaps.size() ? true:false);
            }
        }
    }

    private void refreshMenuOptions()
    {
        int l;

        for(int i = getMenuState(); i < getMenuState()+5; i++)
        {
            l = i-getMenuState();

            if(i >= loadableMaps.size())
            {
                this.menuOptions[l] = "";
                continue;
            }

            this.menuOptions[l] = loadableMaps.get(i).toString();
        }
    }

    @Override
    public void keyPressed(int keyCode)
    {
        if(!this.ignorePressedKeys)
        {
            super.keyPressed(keyCode);

            
            if(this.playerOption < 5 && !(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER))
            {
                if(getMenuState()+this.playerOption >= loadableMaps.size())
                    if(previousPlayerOption != 5)
                        this.playerOption = 5;
                    else if(loadableMaps.size() == 0)
                        this.playerOption = 6;
                    else
                        this.playerOption = getMenuState()+this.playerOption-loadableMaps.size()-1;
            }

            this.previousPlayerOption = this.playerOption;
            return;
        }
    
        if(this.playerOption == 5)
        {
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(menuPage > 0)
                    menuPage--;
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(getMenuState()+5 < loadableMaps.size())
                    menuPage++;
        }

        if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE)
            this.handleMenuOption();
        
        this.previousPlayerOption = this.playerOption;
    }

    @Override
    public void handleMenuOption()
    {
        // Finished page selection
        if(this.ignorePressedKeys)
        {
            this.setIgnorePressedKeys(false);
            return;
        }
    
        // Load chosen map
        if(this.playerOption < 5)
        {
            this.game.loadMap(loadableMaps.get(getMenuState() + this.playerOption));
            return;
        }

        // Start game
        if(this.playerOption == 5)
        {
            this.ignorePressedKeys = true;
        }

        if(this.playerOption == 6)
            this.game.setMenu(this.previousMenu);
    }


    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }

    private int getMenuState()
    {
        return menuPage*5;
    }
}
