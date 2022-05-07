package snake;

public class RankingsMenu extends Menu
{
    public RankingsMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
        };
    }

    public RankingsMenu(Game game)
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
