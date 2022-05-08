package snake;

public class Rank
{
    private String name;
    private int points;
    private String message;

    public Rank(String name, int points, String message)
    {
        this.name = name;
        this.points = points;
        this.message = message;
    }

    /**
     * Returns the name variable of this object
     * @return name
     */
    public String getName()     {return this.name;}

    /**
     * Return the points variable of this object
     * @return points
     */
    public int getPoints()      {return this.points;}

    /**
     * Returns the message variable of this object
     * @return message
     */
    public String getMessage()  {return this.message;}
}
