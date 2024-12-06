package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public final class Main {
    private  Main() {
    }
    public static void main(final String[] args) throws SQLException {
        DatabaseManager.initializeDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name for Player 1: ");
        String player1Name = scanner.nextLine();
        Player player1 = new HumanPlayer('R', player1Name);

        System.out.print("Enter name for Player 2: ");
        String player2Name = scanner.nextLine();
        Player player2 = new AiPlayer('B', player2Name);

        Game game = new Game(player1, player2);
        game.start();
    }
}
