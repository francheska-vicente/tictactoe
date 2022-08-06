public abstract class Agent
{
	public abstract int [] decision (char [][] board, char computer, char player);

	public static int countUnmarked (char [][] board)
	{
		int count = 0;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board [i][j] == ' ')
					count++;
		
		return count;
	}

	public static int [] checkWinningMove (char [][] board, char symbol)
	{
		int [] win = {-1, -1};
		boolean checker = true;

		for (int i = 0; i < 3 && checker; i++)
			if (board [i][0] == symbol && board [i][1] == symbol)
			{
				if (board [i][2] == ' ')
				{
					checker = false;
					win [0] = i;
					win [1] = 2;
				}
			}
			else if (board [i][0] == symbol && board[i][2] == symbol)
			{	
				if (board [i][1] == ' ')
				{
					checker = false;
					win [0] = i;
					win [1] = 1;
				}
			}
			else if (board [i][1] == symbol && board [i][2] == symbol)
			{
				if (board [i][0] == ' ')
				{
					checker = false;
					win [0] = i;
					win [1] = 0;
				}
			}

		if (checker)
		{
			for (int i = 0; i < 3 && checker; i++)
				if (board [0][i] == symbol && board [1][i] == symbol)
				{
					if (board [2][i] == ' ')
					{
						checker = false;
						win [0] = 2;
						win [1] = i;
					}
				}
				else if (board [0][i] == symbol && board [2][i] == symbol)
				{
					if (board [1][i] == ' ')
					{
						checker = false;
						win [0] = 1;
						win [1] = i;
					}
				}
				else if (board [1][i] == symbol && board [2][i] == symbol)
				{
					if (board [0][i] == ' ')
					{
						checker = false;
						win [0] = 0;
						win [1] = i;
					}
				}

			if (checker)
			{
				if (board [0][0] == symbol && board [1][1] == symbol && board [2][2] == ' ')
				{
					win [0] = 2;
					win [1] = 2;
				}
				else if (board [0][0] == symbol && board [1][1] == ' ' && board [2][2] == symbol)
				{
					win [0] = 1;
					win [1] = 1;
				}
				else if (board [0][0] == ' ' && board [1][1] == symbol && board [2][2] == symbol)
				{
					win [0] = 0;
					win [1] = 0;
				}
				else if (board [0][2] == ' ' && board [1][1] == symbol && board [2][0] == symbol)
				{
					win [0] = 0;
					win [1] = 2;
				}
				else if (board [0][2] == symbol && board [1][1] == ' ' && board [2][0] == symbol)
				{
					win [0] = 1;
					win [1] = 1;
				}
				else if (board [0][2] == symbol && board [1][1] == symbol && board [2][0] == ' ')
				{
					win [0] = 2;
					win [1] = 0;
				}
			}
		}
		
		return win;
	}

}