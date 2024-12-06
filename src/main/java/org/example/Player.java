package org.example;

/**
 * Abstract class representing a player in the Connect4 game.
 * Defines common functionality for both human and AI players.
 */
public abstract class Player {
    private char symbol;
    private String name;

    /**
     * Konstruktor a játékos inicializálásához.
     *
     * @param symbol1 a játékos szimbóluma
     * @param name1 a játékos neve
     */
    public Player(char symbol1, String name1) {
        this.symbol = symbol1;
        this.name = name1;
    }

    /**
     * Visszaadja a játékos nevét.
     *
     * @return a játékos neve
     */
    public String getName() {
        return name;
    }

    /**
     * Visszaadja a játékos szimbólumát.
     *
     * @return a játékos szimbóluma
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Választ egy oszlopot a játékos által.
     *
     * @param board a játéktábla
     * @return a választott oszlop
     */
    public abstract int chooseColumn(Board board);

    /**
     * Sets the name of the player.
     *
     * @param name1 The name to be set for the player.
     */

    public void setName(String name1) {
        this.name = name1;
    }
}
