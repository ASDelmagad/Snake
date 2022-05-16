package snake;

import java.io.Serializable;
import java.util.Comparator;

public class Rank implements Comparator<Rank>, Comparable<Rank>, Serializable
{
    private String name;
    private int points;
    private String message;

    public Rank()
    {
    }

    public Rank(String name, int points)
    {
        this.name = name;
        this.points = points;
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

    public String toString()
    {
        return this.name + " | " + this.points;
    }

    public int compare(Rank r1, Rank r2)
    {
        return r2.points - r1.points;
    }

    @Override
    public int compareTo(Rank o) {
        return 0;
    }
}
