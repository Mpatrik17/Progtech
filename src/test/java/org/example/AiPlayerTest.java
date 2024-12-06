package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AiPlayerTest {
    /**
     * Method under test: {@link AiPlayer#chooseColumn(Board)}
     */
    @Test
    void testChooseColumn() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        AiPlayer aiPlayer = new AiPlayer('A', "Name");

        // Act
        aiPlayer.chooseColumn(new Board());
    }

    /**
     * Method under test: {@link AiPlayer#AiPlayer(char, String)}
     */
    @Test
    void testNewAiPlayer() {
        // Arrange and Act
        AiPlayer actualAiPlayer = new AiPlayer('A', "Name");

        // Assert
        assertEquals("Name", actualAiPlayer.getName());
        assertEquals('A', actualAiPlayer.getSymbol());
    }
}
