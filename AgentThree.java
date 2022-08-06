public class AgentThree extends Agent
{
	private int current_depth;

	public AgentThree ()
	{
		current_depth = 0;
	}

	public int [] decision (char [][] board, char computer, char player)
	{
		AlphaBetaTwo gameTree = new AlphaBetaTwo (computer, player);
		int count = super.countUnmarked (board);
		int [] moves = gameTree.getBestCoords (board, current_depth);
		
		current_depth++;
		
		return moves;
	}
}