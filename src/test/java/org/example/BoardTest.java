package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class BoardTest {
    /**
     * Method under test: {@link Board#checkWinner(char)}
     */
    @Test
    void testCheckWinner() {
        // Arrange, Act and Assert
        assertFalse((new Board()).checkWinner('A'));
        assertTrue((new Board()).checkWinner(' '));
    }

    /**
     * Method under test: {@link Board#checkWinner(char)}
     */
    @Test
    void testCheckWinner2() {
        // Arrange
        Board board = new Board();
        board.setBoardState(new char[][]{"A\u0003\u0006\u0003".toCharArray()});

        // Act and Assert
        assertFalse(board.checkWinner('A'));
    }

    /**
     * Method under test: {@link Board#checkWinner(char)}
     */
    @Test
    void testCheckWinner3() {
        // Arrange
        Board board = new Board();
        board.setBoardState(new char[][]{"AA\u0006\u0003".toCharArray()});

        // Act and Assert
        assertFalse(board.checkWinner('A'));
    }

    /**
     * Method under test: {@link Board#checkWinner(char)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCheckWinner4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at org.example.Board.checkWinner(Board.java:112)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Board board = new Board();
        board.setBoardState(new char[][]{});

        // Act
        board.checkWinner('A');
    }

    /**
     * Method under test: {@link Board#checkWinner(char)}
     */
    @Test
    void testCheckWinner5() {
        // Arrange
        Board board = new Board();
        board.setBoardState(new char[][]{"AAA\u0003".toCharArray()});

        // Act and Assert
        assertFalse(board.checkWinner('A'));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Board#setBoardState(char[][])}
     *   <li>{@link Board#getBoardState()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Board board = new Board();
        char[][] state = new char[][]{"A A ".toCharArray()};

        // Act
        board.setBoardState(state);

        // Assert that nothing has changed
        assertSame(state, board.getBoardState());
    }

    /**
     * Method under test: default or parameterless constructor of {@link Board}
     */
    @Test
    void testNewBoard() {
        // Arrange, Act and Assert
        char[][] boardState = (new Board()).getBoardState();
        assertEquals(6, boardState.length);
        assertArrayEquals("       ".toCharArray(), boardState[0]);
        assertArrayEquals("       ".toCharArray(), boardState[1]);
        assertArrayEquals("       ".toCharArray(), boardState[2]);
        assertArrayEquals("       ".toCharArray(), boardState[3]);
        assertArrayEquals("       ".toCharArray(), boardState[4]);
        assertArrayEquals("       ".toCharArray(), boardState[5]);
    }

    /**
     * Method under test: {@link Board#display()}
     */
    @Test
    void testDisplay() {
        // Arrange: Létrehozzuk a Board objektumot
        Board board = new Board();

        // Először átirányítjuk a kimenetet egy ByteArrayOutputStream-ra, hogy elkapjuk a display() metódus kimenetét
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act: Meghívjuk a display() metódust
        board.display();

        // Elvárt kimenet egy üres táblára, ahol minden sor üres helyeket (' ') tartalmaz
        String expectedOutput = "| | | | | | | |\n| | | | | | | |\n| | | | | | | |\n| | | | | | | |\n| | | | | | | |\n| | | | | | | |\n---------------\n";

        // Töröljük a sortöréseket a kimenetből, hogy biztosan ne legyenek különbségek
        String actualOutput = outputStream.toString().replace("\r\n", "\n");

        // Assert: Ellenőrizzük, hogy a kimenet megegyezik az elvárt kimenettel
        assertEquals(expectedOutput, actualOutput);
    }
}
