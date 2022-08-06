public class AgentOne extends Agent
{
	public int [] getSquare (char [][] board, char computer, char player)
	{
		int [] moves = {-1, -1};
		boolean checker = true;
		int [][] choices = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
		int i = 0;

		while (checker && i < choices.length)
		{
			if (board [choices [i][0]][choices [i][1]] == ' ')
			{
				moves [0] = choices [i][0];
				moves [1] = choices [i][1];
				checker = false;
			}
			else
				i++;
		}

		return moves;
	}

	public int [] getBestCorner (char [][] board, char computer, char player)
	{
		int [] moves = {-1, -1};

		if (board [2][1] == player && board [1][2] == player && board [2][2] == ' ')
		{
			moves [0] = 2;
			moves [1] = 2;
		}
		else if (board [0][0] == player && board [2][2] == player && board [1][0] == ' ')
		{
			moves [0] = 1;
			moves [1] = 0;
		}
		else if (board [0][2] == player && board [2][0] == player && board [0][1] == ' ')
		{
			moves [0] = 0;
			moves [1] = 1;
		}
		else if (board [0][0] == ' ' && board [2][2] == ' ' && board [0][0] == ' ')
		{
			moves [0] = 0;
			moves [1] = 0;
		}
		else if (board [0][2] == ' ' && board [2][0] == ' ' && board [0][2] == ' ')
		{
			moves [0] = 0;
			moves [1] = 2;
		}
		else if (board [0][0] == ' ')
		{
			moves [0] = 0;
			moves [1] = 0;
		}
		else if (board [2][2] == ' ')
		{
			moves [0] = 2;
			moves [1] = 2;
		}
		else if (board [0][2] == ' ')
		{
			moves [0] = 0;
			moves [1] = 2;
		}
		else if (board [2][0] == ' ')
		{
			moves [0] = 2;
			moves [1] = 0;
		}

		return moves;
	} 

	public int [] decision (char [][] board, char computer, char player)
	{
		int [] moves = super.checkWinningMove (board, computer);

		// if there is a move that would make the computer win, return that coordinates

		// if there is no move that would make the computer win in the current configuration
		if (moves [0] == -1 && moves [1] == -1)
		{
			moves = super.checkWinningMove (board, player);

			// if there is a move that would make the player win, return that coordinates to block that square

			// if there is no move that would make the player win in the current configuration
			if (moves [0] == -1 && moves [1] == -1)
			{
				if (board [1][1] == ' ')
				{
					moves [0] = 1;
					moves [1] = 1;
				}
				else
				{
					if (moves [0] == -1 && moves [1] == -1)
					{
						moves = getBestCorner (board, computer, player);

						if (moves [0] == -1 && moves [1] == -1)
							moves = getSquare (board, computer, player);
					}
				}
			}
		}

		return moves;	
	}
} 