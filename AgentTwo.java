public class AgentTwo extends Agent
{
	public int [] decision (char [][] board, char computer, char player)
	{
		AlphaBeta gameTree = new AlphaBeta (player, computer);
		int count = super.countUnmarked (board);
		int [] moves = gameTree.getBestCoords (board, count);
		
		return moves;	
	}
}