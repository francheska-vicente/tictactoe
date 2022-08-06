import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class View extends JFrame {

    private final int SIZE = 3;
    private final int LEVELS = 3;

    private JPanel game;

    private JButton[][] squares;

    private Font title;
    private Font body;
    private Color empty;
    private ImageIcon emptyIcon;
    private ImageIcon xIcon;
    private ImageIcon oIcon;

    public View() {
        super("Tic Tac Toe");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.height - 100, screenSize.height - 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initResources();
    }

    /**
     * Sets the ActionListener of the buttons in the grid to the indicated
     * ActionListener.
     *
     * @param listener is the ActionListener to be assigned to the buttons
     */
    public void setListener(ActionListener listener) {
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++)
                squares[j][k].addActionListener(listener);
    }

    /**
     * Initializes the images, sounds, fonts, and other resources.
     */
    private void initResources() {
        // fonts
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        title = null;
        body = null;
        try {
            InputStream is = Main.class.getResourceAsStream("/fonts/Chromate.ttf");
            title = Font.createFont(Font.TRUETYPE_FONT, is);
            ge.registerFont(title);
        } catch (Exception ex) {
            title = new Font("Roboto", Font.PLAIN, 1);
        }
        try {
            InputStream is = Main.class.getResourceAsStream("/fonts/NATS.ttf");
            body = Font.createFont(Font.TRUETYPE_FONT, is);
            ge.registerFont(body);
        } catch (Exception ex) {
            body = new Font("Roboto", Font.PLAIN, 1);
        }

        // colors
        empty = new Color(106, 211, 181);

        // icons
        emptyIcon = new ImageIcon("images/empty.png");
        xIcon = new ImageIcon("images/x.png");
        oIcon = new ImageIcon("images/o.png");
    }

    /**
     * Initializes the components of the game panel.
     * 
     * @param board is the grid
     */
    public void initBoard(char[][] board) {
        game = new JPanel();
        game.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // generates grid of squares
        squares = new JButton[SIZE][SIZE];
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                squares[j][k] = new JButton();
                squares[j][k].setActionCommand(Integer.toString(j) + Integer.toString(k));
                squares[j][k].setIcon(emptyIcon);
                squares[j][k].setBackground(empty);
                game.add(squares[j][k], c);
                c.gridx++;
            }
            c.gridx = 0;
            c.gridy++;
        }

        add(game);
        setVisible(true);
    }

    /**
     * Updates the icons per square and disables the buttons with marks.
     * 
     * @param board is the grid
     */
    public void updateBoard(char[][] board) {
        for (int j = 0; j < SIZE; j++)
            for (int k = 0; k < SIZE; k++)
                if (board[j][k] == 'X') {
                    squares[j][k].setDisabledIcon(xIcon);
                    squares[j][k].setEnabled(false);
                }
                else if (board[j][k] == 'O') {
                    squares[j][k].setDisabledIcon(oIcon);
                    squares[j][k].setEnabled(false);
                }
    }

    /**
     * Resets the game by removing the game panel and hiding the frame.
     */
    public void resetGame() {
        setVisible(false);
        remove(game);
    }

    /**
     * Creates a JOptionPane which asks the user for which of the players will
     * go first, and which level is the agent at.
     *
     * @return input is the array containing the user's choices.
     */
    public int[] askUser() {
        int[] input = new int[2];

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel text1 = new JLabel("Which level of rational behavior?");
        panel.add(text1);

        JRadioButton[] levels = new JRadioButton[LEVELS];
        ButtonGroup group = new ButtonGroup();
        levels[0] = new JRadioButton("Level 0 (Random)");
        levels[0].setActionCommand("0");
        levels[1] = new JRadioButton("Level 1 (Table)");
        levels[1].setActionCommand("1");
        levels[2] = new JRadioButton("Level 2 (Search)");
        levels[2].setActionCommand("2");
        for (int i = 0; i < LEVELS; i++)
            group.add(levels[i]);
        levels[0].setSelected(true);
        for (int j = 0; j < LEVELS; j++)
            panel.add(levels[j]);

        JLabel text2 = new JLabel("Who will go first?");
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(text2);

        Object[] options = {"Player", "Computer"};
        int n = JOptionPane.showOptionDialog(null, panel, "Tic-Tac-Toe Configuration",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);

        // input for player order
        switch (n) {
            case JOptionPane.YES_OPTION:
                input[0] = 1;
                break;
            case JOptionPane.NO_OPTION:
                input[0] = 0;
                break;
            default:
                input[0] = -1;
        }

        // input for agent level
        switch (group.getSelection().getActionCommand()) {
            case "0":
                input[1] = 0;
                break;
            case "1":
                input[1] = 1;
                break;
            default:
                input[1] = 2;
        }

        return input;
    }

    /**
     * Shows a prompt for when the game ends in a draw.
     */
    public void drawPrompt() {
        String text = "It's a Draw!";
        JOptionPane.showMessageDialog(this, text, "No Winner", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a prompt for when the user wins the game.
     */
    public void winPrompt() {
        String text = "You win!";
        JOptionPane.showMessageDialog(this, text, "Winner", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a prompt for when the user loses the game.
     */
    public void losePrompt() {
        String text = "The computer won!";
        JOptionPane.showMessageDialog(this, text, "Loser", JOptionPane.INFORMATION_MESSAGE);
    }
}
