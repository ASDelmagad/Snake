package snake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rankings implements Serializable
{
    List<Rank> rankings = new ArrayList<Rank>();

    /**
     * Sets the rankings variable to rankings param
     * @param rankings
     */
    public void setRankings(List<Rank> rankings)
    {
        this.rankings = rankings;
    }

    /**
     * Returns rankings variable
     * @return rankings
     */
    public List<Rank> getRankings()
    {
        return this.rankings;
    }

    /**
     * Returns rank by id from the rankings list
     * @param rankId
     * @return rank object
     */
    public Rank getRank(int rankId)
    {
        // Sort before giving out id
        Collections.sort(rankings, new Rank());

        return rankings.get(rankId);
    }

    /**
     * Adds rank to rankings list
     * @param rank
     */
    public void addRank(Rank rank)
    {
        rankings.add(rank);
    }

    /**
     * Returns size of rankings list
     * @return size of rankings list
     */
    public int size()
    {
        return rankings.size();
    }
}
