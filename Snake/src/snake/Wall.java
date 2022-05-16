package snake;

import java.awt.Graphics2D;

public class Wall extends Block
{

    public Wall(GameMap map, int x, int y) {
        super(map, x, y);
        super.isWall = true;
        //TODO Auto-generated constructor stub
    }

    /**
     * Handles wall graphics, draws grass and tree
     */
    @Override
    public void draw(Graphics2D g2, int coordX, int coordY)
    {
        int tileSize = this.map.getTilesize();

        g2.drawImage(this.map.game.imageMap.get("grass"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
        g2.drawImage(this.map.game.imageMap.get("tree"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
    }

    @Override
    public void update(long millisecs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hit(Head head, GameMap gameMap)
    {
        head.die();
    }

}
