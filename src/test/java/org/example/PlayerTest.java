package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlayerTest {
    /**
     * Method under test: {@link Player#getName()}
     */
    @Test
    void testGetName() {
        // Arrange, Act and Assert
        assertEquals("Name", (new AiPlayer('A', "Name")).getName());
    }

    /**
     * Method under test: {@link Player#getSymbol()}
     */
    @Test
    void testGetSymbol() {
        // Arrange, Act and Assert
        assertEquals('A', (new AiPlayer('A', "Name")).getSymbol());
    }

    /**
     * Method under test: {@link Player#setName(String)}
     */
    @Test
    void testSetName() {
        // Arrange
        AiPlayer aiPlayer = new AiPlayer('A', "Name");

        // Act
        aiPlayer.setName("Name1");

        // Assert
        assertEquals("Name1", aiPlayer.getName());
    }
}
