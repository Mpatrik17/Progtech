package org.example;

/**
 * Represents the state of a Connect4 game at a particular moment.
 */
public class GameState {
    private final Player player1;
    private final Player player2;
    private final Player winner;
    private final int turn;
    private final String boardState;
    private final Player currentPlayer;

    /**
     * Constructor to initialize a GameState object.
     *
     * @param playerOne      The first player.
     * @param playerTwo      The second player.
     * @param gameWinner     The winner of the game (null if no winner).
     * @param currentTurn    The current turn number.
     * @param gameBoardState The current state of the board as a string.
     */
    public GameState(Player playerOne,
                     Player playerTwo,
                     Player gameWinner,
                     int currentTurn,
                     String gameBoardState) {
        this.player1 = playerOne;
        this.player2 = playerTwo;
        this.winner = gameWinner;
        this.turn = currentTurn;
        this.boardState = gameBoardState;
        this.currentPlayer = gameWinner;
    }

    /**
     * Gets the first player.
     *
     * @return The first player.
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets the second player.
     *
     * @return The second player.
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Gets the winner of the game.
     *
     * @return The winner, or null if no winner.
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Gets the current turn number.
     *
     * @return The turn number.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Gets the current state of the board.
     *
     * @return The board state as a string.
     */
    public String getBoardState() {
        return boardState;
    }

    /**
     * Gets the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
