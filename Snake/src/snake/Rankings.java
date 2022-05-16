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

    public Rank getRank(int rankId)
    {
        // Sort before giving out id
        Collections.sort(rankings, new Rank());

        return rankings.get(rankId);
    }

    public void addRank(Rank rank)
    {
        rankings.add(rank);
    }

    public int size()
    {
        return rankings.size();
    }
}
