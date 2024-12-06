package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class GameStateTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link GameState#GameState(Player, Player, Player, int, String)}
     *   <li>{@link GameState#getBoardState()}
     *   <li>{@link GameState#getCurrentPlayer()}
     *   <li>{@link GameState#getPlayer1()}
     *   <li>{@link GameState#getPlayer2()}
     *   <li>{@link GameState#getTurn()}
     *   <li>{@link GameState#getWinner()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        AiPlayer playerOne = new AiPlayer('A', "Name");

        AiPlayer playerTwo = new AiPlayer('A', "Name");

        AiPlayer gameWinner = new AiPlayer('A', "Name");

        // Act
        GameState actualGameState = new GameState(playerOne, playerTwo, gameWinner, 1, "Game Board State");
        String actualBoardState = actualGameState.getBoardState();
        Player actualCurrentPlayer = actualGameState.getCurrentPlayer();
        Player actualPlayer1 = actualGameState.getPlayer1();
        Player actualPlayer2 = actualGameState.getPlayer2();
        int actualTurn = actualGameState.getTurn();

        // Assert
        assertEquals("Game Board State", actualBoardState);
        assertEquals(1, actualTurn);
        assertSame(gameWinner, actualCurrentPlayer);
        assertSame(playerOne, actualPlayer1);
        assertSame(playerTwo, actualPlayer2);
        assertSame(gameWinner, actualGameState.getWinner());
    }
}
