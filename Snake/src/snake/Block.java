package snake;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Block implements Serializable
{
    protected GameMap map;
    protected int coordX;
    protected int coordY;
    protected boolean isWall = false;

    protected int blockSize;

    public Block(GameMap map, int x, int y)
    {
        this.map = map;
        this.coordX = x;
        this.coordY = y;

        this.blockSize = map.game.gamePanel.getTileSize();
    }

    /**
     * Returns true if the Block is a Wall object
     * @return isWall
     */
    public boolean isWall()     {return isWall;}

    public abstract void hit(Head head, GameMap map);
    public abstract void draw(Graphics2D g2, int coordX, int coordY);
    public abstract void update(long millisecs);
}
