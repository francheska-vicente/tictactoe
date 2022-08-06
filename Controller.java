
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class Controller implements ActionListener {

    private Model model;
    private View view;
    private Agent agent;
    private boolean endGame;
    private char current;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Initializes the game based on the user input then starts the game.
     */
    public void gameFlow() {
        int[] input = view.askUser();
        if (input[0] != -1) {
            initializeGame(input[0], input[1]);
            if (model.getComp() == 'X')
                compTurn();
        }
        else
            System.exit(0);
    }

    /**
     * Creates the Tic-Tac-Toe grid, sets the player who will go first,
     * and creates the agent based on the user input.
     * 
     * @param   first is the player who will go first
     * @param   level is the level of the agent
     */
    private void initializeGame(int first, int level) {
        char player;
        if (first == 1)
            player = 'X';
        else
            player = 'O';

        model.createBoard(player);
        if (level == 0)
            agent = new AgentZero();
        else if (level == 1)
            agent = new AgentOne();
        else if (level == 2)
            agent = new AgentTwo();

        view.initBoard(model.getBoard());
        view.updateBoard(model.getBoard());
        view.setListener(this);
    }
    
    /**
     * Updates the grid based on the user's input,
     * triggers the computer's turn (if possible), and
     * checks if the game is over.
     * 
     * @param   row is the row value of the user's move
     * @param   col is the col value of the user's move
     */
    public void playerTurn(int row, int col) {
        model.setPlayerMove(row, col);
        view.updateBoard(model.getBoard());

        // checks if computer can make a move
        if (model.checkWinner() == ' ' && model.getMoves() < 9)
            compTurn();

        // checks if the user won
        if (model.checkWinner() == model.getPlayer()) {
            view.winPrompt();
            resetGame();
        }
        // checks if the computer won
        else if (model.checkWinner() == model.getComp()) {
            view.losePrompt();
            resetGame();
        }
        // checks if the grid is full and no more moves can be made
        else if (model.getMoves() == 9) {
            view.drawPrompt();
            resetGame();
        }

    }

    /**
     * Updates the grid based on the agent's decision.
     */
    public void compTurn() {
        int[] move = agent.decision(model.getBoard(), model.getComp(), model.getPlayer ());

        model.setCompMove(move[0], move[1]);
        view.updateBoard(model.getBoard());
    }

    /**
     * Resets the grid after a game ends and restarts the program.
     */
    public void resetGame() {
        view.resetGame();
        gameFlow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++)
                if (e.getActionCommand().equals(Integer.toString(j) + Integer.toString(k)))
                    playerTurn(j, k);
    }
}
