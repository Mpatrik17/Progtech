package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {
    /**
     * Method under test: {@link HumanPlayer#chooseColumn(Board)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChooseColumn() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        HumanPlayer humanPlayer = null;
        Board board = null;

        // Act
        int actualChooseColumnResult = humanPlayer.chooseColumn(board);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link HumanPlayer#HumanPlayer(char, String)}
     */
    @Test
    void testNewHumanPlayer() {
        // Arrange and Act
        HumanPlayer actualHumanPlayer = new HumanPlayer('A', "Name");

        // Assert
        assertEquals("Name", actualHumanPlayer.getName());
        assertEquals('A', actualHumanPlayer.getSymbol());
    }
}
