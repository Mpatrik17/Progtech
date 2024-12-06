package org.example;

import java.util.Scanner;

/**
 * Represents a human player in the Connect4 game.
 */
public class HumanPlayer extends Player {
    private final Scanner scanner;

    /**
     * Inicializálja a játékos nevét és szimbólumát.
     *
     * @param symbol a játékos szimbóluma
     * @param name   a játékos neve
     */
    public HumanPlayer(char symbol, String name) {
        super(symbol, name);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Választ egy oszlopot a játékos által.
     *
     * @param board a játéktábla
     * @return a választott oszlop
     */
    @Override
    public int chooseColumn(Board board) {
        int column;
        do {
            System.out.print("Player " + getSymbol()
                    + ", choose a column (0-6): ");
            column = scanner.nextInt();
        } while (!board.validateColumn(column));
        return column;
    }
}
