package snake;

import java.awt.Graphics2D;

public class Cell extends Block
{
    private int life;
    private String cellDirection; // Not used by head, because it was inplemented after developing that.
    private String headDirection; // The direction of head when it was created, so we can calculate later moves.

    private boolean isTail = false;
    private String tailDirection;

    public Cell(GameMap map, int x, int y, String direction, String headDirection)
    {
        super(map, x, y);
        this.life = map.snake.size();

        this.cellDirection = direction;
        this.headDirection = headDirection;
    }

    /**
     * Returns life variable
     * @return life
     */
    public int getLife()        {return this.life;}

    /**
     * Returns cellDirection variable
     * @return cellDirection
     */
    public String getCellDirection()    {return this.cellDirection;}

    /**
     * Returns headDirection variable
     * @param headDirection
     */
    public String getHeadDirection()    {return this.headDirection;}

    /**
     * Gets called when the cell should be deleted
     */
    public void cellDie()
    {
        this.map.snake.remove(this);
        map.setBlock(new Void(this.map, this.coordX, this.coordY));
    }

    /**
     * Sets life variable to life param
     * @param life
     */
    public void setLife(int life)
    {
        this.life = life;
    }

    @Override
    public void draw(Graphics2D g2, int coordX, int coordY)
    {
        int tileSize = this.map.getTilesize();

        if(isTail)
        {
            g2.drawImage(this.map.game.imageMap.get("grass"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
            g2.drawImage(this.map.game.imageMap.get("tail_" + this.tailDirection), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
        }
        else
        {
            g2.drawImage(this.map.game.imageMap.get("grass"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
            g2.drawImage(this.map.game.imageMap.get("body_" + this.cellDirection), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
        }
    }

    @Override
    public void update(long millisecs)
    {
        if(isTail)
            return;

        if(this.equals(this.map.snake.get(this.map.snake.size()-1))) // If the last object in snake, then its a tail
        {
            switch(this.headDirection) // The tail points into the opposite direction than the head
            {
                case "left":        {this.tailDirection = "right"; break;}
                case "up":        {this.tailDirection = "down"; break;}
                case "down":        {this.tailDirection = "up"; break;}
                case "right":        {this.tailDirection = "left"; break;}
            }

            this.isTail = true;
        }
    }

    @Override
    public void hit(Head head, GameMap map)
    {
        head.die();
    }

    // Empty because its called in head and head is an extended version of this object.
    public void keyPressed(int keyCode) {
    }
}
