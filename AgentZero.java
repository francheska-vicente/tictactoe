import java.util.Random;

public class AgentZero extends Agent
{
	private int generateRandomNumber ()
	{
		Random rand = new Random ();
		return (rand.nextInt (3) + 1);
	}

	public int [] decision (char [][] board, char computer, char player)
	{
		int [] moves = super.checkWinningMove (board, computer);

		if (moves [0] == -1 && moves [1] == -1)
		{
			boolean checker = true;

			do
			{
				moves [0] = generateRandomNumber () - 1;
				moves [1] = generateRandomNumber () - 1;

				if (board [moves [0]][moves [1]] == ' ')
					checker = false;
			} while (checker);
		}
		
		return moves;	
	}
}