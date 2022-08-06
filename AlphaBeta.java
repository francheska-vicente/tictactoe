import java.util.*;

public class AlphaBeta
{
	private char player;
	private char computer;

	public AlphaBeta (char player, char computer)
	{
		this.player = player;
		this.computer = computer;
	}

	public int evaluateHeuristicValue (char [][] board)
	{
		int result = 0; // 0 -> draw, -1 -> lose, 1 -> win
		int winPlayer = 0, winComp = 0;
		
		for (int i = 0; i < 3; i++)
		{
			winPlayer = 0;
			winComp = 0;

			for (int j = 0; j < 3; j++)
				if (board [i][j] == player)
					winPlayer++;
				else if (board [i][j] == computer)
					winComp++;

			if (winPlayer == 3)
				result = -1;
			else if (winComp == 3)
				result = 1;
		}
			
		if (result == 0)
		{
			for (int j = 0; j < 3; j++)
			{
				winPlayer = 0;
				winComp = 0;

				for (int i = 0; i < 3; i++)
					if (board [i][j] == player)
						winPlayer++;
					else if (board [i][j] == computer)
						winComp++;

				if (winPlayer == 3)
					result = -1;
				else if (winComp == 3)
					result = 1;
			}

			if (result == 0)
			{
				winPlayer = 0;
				winComp = 0;

				for (int i = 0; i < 3; i++)
					if (board [i][i] == player)
						winPlayer++;
					else if (board [i][i] == computer)
						winComp++;

				if (winPlayer == 3)
					result = -1;
				else if (winComp == 3)
					result = 1;

				if (result == 0)
				{
					winPlayer = 0;
					winComp = 0;

					for (int i = 0; i < 3; i++)
						if (board [i][2 - i] == player)
							winPlayer++;
						else if (board [i][2 - i] == computer)
							winComp++;

					if (winPlayer == 3)
						result = -1;
					else if (winComp == 3)
						result = 1;
				}
			}
		}

		return result;
	}

	public void printConfig (char [][] board)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board [i][j] == ' ')
					System.out.print ("_");
				else
					System.out.print (board [i][j]);
			}
			System.out.println ();
		}
		System.out.println ();
		System.out.println ();
	}

	public int minimum (char [][] board, int depth, boolean maximize, int alpha, int beta)
	{
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board [i][j] == ' ')
				{
					board [i][j] = player;
					//printConfig (board);
					min = Math.min (min, minimax (board, depth - 1, maximize, alpha, beta));
					board [i][j] = ' ';
					beta = Math.min (beta, min);

					if (alpha >= beta)
						return min;
				}

		return min;
	}

	public int maximum (char [][] board, int depth, boolean maximize, int alpha, int beta)
	{
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board [i][j] == ' ')
				{
					board [i][j] = computer;
					//printConfig (board);
					max = Math.max (max, minimax (board, depth - 1, maximize, alpha, beta));
					board [i][j] = ' ';
					alpha = Math.max (alpha, max);

					if (alpha >= beta)
						return max;
				}

		return max;
	}

	public int minimax (char [][] board, int depth, boolean maximize, int alpha, int beta)
	{
		int heuristicVal = evaluateHeuristicValue (board);
		
		if (heuristicVal == 1 || heuristicVal == -1 || depth == 0 || Agent.countUnmarked (board) == 0)
		{
			return heuristicVal;
		}
		else
		{
			if (maximize)
			{
				return maximum (board, depth, false, alpha, beta);
			}	
			else
			{
				return minimum (board, depth, true, alpha, beta);
			}
		}
	}


	public int [] getBestCoords (char [][] board, int count)
	{
		int [] coords = Agent.checkWinningMove (board, computer);
		int max = -99999;
		// System.out.println (coords [0] + " " + coords [1]);
		// System.out.println ("count: " + count);

		if (board [1][1] == ' ')
		{
			coords [0] = 1;
			coords [1] = 1;
		}
		else if (coords [0] == -1 && coords [0] == -1)
		{
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
				{
					int current = 0;
					if (board [i][j] == ' ')
					{
						board [i][j] = computer;
						current = minimax (board, 12, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
						
						board [i][j] = ' ';

						if (current > max)
						{
							coords [0] = i;
							coords [1] = j;
							max = current;
						}
					}
				}
		}
		
		// System.out.println ("coords:  " + coords [0] + " " + coords [1]);
		return coords;
	}
}