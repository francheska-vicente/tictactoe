import java.util.*;

public class Model {
    private char[][] board;

    private char player;
    private char computer;

    private int movectr;

    /**
     * Initializes the Tic-Tac-Toe game.
     * 
     * @param   player is the user's chosen symbol
     */
    public void createBoard(char player) {
        // creates an empty tic-tac-toe grid
        board = new char[3][3];
        for (int j = 0; j < 3; j++)
            Arrays.fill(board[j], ' ');
        
        // sets the symbols for both the user and the computer
        this.player = player;
        if (player == 'X')
            computer = 'O';
        else
            computer = 'X';

        // initializes the move counter to 0
        movectr = 0;
    }

    /**
     * Returns the whole board.
     * 
     * @return  board is the board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Returns the symbol of the user.
     * 
     * @return  player is the symbol of the user.
     */
    public char getPlayer() {
        return player;
    }

    /**
     * Returns the symbol of the computer.
     * 
     * @return  computer is the symbol of the computer.
     */
    public char getComp() {
        return computer;
    }

    /**
     * Returns the total number of moves so far.
     * 
     * @return  movectr is the total number of moves.
     */
    public int getMoves() {
        return movectr;
    }

    /**
     * Marks a space in the grid for the user, if valid.
     * 
     * @param   row is the row value of the user's move
     * @param   col is the col value of the user's move
     * @return  true if the move is valid, or 
     *          false if the move is invalid
     */
    public boolean setPlayerMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = player;
            movectr++;
            return true;
        }
        else
            return false;
    }

    /**
     * Marks a space in the grid for the computer, if valid.
     * 
     * @param   row is the row value of the computer's move
     * @param   col is the col value of the computer's move
     * @return  true if the move is valid; false if the move is invalid.
     */
    public boolean setCompMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = computer;
            movectr++;
            return true;
        }
        else
            return false;
    }

    /**
     * Checks if either the user or the computer has won the game.
     * The game is won if three symbols are placed in a horizontal, 
     * vertical, or diagonal line.
     * 
     * @return  the symbol of the winning user, 
     *          or a space if there is no winner yet
     */
    public char checkWinner() {
        if (board[0][0] == player && board[0][1] == player && board[0][2] == player
            || board[1][0] == player && board[1][1] == player && board[1][2] == player
            || board[2][0] == player && board[2][1] == player && board[2][2] == player

            || board[0][0] == player && board[1][0] == player && board[2][0] == player
            || board[0][1] == player && board[1][1] == player && board[2][1] == player
            || board[0][2] == player && board[1][2] == player && board[2][2] == player

            || board[0][0] == player && board[1][1] == player && board[2][2] == player
            || board[2][0] == player && board[1][1] == player && board[0][2] == player)
            return player;
        else if (board[0][0] == computer && board[0][1] == computer && board[0][2] == computer
            || board[1][0] == computer && board[1][1] == computer && board[1][2] == computer
            || board[2][0] == computer && board[2][1] == computer && board[2][2] == computer

            || board[0][0] == computer && board[1][0] == computer && board[2][0] == computer
            || board[0][1] == computer && board[1][1] == computer && board[2][1] == computer
            || board[0][2] == computer && board[1][2] == computer && board[2][2] == computer

            || board[0][0] == computer && board[1][1] == computer && board[2][2] == computer
            || board[2][0] == computer && board[1][1] == computer && board[0][2] == computer)
            return computer;
        else
            return ' ';
    }
}
