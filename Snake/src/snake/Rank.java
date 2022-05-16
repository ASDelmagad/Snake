package snake;

import java.io.Serializable;
import java.util.Comparator;

public class Rank implements Comparator<Rank>, Comparable<Rank>, Serializable
{
    private String name;
    private int points;

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

    /**
     * Returns name and points in the same string
     */
    public String toString()
    {
        return this.name + " | " + this.points;
    }

    /**
     * Used by comaprator for listing
     */
    public int compare(Rank r1, Rank r2)
    {
        return r2.points - r1.points;
    }

    /**
     * Used by comparator
     */
    @Override
    public int compareTo(Rank o) {
        return 0;
    }
}
