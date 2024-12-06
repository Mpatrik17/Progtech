package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents the game logic for Connect4.
 * Handles the game flow, alternates turns between players,
 * checks for winners, and saves or loads game states.
 */
public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private int turn;

    private static final int MAX_TURNS = 42;
    private static final String SAVE_FILE = "game_state.txt";
    private DatabaseManager dbManager;

    /**
     * Constructs a new game with the specified players.
     *
     * @param firstPlayer  the first player to play
     * @param secondPlayer the second player to play
     */
    public Game(Player firstPlayer, Player secondPlayer) {
        this.board = new Board();
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
        this.currentPlayer = firstPlayer;
        this.turn = 1;
        this.dbManager = new DatabaseManager();
    }

    /**
     * Starts the game, alternates players, and determines the winner.
     * This method also handles saving and loading the game state.
     */
    public void start() {
        boolean winner = false;
        Scanner scanner = new Scanner(System.in);

        // Prompt to load a saved game if requested
        System.out.print("Do you want to load a previous game? (yes/no): ");
        String loadResponse = scanner.nextLine();
        if (loadResponse.equalsIgnoreCase("yes")) {
            if (loadGameState()) {
                System.out.println("Game loaded successfully!");
            } else {
                System.out.println("No saved game found. Starting a new game.");
            }
        }


        while (!winner && turn <= MAX_TURNS) {
            board.display();

            int column = currentPlayer.chooseColumn(board);
            board.dropChecker(column, currentPlayer.getSymbol());
            winner = board.checkWinner(currentPlayer.getSymbol());

            if (!winner) {
                System.out.print("Do you want to save"
                        + " the current game state? (yes/no): ");
                String saveResponse = scanner.nextLine();
                if (saveResponse.equalsIgnoreCase("yes")) {
                    saveGameState();
                }

                switchPlayer();
                turn++;
            }
        }

        board.display();


        if (winner) {
            System.out.println("Player " + currentPlayer.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }


        try {
            dbManager.displayTopPlayers();
        } catch (SQLException e) {
            System.out.println("Error displaying top players: "
                    + e.getMessage());
        }

        // Prompt to save the final game state
        System.out.print("Do you want to save "
                + "the final game state? (yes/no): ");
        String saveFinalResponse = scanner.nextLine();
        if (saveFinalResponse.equalsIgnoreCase("yes")) {
            saveGameState();
        }
    }

    /**
     * Switches the current player to the other player.
     */
    void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Saves the current game state to a text file.
     * The game state includes player names,
     * the current turn, and the board state.
     */
    private void saveGameState() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(SAVE_FILE))) {
            writer.write(player1.getName() + "\n");
            writer.write(player2.getName() + "\n");
            writer.write(currentPlayer.getName() + "\n");
            writer.write(turn + "\n");

            char[][] boardState = board.getBoardState();
            for (char[] row : boardState) {
                writer.write(new String(row) + "\n");
            }

            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }

    /**
     * Loads the game state from a text file.
     * The game state includes player names,
     * the current turn, and the board state.
     *
     * @return true if the game state was
     * loaded successfully, false otherwise
     */
    boolean loadGameState() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(SAVE_FILE))) {
            player1.setName(reader.readLine());
            player2.setName(reader.readLine());
            String currentPlayerName = reader.readLine();
            turn = Integer.parseInt(reader.readLine());

            char[][] boardState = new char[6][7];
            for (int i = 0; i < 6; i++) {
                boardState[i] = reader.readLine().toCharArray();
            }

            board.setBoardState(boardState);
            currentPlayer =
                    currentPlayerName.equals(player1.getName())
                            ? player1 : player2;

            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading game state: " + e.getMessage());
            return false;
        }
    }
}
