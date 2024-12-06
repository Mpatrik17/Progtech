package org.example;

import java.util.Random;

/**
 * AI player for the Connect4 game.
 */
public class AiPlayer extends Player {
    private Random random;

    /**
     * Inicializálja az AI játékost szimbólummal és névvel.
     *
     * @param symbol a játékos szimbóluma
     * @param name   a játékos neve
     */
    public AiPlayer(char symbol, String name) {
        super(symbol, name);
        this.random = new Random();
    }

    /**
     * Választ egy oszlopot az AI által.
     *
     * @param board a játéktábla
     * @return a választott oszlop
     */
    @Override
    public int chooseColumn(Board board) {
        int column;
        do {
            column = random.nextInt(7);
        } while (!board.validateColumn(column));
        System.out.println("AI Player " + getSymbol()
                + " chooses column " + column);
        return column;
    }
}

