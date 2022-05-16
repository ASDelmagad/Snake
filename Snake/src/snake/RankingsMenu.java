package snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class RankingsMenu extends Menu
{
    private int menuPage = 0;
    private int previousPlayerOption = this.playerOption;
    private Rankings rankings;

    public RankingsMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.rankings = this.game.getRankings();

        this.menuOptions = new String[]{
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            this.game.getText("STEPBACK")
        };
    }

    public RankingsMenu(Game game)
    {
        this(game, null);
    }

    /**
     * Handles menu draw
     * @param g2 the graphics used for drawing
     */
    @Override
    public void draw(Graphics2D g2)
    {
        // Bold and italic green Snake title
        g2.setColor(Color.green);
        g2.setFont(new Font("Serif", Font.ITALIC, 55));
        g2.drawString("SNAKE", 15, 70);

        refreshMenuOptions(); // So other page is being shwown

        for(int i = getMenuState(); i < getMenuState()+7 && i < rankings.size(); i++)
        {
            this.addVisibleOption(g2, i-getMenuState(), i-getMenuState());
        }

        this.addVisibleOption(g2, 7, 8, "                              " + this.game.getText("NEXTPAGE"));
        this.addVisibleOption(g2, 8, 10);

        if(this.ignorePressedKeys)
        {
            if(this.playerOption == 5)
            {
                this.addVisibleArrow(g2, 8, menuPage == 0 ? false:true, getMenuState()+7 < rankings.size() ? true:false);
            }
        }
    }

    /**
     * Refreshes page
     */
    private void refreshMenuOptions()
    {
        int l;

        for(int i = getMenuState(); i < getMenuState()+7; i++)
        {
            l = i-getMenuState();

            if(i >= rankings.size())
            {
                this.menuOptions[l] = "";
                continue;
            }

            this.menuOptions[l] = rankings.getRank(l).toString();
        }
    }

    /**
     * Handles pressed keys
     */
    @Override
    public void keyPressed(int keyCode)
    {
        if(!this.ignorePressedKeys)
        {
            super.keyPressed(keyCode);

            
            if(this.playerOption < 7 && !(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER))
            {
                if(getMenuState()+this.playerOption >= rankings.size())
                    if(previousPlayerOption != 7)
                        this.playerOption = 7;
                    else if(rankings.size() == 0)
                        this.playerOption = 8;
                    else
                        this.playerOption = getMenuState()+this.playerOption-rankings.size()-1;
            }

            this.previousPlayerOption = this.playerOption;
            return;
        }
    
        if(this.playerOption == 7)
        {
            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(menuPage > 0)
                    menuPage--;
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(getMenuState()+7 < rankings.size())
                    menuPage++;
        }

        if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE)
            this.handleMenuOption();
        
        this.previousPlayerOption = this.playerOption;
    }

    /**
     * Handles chosen menu option
     */
    @Override
    public void handleMenuOption()
    {
        // Finished page selection
        if(this.ignorePressedKeys)
        {
            this.setIgnorePressedKeys(false);
            return;
        }

        // Start game
        if(this.playerOption == 7)
        {
            this.ignorePressedKeys = true;
        }

        if(this.playerOption == 8)
            this.game.setMenu(this.previousMenu);
    }

    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Returns menu state. menuPage*items per page
     */
    private int getMenuState()
    {
        return menuPage*7;
    }
}
