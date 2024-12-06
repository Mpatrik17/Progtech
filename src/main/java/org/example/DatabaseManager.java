
package org.example;


import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Kezeli az adatbázist és a játékosokkal kapcsolatos műveleteket.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:connect4.db";

    /**
     * Kapcsolatot nyit az adatbázishoz.
     *
     * @return az adatbázis kapcsolat
     * @throws SQLException ha hiba történik a kapcsolat nyitásakor
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Inicializálja az adatbázist, létrehozza a szükséges táblákat.
     *
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Létrehozza a players táblát, ha nem létezik
            String createPlayersTable = "CREATE TABLE IF NOT EXISTS players ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "wins INTEGER DEFAULT 0)";
            stmt.execute(createPlayersTable);

            // Létrehozza a games táblát, ha nem létezik
            String createGamesTable = "CREATE TABLE IF NOT EXISTS games ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "player1_id INTEGER, "
                    + "player2_id INTEGER, "
                    + "winner_id INTEGER, "
                    + "turn INTEGER, "  // Itt adjuk hozzá a 'turn' oszlopot
                    + "board_state TEXT, "
                    + "FOREIGN KEY(player1_id) REFERENCES players(id), "
                    + "FOREIGN KEY(player2_id) REFERENCES players(id), "
                    + "FOREIGN KEY(winner_id) REFERENCES players(id))";
            stmt.execute(createGamesTable);
        }
    }


    /**
     * Ment egy új játékost az adatbázisba.
     *
     * @param playerName a játékos neve
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    public static void savePlayer(String playerName) throws SQLException {
        String sql = "INSERT INTO players (name, wins) VALUES (?, 0)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        }
    }

    /**
     * Frissíti a játékos győzelmeinek számát.
     *
     * @param playerName a játékos neve
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    public static void updatePlayerWins(String playerName)
            throws SQLException {
        String sql = "UPDATE players SET wins = wins + 1 WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        }
    }

    /**
     * Ment egy játékot az adatbázisba.
     *
     * @param player1 az első játékos
     * @param player2 a második játékos
     * @param winner a győztes játékos
     * @param turn az aktuális kör
     * @param boardState az aktuális játéktábla állapota
     * @throws SQLException ha hiba
     * történik az adatbázis műveletek közben
     */
    public static void saveGame(Player player1, Player player2,
         final Player winner, int turn, String boardState)
            throws SQLException {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Do you want to save"
                + " the current game state? (yes/no): ");
        String response = scanner.nextLine();

        // Ha a válasz igen, akkor mentse el a játékot
        if (response.equalsIgnoreCase("yes")) {
            String sql = "INSERT INTO games (player1_id,"
                    + " player2_id, winner_id, turn,"
                    + " board_state) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, getPlayerId(player1.getName()));
                pstmt.setInt(2, getPlayerId(player2.getName()));
                pstmt.setInt(3, getPlayerId(winner.getName()));
                pstmt.setInt(4, turn);
                pstmt.setString(5, boardState);
                pstmt.executeUpdate();
            }
            updatePlayerWins(winner.getName());
            System.out.println("Game saved successfully!");
        } else {
            System.out.println("Game was not saved.");
        }
    }




    /**
     * Betölti a legutóbbi játék állapotát az adatbázisból.
     *
     * @return a legutóbbi játék állapota, vagy null, ha nincs elérhető játék
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    public GameState loadGameState() throws SQLException {
        String sql = "SELECT player1_id, player2_id, winner_id,"
                + " turn, board_state FROM games ORDER BY id DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Player player1 = getPlayerById(rs.getInt("player1_id"));
                Player player2 = getPlayerById(rs.getInt("player2_id"));
                Player winner = getPlayerById(rs.getInt("winner_id"));
                int turn = rs.getInt("turn");
                String boardState = rs.getString("board_state");
                return new GameState(player1, player2,
                        winner, turn, boardState);
            }
        }
        return null;
    }

    /**
     * Visszaadja a játékos ID-ját a neve alapján.
     *
     * @param playerName a játékos neve
     * @return a játékos ID-ja
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    private static int getPlayerId(String playerName)
            throws SQLException {
        String sql = "SELECT id FROM players WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    /**
     * Visszaadja a játékos objektumot az ID alapján.
     *
     * @param playerId a játékos ID-ja
     * @return a játékos objektuma
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    private static Player getPlayerById(int playerId)
            throws SQLException {
        String sql = "SELECT name FROM players WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new HumanPlayer('R', "hF");
            }
        }
        return null;
    }

    /**
     * Kiírja a top 3 legtöbb győzelmet szerzett játékost.
     *
     * @throws SQLException ha hiba történik az adatbázis műveletek közben
     */
    public static void displayTopPlayers() throws SQLException {
        String sql = "SELECT name, wins FROM players"
                + " ORDER BY wins DESC LIMIT 3";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Top 3 players:");
            int rank = 1;
            while (rs.next()) {
                System.out.println(rank + ". " + rs.getString("name")
                        + " with " + rs.getInt("wins") + " wins");
                rank++;
            }
        }

    }
}
