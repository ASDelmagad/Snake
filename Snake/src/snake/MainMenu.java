package snake;

public class MainMenu extends Menu
{
    public MainMenu(Game game, Menu previousMenu)
    {
        super(game, previousMenu);

        this.menuOptions = new String[]{
            this.game.toUTF8("Új Játék"),             // 0
            "Játék Betöltése",      // 1
            "Ranglista",            // 2
            "Beállítások",          // 3
            "Kilépés"               // 4
        };
    }

    public MainMenu(Game game)
    {
        this(game, null);
    }

    @Override
    public void handleMenuOption()
    {
        switch(this.playerOption)
        {
            case 0:     {this.game.setMenu(new NewGameMenu(this.game, this)); break;}
            case 1:     {this.game.setMenu(new LoadGameMenu(this.game, this)); break;}
            case 2:     {this.game.setMenu(new RankingsMenu(this.game, this)); break;}
            case 3:     {this.game.setMenu(new SettingsMenu(this.game, this)); break;}
            case 4:     {System.exit(0); break;}
        }
    }

    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }
}
