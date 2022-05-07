package snake;

public class NewGameMenu extends Menu
{
    public NewGameMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
        };
    }

    public NewGameMenu(Game game)
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
