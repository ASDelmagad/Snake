package snake;

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GameMap extends Menu
{
    private String mapDifficulty = null;
    private int mapSize = 0;
    private Food food = null;
    private boolean generated = false;
    private boolean gameEnd = false;
    private boolean gamePause = false;
    private int points = 0;
    private String playerName;
    private Date saveDate = new Date();

    public GameMap(Game game, Menu previousMenu, String playerName, String saveTime)
    {
        super(game, previousMenu.getPreviousMenu()); // It's always the mainmenu
        super.isMap = true;
        
        Date date = new Date();

        this.playerName = playerName;
        this.saveDate.setTime(saveTime == "0" ? date.getTime():Long.getLong(saveTime));
    
        this.menuOptions = new String[]{
            "Mentés",
            "Folytatás"
        };
    }

    public GameMap(Game game, Menu previousMenu, String playerName)
    {
        this(game, previousMenu, playerName, "0");
    }

    ArrayList<ArrayList<Block>> map = new ArrayList<ArrayList<Block>>();
    ArrayList<Cell> snake = new ArrayList<Cell>(); // id 0 is head, everything else are cells. Cells are being added in id 1

    /**
     * Generates this map based on the mapType and mapSize
     * Runnable only once
     */
    public void generateMap(String mapDifficulty, int mapSize)
    {
        if(generated)
            return;

        map = new ArrayList<ArrayList<Block>>(); // Flush any possible data
        ArrayList<Block> line;

        for(int i = 0; i < mapSize; i++)
        {
            line = new ArrayList<Block>();
            map.add(line);

            for(int l = 0; l < mapSize; l++)
            {
                line.add(l, new Void(this, l, i));
            }

            map.set(i, line);
        }

        // Create snake head
        line = map.get(mapSize/2);
        line.set(mapSize/2, new Head(this, mapSize/2, mapSize/2));
        map.set(mapSize/2, line);

        int blockAmount = 0;
        int currentBlockAmount = 0;

        switch(mapDifficulty)
        {
            case "Normál":
            {
                blockAmount = (mapSize*mapSize)/8;
            }
            case "Nehéz":
            {
                blockAmount = (mapSize*mapSize)/6;
            }
        }

        Random random = new Random();
        int coordX;
        int coordY;

        while(currentBlockAmount < blockAmount)
        {
            coordX = random.nextInt(mapSize);
            coordY = random.nextInt(mapSize);

            if(map.get(coordY).get(coordX).getClass() != Void.class)
                continue;
            
            if((coordX >= mapSize/2-2 && mapSize/2+2 >= coordX) && (coordY >= mapSize/2-2 && mapSize/2+2 >= coordY))
                continue;
            
            if(countWallsAround(coordX, coordY) >= 1)
                continue;

            line = map.get(coordY);

            line.set(coordX, new Wall(this, coordX, coordY));

            currentBlockAmount++;
        }

        this.mapSize = mapSize;
        this.mapDifficulty = mapDifficulty;

        generated = true;
    }

    /**
     * Forwards draw command with coordinates to all the map blocks
     */
    @Override
    public void draw(Graphics2D g2)
    {
        ArrayList<Block> line;

        for(int i = 0; i < map.size(); i++)
        {
            line = map.get(i);

            for(int l = 0; l < line.size(); l++)
            {
                line.get(l).draw(g2, l, i);
            }
        }

        int middlePoint = this.game.gamePanel.getScreenSize()/2;

        Head snakeHead = (Head)this.snake.get(0);
        float speed = snakeHead.getSpeedValue();

        String printText = String.format(java.util.Locale.US,"Sebesség: %.2f | %s Pont", speed, this.points);
        
        g2.setColor(new Color(230, 230, 230, 255));
        g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 15));
        g2.drawString(printText, middlePoint-85, this.game.gamePanel.getScreenSize() + 25);

        if(this.gameEnd)
        {
            int windowHeight = 120;
            int windowWidth = 360;

            g2.setColor(new Color(40, 40, 40, 210));
            g2.fillRect(middlePoint-(windowWidth/2), middlePoint-(windowHeight/2), windowWidth, windowHeight);

            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 20));
            g2.setColor(new Color(255, 255, 255, 220));
            g2.drawString("JÁTÉK VÉGE", middlePoint-65, middlePoint-(windowHeight/2)+25);

            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));
            g2.setColor(new Color(200, 200, 200, 220));
            g2.drawString(this.playerName, middlePoint-65, middlePoint-(windowHeight/2)+50);
            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));
            g2.setColor(new Color(200, 200, 200, 220));
            g2.drawString("Pontok: " + this.points, middlePoint-65, middlePoint-(windowHeight/2)+70);

            g2.setColor(new Color(194, 194, 194));
            g2.fillRect(middlePoint-67, middlePoint-(windowHeight/2)+85, 120, 20);
            
            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 17));
            g2.setColor(new Color(0, 0, 0));
            g2.drawString("Visszalépés", middlePoint-55, middlePoint-(windowHeight/2)+100);
        }
        else if(this.gamePause)
        {
            int windowHeight = 120;
            int windowWidth = 360;

            g2.setColor(new Color(40, 40, 40, 210));
            g2.fillRect(middlePoint-(windowWidth/2), middlePoint-(windowHeight/2), windowWidth, windowHeight);

            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 20));
            g2.setColor(new Color(255, 255, 255, 220));
            g2.drawString("MEGÁLLÍTVA", middlePoint-65, middlePoint-(windowHeight/2)+25);

            g2.setColor(new Color(194, 194, 194));
            if(super.playerOption == 0)
            {
                g2.fillRect(middlePoint-67, middlePoint-(windowHeight/2)+38, 100, 15);
                g2.setColor(new Color(0, 0, 0));
            }
            else
            {
                g2.fillRect(middlePoint-67, middlePoint-(windowHeight/2)+58, 100, 15);
            }

            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));
            g2.setColor(new Color(200, 200, 200, 220));

            if(super.playerOption == 0)
                g2.setColor(new Color(0, 0, 0));

            g2.drawString("Mentés", middlePoint-65, middlePoint-(windowHeight/2)+50);
            g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));
            g2.setColor(new Color(200, 200, 200, 220));

            if(super.playerOption == 1)
                g2.setColor(new Color(0, 0, 0));

            g2.drawString("Visszalépés", middlePoint-65, middlePoint-(windowHeight/2)+70);
        }
    }

    /**
     * Being called when the head does a step-
     * @param head The head object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void hit(Head head, int coordX, int coordY, GameMap gameMap)
    {
        this.map.get(coordY).get(coordX).hit(head, gameMap);
    }

    /**
     * Being called when food is eaten in the head object
     */
    public void foodEaten()
    {
        for(int i = 0; i < snake.size(); i++)
        {
            snake.get(i).setLife(snake.get(i).getLife() + 1);
        }

        this.addPoint();
        food = null;
    }

    /**
     * Adds cell at a specific location. Used when snake moves.
     */
    public void snakeAddCell(int coordX, int coordY, String headDirection)
    {
        String cellDirection = "bottomleft";

        if(snake.size() > 1)
        {
            Cell previousCell = snake.get(1);
            String pCellDirection = previousCell.getCellDirection();
            String pCellHeadDirection = previousCell.getHeadDirection();

            if(pCellDirection.equals("bottomleft"))
            {
                if(pCellHeadDirection.equals("left"))
                {
                    if(headDirection.equals("left"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topright";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomright";
                }
                else if(pCellHeadDirection.equals("down"))
                {
                    if(headDirection.equals("left"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("right"))
                        cellDirection = "topright";
                    else if(headDirection.equals("down"))
                        cellDirection = "vertical";
                }
            }
            else if(pCellDirection.equals("bottomright"))
            {
                if(pCellHeadDirection.equals("right"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomleft";
                }
                else if(pCellHeadDirection.equals("down"))
                {
                    if(headDirection.equals("left"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("right"))
                        cellDirection = "topright";
                    else if(headDirection.equals("down"))
                        cellDirection = "vertical";
                }
            }
            else if(pCellDirection.equals("horizontal"))
            {
                if(pCellHeadDirection.equals("right"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomleft";
                }
                else if(pCellHeadDirection.equals("left"))
                {
                    if(headDirection.equals("left"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topright";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomright";
                }
            }
            else if(pCellDirection.equals("topleft"))
            {
                if(pCellHeadDirection.equals("up"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "bottomright";
                    else if(headDirection.equals("up"))
                        cellDirection = "vertical";
                    else if(headDirection.equals("left"))
                        cellDirection = "bottomleft";
                }
                else if(pCellHeadDirection.equals("left"))
                {
                    if(headDirection.equals("left"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topright";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomright";
                }
            }
            else if(pCellDirection.equals("topright"))
            {
                if(pCellHeadDirection.equals("up"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "bottomright";
                    else if(headDirection.equals("up"))
                        cellDirection = "vertical";
                    else if(headDirection.equals("left"))
                        cellDirection = "bottomleft";
                }
                else if(pCellHeadDirection.equals("right"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "horizontal";
                    else if(headDirection.equals("up"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("down"))
                        cellDirection = "bottomleft";
                }
            }
            else if(pCellDirection.equals("vertical"))
            {
                if(pCellHeadDirection.equals("up"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "bottomright";
                    else if(headDirection.equals("up"))
                        cellDirection = "vertical";
                    else if(headDirection.equals("left"))
                        cellDirection = "bottomleft";
                }
                else if(pCellHeadDirection.equals("down"))
                {
                    if(headDirection.equals("right"))
                        cellDirection = "topright";
                    else if(headDirection.equals("left"))
                        cellDirection = "topleft";
                    else if(headDirection.equals("down"))
                        cellDirection = "vertical";
                }
            }
            
        }
        else
        {
            if(headDirection.equals("right") || headDirection.equals("left"))
                cellDirection = "horizontal";
            else
                cellDirection = "vertical";
        }

        Cell cell = new snake.Cell(this, coordX, coordY, cellDirection, headDirection);
        ArrayList<Block> line = map.get(coordY);

        line.set(coordX, cell);
        map.set(coordY, line);

        snake.add(1, cell);
    }

    /**
     * Called to spawn a new food object
     */
    private void spawnFood()
    {
        if(food != null)
            return;
        
        int coordX, coordY;
        Random random = new Random();

        do
        {
            coordX = random.nextInt(mapSize);
            coordY = random.nextInt(mapSize);
        }
        while(map.get(coordY).get(coordX).getClass() != Void.class);

        this.food = new Food(this, coordX, coordY);

        ArrayList<Block> line = map.get(coordY);
        line.set(coordX, food);
    }

    /**
     * Adds 1 unit of point calculated by the mapsize and difficulty
     */
    public void addPoint()
    {
        int pointMultiplier = 4;

        switch(mapDifficulty)
        {
            case "Könnyű": {pointMultiplier = 4; break;}
            case "Normál": {pointMultiplier = 2; break;}
            case "Nehéz": {pointMultiplier = 1; break;}
        }

        this.points += ((48 - mapSize)/pointMultiplier);
    }

    /**
     * Counts wall objects around a coordinate
     * @param coordX
     * @param coordY
     * @return the count of walls
     */
    private int countWallsAround(int coordX, int coordY)
    {
        int count = 0;

        if(getBlockByCoords(coordX-1, coordY).isWall())     count++;
        if(getBlockByCoords(coordX+1, coordY).isWall())     count++;
        if(getBlockByCoords(coordX-1, coordY-1).isWall())   count++;
        if(getBlockByCoords(coordX, coordY-1).isWall())     count++;
        if(getBlockByCoords(coordX+1, coordY-1).isWall())   count++;
        if(getBlockByCoords(coordX-1, coordY+1).isWall())   count++;
        if(getBlockByCoords(coordX, coordY+1).isWall())     count++;
        if(getBlockByCoords(coordX+1, coordY+1).isWall())   count++;
    
        return count;
    }

    //------------------[Overrided Functions]-----------------

    /**
     * Forwards the update command to all the map blocks
     */
    @Override
    public void update(long milisecs)
    {
        ArrayList<Block> line;

        for(int i = 0; i < map.size(); i++)
        {
            line = map.get(i);

            for(int l = 0; l < line.size(); l++)
            {
                line.get(l).update(milisecs);
            }
        }

        if(food == null)
        {
            spawnFood();
        }
    }

    /**
     * Handles option menu options
     */
    @Override
    public void handleMenuOption()
    {
        if(super.playerOption == 0)
        {
            this.saveDate = new Date();
            this.game.saveMap(this);
            this.game.setMenu(this.previousMenu);
        }

        if(super.playerOption == 1)
        {
            this.gamePause = false;
        }
    }

    @Override
    public void keyPressed(int keyCode)
    {
        if(!this.gameEnd)
        {
            if(this.gamePause)
            {
                switch(keyCode)
                {
                    case KeyEvent.VK_DOWN:          {super.nextPlayerOption(); break;}
                    case KeyEvent.VK_RIGHT:         {super.nextPlayerOption(); break;}
                    case KeyEvent.VK_UP:            {super.previousPlayerOption(); break;}
                    case KeyEvent.VK_LEFT:          {super.previousPlayerOption(); break;}

                    case KeyEvent.VK_ENTER:         {this.handleMenuOption(); break;}
                    case KeyEvent.VK_SPACE:         {this.handleMenuOption(); break;}
                }

                return;
            }

            if(keyCode == KeyEvent.VK_P)
            {
                this.gamePause = true;
                return;
            }

            try
            {
                snake.get(0).keyPressed(keyCode);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            if(keyCode == KeyEvent.VK_ENTER ||keyCode == KeyEvent.VK_SPACE)
                this.game.setMenu(this.previousMenu);
        }
    }

    @Override
    public void keyTyped(char key) {
        // TODO Auto-generated method stub
        
    }

    // - - - - - [Get/Set methods]
    /**
     * Returns mapSize variable
     * @return mapSize
     */
    public int getMapSize()             {return this.mapSize;}

    /**
     * Returns mapDifficulty variable
     * @return mapDifficulty
     */
    public String getMapDifficulty()    {return this.mapDifficulty;}

    /**
     * Returns the tileSize of the map in pixels
     * @return the tilesize
     */
    public int getTilesize()            {return this.game.gamePanel.getTileSize();}

    /**
     * Returns snake variable
     * @return snake
     */
    public ArrayList<Cell> getSnake()   {return this.snake;}

    /**
     * Returns gameEnd variable. True if game has ended, false otherwise.
     * @return gameEnd
     */
    public boolean getGameEnd()         {return this.gameEnd;}

    /**
     * Returns gamePause variable
     * @return gamePause
     */
    public boolean getGamePause()       {return this.gamePause;}

    /**
     * Returns playerName variable
     * @return playerName
     */
    public String getPlayerName()       {return this.playerName;}

    /**
     * Returns a block from map variable by the coords given
     * @param coordX
     * @param coordY
     * @return
     */
    public Block getBlockByCoords(int coordX, int coordY)
    {
        try
        {
            return this.map.get(coordY).get(coordX);
        }
        catch(Exception e)
        {
            return new Void(this, coordY, coordX);
        }
    }

    /**
     * Sets a block into map by the blocks x and y coordinates
     * @param block
     */
    public void setBlock(Block block)
    {
        ArrayList<Block> line = this.map.get(block.coordY);
        line.set(block.coordX, block);

        this.map.set(block.coordY, line);
    }

    /**
     * Sets gameEnd variable
     * @param gameEnd
     */
    public void setGameEnd(boolean gameEnd)
    {
        this.gameEnd = gameEnd;
    }

    /** 
     * Returns a text of string containing its savetime, playername, mapsize and difficulty.
     */
    public String toString()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(this.saveDate);

        return strDate + " | " + this.playerName + " |" + this.mapSize  + " | " + this.mapDifficulty;
    }
}
