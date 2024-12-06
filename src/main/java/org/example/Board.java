package org.example;

/**
 * Represents the Connect4 game board.
 * Provides methods for initializing,
 * displaying, and manipulating the board state.
 */
public class Board {
    private char[][] board;

    /**
     * Initializes a new Connect4 game
     * board with default size (6 rows x 7 columns).
     */
    public Board() {
        this.board = new char[6][7];
        initializeBoard();
    }

    /**
     * Fills the board with empty spaces (' ').
     * This method is called during board initialization.
     */
    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' '; // Empty space
            }
        }
    }

    /**
     * Gets the current state of the
     * board as a 2D character array.
     *
     * @return a 2D array representing
     *         the current board state
     */
    public char[][] getBoardState() {
        return board;
    }

    /**
     * Sets the board state using
     * a 2D character array.
     *
     * @param state a 2D array
     *              representing the new board state
     */
    public void setBoardState(char[][] state) {
        this.board = state;
    }

    /**
     * Displays the board in a readable
     * format to the console.
     * Each cell is separated by vertical bars '|'.
     */
    public void display() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print("|" + cell);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    /**
     * Drops a checker (symbol) into
     * the specified column.
     * The checker falls to the
     * lowest available row in the column.
     *
     * @param column the column index
     *               (0-based) to drop the checker into
     * @param symbol the character symbol
     *               representing the player's checker
     */
    public void dropChecker(int column, char symbol) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = symbol;
                break;
            }
        }
    }

    /**
     * Checks if the given player has won the game.
     * It checks horizontal, vertical, and diagonal lines.
     *
     * @param symbol the character symbol
     *               representing the player's checker
     * @return true if the player has four
     *         checkers in a row, otherwise false
     */
    public boolean checkWinner(char symbol) {
        // Check horizontal lines
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                if (board[row][col] == symbol
                        && board[row][col + 1] == symbol
                        && board[row][col + 2] == symbol
                        && board[row][col + 3] == symbol) {
                    return true; // Found a horizontal line of 4
                }
            }
        }

        // Check vertical lines
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length - 3; row++) {
                if (board[row][col] == symbol
                        && board[row + 1][col] == symbol
                        && board[row + 2][col] == symbol
                        && board[row + 3][col] == symbol) {
                    return true; // Found a vertical line of 4
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                if (board[row][col] == symbol
                        && board[row + 1][col + 1] == symbol
                        && board[row + 2][col + 2] == symbol
                        && board[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonals (bottom-left to top-right)
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                if (board[row][col] == symbol
                        && board[row - 1][col + 1] == symbol
                        && board[row - 2][col + 2] == symbol
                        && board[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        return false; // No winner found
    }

    /**
     * Validates whether the specified
     * column is a valid and playable choice.
     *
     * @param column the column index to validate
     * @return true if the column is
     *         within bounds and not full, false otherwise
     */
    public boolean validateColumn(int column) {
        // Check if the column index is within bounds
        if (column < 0 || column >= board[0].length) {
            return false;
        }

        // Check if the column is not full
        return board[0][column] == ' ';
    }
}
