package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Head extends Cell
{
    final long startSpeed = 400;
    final long endSpeed = 100;

    private long currentSpeed = startSpeed;
    private long lastFrameTime = 0;

    private String snakeDirection = "left";
    private String animatedDirection = snakeDirection;

    public Head(GameMap map, int x, int y)
    {
        super(map, x, y, "left", "left");

        this.map.snake.clear();
        this.map.snake.add(this); // Add head to snake
    }

    @Override
    public void draw(Graphics2D g2, int coordX, int coordY)
    {
        int tileSize = this.map.getTilesize();

        g2.drawImage(this.map.game.imageMap.get("grass"), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
        g2.drawImage(this.map.game.imageMap.get("head_"+animatedDirection), coordX*tileSize, coordY*tileSize, tileSize, tileSize, null);
    }

    @Override
    public void update(long milisecs)
    {
        if(this.map.getGameEnd())
            return;

        if(this.map.getGamePause())
            return;

        if(lastFrameTime+currentSpeed > milisecs)
            return;
        
        lastFrameTime = milisecs;

        if(endSpeed < currentSpeed)
            currentSpeed -= 2;

        step(snakeDirection);
    }

    public void step(String direction)
    {
        int cellCoordX = this.coordX;
        int cellCoordY = this.coordY;

        int newCoordX = this.coordX;
        int newCoordY = this.coordY;

        switch(direction)
        {
            case "up":
            {
                newCoordY--;

                if(newCoordY < 0)
                    newCoordY = this.map.getMapSize() - 1;
                
                break;
            }
            case "down":
            {
                newCoordY++;

                if(newCoordY >= this.map.getMapSize())
                    newCoordY = 0;
                
                break;
            }
            case "left":
            {
                newCoordX--;

                if(newCoordX < 0)
                    newCoordX = this.map.getMapSize() - 1;
                
                break;
            }
            case "right":
            {
                newCoordX++;

                if(newCoordX >= this.map.getMapSize())
                    newCoordX = 0;
                
                break;
            }
        }

        this.map.hit(this, newCoordX, newCoordY, this.map);

        Cell cell;

        for(int i = 0; i < this.map.snake.size(); i++)
        {
            cell = this.map.snake.get(i);

            cell.setLife(cell.getLife()-1);

            if(cell.getLife() <= 0)
            {
                cell.cellDie();
            }
        }

        this.animatedDirection = direction;

        this.coordX = newCoordX;
        this.coordY = newCoordY;

        this.map.setBlock(this); // Sets block by this objects x and y coordinate

        this.map.snakeAddCell(cellCoordX, cellCoordY, direction);
    }

    //Head cell cannot die!
    @Override
    public void cellDie()
    {
        return;
    }

    /**
     * Gets called when the head hits the wrong block
     */
    public void die()
    {
        this.map.setGameEnd(true);;
    }

    /**
     * Handles keyPressed event called by the gamemap object
     */
    public void keyPressed(int keyCode)
    {
        switch(keyCode)
        {
            case KeyEvent.VK_UP:        {if(!this.animatedDirection.equals("down")) this.setDirection("up"); break;}
            case KeyEvent.VK_DOWN:      {if(!this.animatedDirection.equals("up")) this.setDirection("down"); break;}
            case KeyEvent.VK_LEFT:      {if(!this.animatedDirection.equals("right")) this.setDirection("left"); break;}
            case KeyEvent.VK_RIGHT:     {if(!this.animatedDirection.equals("left")) this.setDirection("right"); break;}
        }
    }

    /**
     * Returns speed value from 0.0 to 1.0
     * @return speed
     */
    public float getSpeedValue()
    {
        return (float)(1 - (float)(currentSpeed-endSpeed)/(float)(startSpeed-endSpeed));
    }

    /**
     * Sets head direction. It can be "left", "right", "up" or "down"
     * @param direction
     */
    public void setDirection(String direction)
    {
        this.snakeDirection = direction;
    }
}
