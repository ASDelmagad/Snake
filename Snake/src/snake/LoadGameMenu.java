package snake;

public class LoadGameMenu extends Menu
{
    public LoadGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
        };
    }

    public LoadGameMenu(Game game)
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
