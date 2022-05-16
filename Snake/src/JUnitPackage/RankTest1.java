package JUnitPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import snake.Rankings;
import snake.Rank;

class RankTest1 {

	@Test
	void test()
	{
		List<Rank> rankList = new ArrayList<Rank>();
		Rankings rankings = new Rankings();
		Rank rank;
		Random random = new Random();
		int number = 0;

		for(int i = 0; i < 11; i++)
		{
			rank = new Rank("Gamer"+i, random.nextInt());

			rankList.add(rank);
			rankings.addRank(rank);
		}

		for(int i = 1; i < 11; i++)
		{
			if(rankList.get(i).getPoints() > rankList.get(number).getPoints())
			{
				number = i;
			}
		}

		assertEquals(rankings.getRank(0).getPoints(), rankList.get(number));
	}

}
