package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GameTest {
    /**
     * Method under test: {@link Game#start()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStart() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        Game game = null;

        // Act
        game.start();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link Game#switchPlayer()}
     */
    @Test
    void testSwitchPlayer() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Game.board
        //     Game.currentPlayer
        //     Game.player1
        //     Game.player2
        //     Game.turn

        // Arrange
        AiPlayer firstPlayer = new AiPlayer('A', "Name");

        // Act
        (new Game(firstPlayer, new AiPlayer('A', "Name"))).switchPlayer();
    }

    /**
     * Method under test: {@link Game#loadGameState()}
     */
    @Test
    void testLoadGameState() {
        // Arrange
        AiPlayer firstPlayer = new AiPlayer('A', "Name");

        // Act and Assert
        assertTrue((new Game(firstPlayer, new AiPlayer('A', "Name"))).loadGameState());
    }

    /**
     * Method under test: {@link Game#loadGameState()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadGameState2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.example.Player.setName(String)" because "this.player1" is null
        //       at org.example.Game.loadGameState(Game.java:122)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new Game(null, new AiPlayer('A', "Name"))).loadGameState();
    }

    /**
     * Method under test: {@link Game#loadGameState()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadGameState3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.example.Player.setName(String)" because "this.player2" is null
        //       at org.example.Game.loadGameState(Game.java:123)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new Game(new AiPlayer('A', "Name"), null)).loadGameState();
    }

    /**
     * Method under test: {@link Game#Game(Player, Player)}
     */
    @Test
    void testNewGame() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Game.board
        //     Game.currentPlayer
        //     Game.player1
        //     Game.player2
        //     Game.turn

        // Arrange
        AiPlayer firstPlayer = new AiPlayer('A', "Name");

        // Act
        new Game(firstPlayer, new AiPlayer('A', "Name"));

    }
}
