import java.util.*;

public class AlphaBetaTwo
{
	private char player;
	private char computer;
	private final int MAX_DEPTH;

	public AlphaBetaTwo (char player, char computer)
	{
		this.player = player;
		this.computer = computer;
		MAX_DEPTH = 12;
	}

	private ArrayList <int []> getUnmarked (char [][] board)
	{
		ArrayList <int []> moves = null;

		return moves;
	}

	private int [] minimax (char [][] board, int depth, int alpha, int beta)
	{
		int [] ans = new int [3];

		return ans;
	}

	public int [] getBestCoords (char [][] board, int current_depth)
	{
		int [] coords = Agent.checkWinningMove (board, computer);

		if (board [1][1] == ' ')
		{
			coords [0] = 1;
			coords [1] = 1;
		}
		else if (coords [0] == -1 && coords [0] == -1)
		{
			int [] temp = minimax (board, current_depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

			coords [0] = temp [1];
			coords [1] = temp [2];
		}

		return coords;
	}
}