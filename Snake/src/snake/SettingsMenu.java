package snake;

public class SettingsMenu extends Menu
{
    public SettingsMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
        };
    }

    public SettingsMenu(Game game)
    {
        this(game, null);
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
