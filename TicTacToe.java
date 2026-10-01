import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    private final JButton[] buttons = new JButton[9];
    private final char[] board = new char[9];
    private char currentPlayer = 'X';
    private final JLabel status = new JLabel("Player X's turn", SwingConstants.CENTER);

    public TicTacToe() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel grid = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 0; i < buttons.length; i++) {
            int position = i;
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 42));
            buttons[i].addActionListener(event -> makeMove(position));
            grid.add(buttons[i]);
        }

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(event -> resetGame());

        frame.add(status, BorderLayout.NORTH);
        frame.add(grid, BorderLayout.CENTER);
        frame.add(newGame, BorderLayout.SOUTH);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void makeMove(int position) {
        if (board[position] != '\0') {
            return;
        }

        board[position] = currentPlayer;
        buttons[position].setText(String.valueOf(currentPlayer));

        if (hasWinner()) {
            status.setText("Player " + currentPlayer + " wins!");
            disableBoard();
        } else if (isDraw()) {
            status.setText("It's a draw!");
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            status.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean hasWinner() {
        int[][] lines = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] line : lines) {
            if (board[line[0]] != '\0'
                    && board[line[0]] == board[line[1]]
                    && board[line[1]] == board[line[2]]) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (char cell : board) {
            if (cell == '\0') {
                return false;
            }
        }
        return true;
    }

    private void disableBoard() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '\0';
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        currentPlayer = 'X';
        status.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
