package snake;

import java.awt.Graphics2D;

public class Void extends Block
{

    public Void(GameMap map, int x, int y) {
        super(map, x, y);
        //TODO Auto-generated constructor stub
    }

    /**
     * Handles void graphics, draws grass
     */
    @Override
    public void draw(Graphics2D g2, int coordX, int coordY)
    {
        int tileSize = this.map.getTilesize();
        
        g2.drawImage(this.map.game.imageMap.get("grass"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
    }

    @Override
    public void update(long millisecs) {
        // TODO Auto-generated method stub
        
    }

    //Void cannot be hiz
    @Override
    public void hit(Head head, GameMap gameMap)
    {
        return; // Nothing happens
    }

}
