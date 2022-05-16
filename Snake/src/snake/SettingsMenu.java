package snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class SettingsMenu extends Menu
{
    public SettingsMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
            this.game.getText("SETTINGSM_MUSIC"),
            this.game.getText("SETTINGSM_SOUND"),
            this.game.getText("STEPBACK")
        };
    }

    public SettingsMenu(Game game)
    {
        this(game, null);
    }

    /**
     * Handling menu graphics
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
        this.addVisibleOption(g2, 2, 3);

        float backgroundVolume = this.game.getMusicVolume();
        float soundVolume = this.game.getEffectsVolume();

        g2.fillRect(140, 102, 5*(int)(backgroundVolume*30), 15);
        g2.fillRect(140, 124, 5*(int)(soundVolume*30), 15);

        if(this.ignorePressedKeys)
        {
            if(playerOption == 0)
            {
                this.addVisibleArrow(g2, 0, backgroundVolume > 0.0 ? true:false, backgroundVolume < 1.0 ? true:false);
            }
            if(playerOption == 1)
            {
                this.addVisibleArrow(g2, 1, soundVolume > 0.0 ? true:false, soundVolume < 1.0 ? true:false);
            }
        }
    }

    /**
     * Handles chosen menu options while in this menu
     */
    @Override
    public void handleMenuOption()
    {
        // Finished with writing text
        if(this.ignorePressedKeys)
        {
            this.setIgnorePressedKeys(false);
            return;
        }
    
        // Starts writing text
        if(this.playerOption < 2)
        {
            this.ignorePressedKeys = true;
            return;
        }

        if(this.playerOption == 2)
            this.game.setMenu(this.previousMenu);
    }

    /**
     * Handles pressed keys while in this menu
     */
    @Override
    public void keyPressed(int keyCode)
    {
        if(!this.ignorePressedKeys)
        {
            super.keyPressed(keyCode);
            return;
        }

        if(playerOption == 0)
        {
            float musicVolume = this.game.getMusicVolume();

            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(musicVolume > (float)0.0)
                    this.game.setMusicVolume(musicVolume-(float)0.01);
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(musicVolume < (float)1.0)
                    this.game.setMusicVolume(musicVolume+(float)0.01);
            
            this.game.setSetting("backgroundVolume", this.game.getMusicVolume());
        }

        if(playerOption == 1)
        {
            float soundVolume = this.game.getEffectsVolume();

            if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
                if(soundVolume > (float)0.0)
                    this.game.setEffectsVolume(soundVolume-(float)0.01);
            
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
                if(soundVolume < (float)1.0)
                    this.game.setEffectsVolume(soundVolume+(float)0.01);
            
            this.game.setSetting("effectsVolume", this.game.getEffectsVolume());
        }

        if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE)
            this.handleMenuOption();
    }

    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }
}
