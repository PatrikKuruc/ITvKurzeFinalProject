package shooter.Menu;

import shooter.Hra.Casovac;
import shooter.Hra.Handler;

import java.sql.*;

/**
 * Trieda Database sluzi na prepojenie s databazou, ukladanie a nacitavania dat.
 */
public class Database {

    private String url = "jdbc:mysql://localhost:3306/score";
    private String userName = "tester";
    private String password = "tester123";
    Handler handler;
    private String commandSELECTBySCORE;
    private String commandSELECTByDAMAGE;
    private String commandSELECTByTIME;

    public Database(Handler handler) throws SQLException {
        this.handler = handler;
    }

    /**
     * Vlozi data do databazy.
     *
     * @throws SQLException SQL vynimka
     */
    public void insertData() throws SQLException {
        Connection connectionDB = DriverManager.getConnection(url, userName, password);
        Statement statement = connectionDB.createStatement();

        String commandINSERT = "insert into highscore value('" + PlayerInfo.txtName.getText() + "'," + handler.score + ", "
                + handler.zranenia + ", '" + Casovac.getDdMinute() + ":" + Casovac.getDdSecond() + "');";

        statement.executeUpdate(commandINSERT);
    }

    /**
     * Vypise vsetky data podla najvyssieho score z databazy do tabulky highscore.
     *
     * @throws SQLException SQL vynimka
     */
    public void selectDataScore() throws SQLException {
        Connection connectionDB = DriverManager.getConnection(url, userName, password);
        Statement statement = connectionDB.createStatement();

        // umoznuje preklik tlacitka SCORE pre vypisovanie MAX / MIN score
        if(Panel2_HighScore.scoreMAX[0]) {
            commandSELECTBySCORE = "SELECT * FROM highscore ORDER BY score DESC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.scoreMAX[0] = false;
        } else {
            commandSELECTBySCORE = "SELECT * FROM highscore ORDER BY score ASC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.scoreMAX[0] = true;
        }

        ResultSet results = statement.executeQuery(commandSELECTBySCORE);
        int number = 0;

        while (results.next()) {
            String name = results.getString("name");
            int score = results.getInt("score");
            int damageTaken = results.getInt("damage_taken");
            String time = results.getString("time");
            number++;

            Panel2_HighScore.txtPlayerNumber.append("       " + number + ".\n\n");
            Panel2_HighScore.txtPlayerName.append("            " + name + "\n\n");
            Panel2_HighScore.txtScore.append("              " + score + "\n\n");
            Panel2_HighScore.txtDamageTaken.append("            " + damageTaken + "\n\n");
            Panel2_HighScore.txtTime.append("          " + time + "\n\n");

        }
    }

    /**
     * Vypise vsetky data podla najmensieho zranenia z databazy do tabulky highscore.
     *
     * @throws SQLException SQL vynimka
     */
    public void selectDataDamage() throws SQLException {
        Connection connectionDB = DriverManager.getConnection(url, userName, password);
        Statement statement = connectionDB.createStatement();

        // umoznuje preklik tlacitka DMG Taken pre vypisovanie MAX / MIN zranenia
        if(Panel2_HighScore.damageTakenMIN[0]) {
            commandSELECTByDAMAGE = "SELECT * FROM highscore ORDER BY damage_taken ASC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.damageTakenMIN[0] = false;
        } else {
            commandSELECTByDAMAGE = "SELECT * FROM highscore ORDER BY damage_taken DESC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.damageTakenMIN[0] = true;
        }

        ResultSet results = statement.executeQuery(commandSELECTByDAMAGE);
        int number = 0;

        while (results.next()) {
            String name = results.getString("name");
            int score = results.getInt("score");
            int damageTaken = results.getInt("damage_taken");
            String time = results.getString("time");
            number++;

            Panel2_HighScore.txtPlayerNumber.append("       " + number + ".\n\n");
            Panel2_HighScore.txtPlayerName.append("            " + name + "\n\n");
            Panel2_HighScore.txtScore.append("              " + score + "\n\n");
            Panel2_HighScore.txtDamageTaken.append("            " + damageTaken + "\n\n");
            Panel2_HighScore.txtTime.append("          " + time + "\n\n");

        }
    }

    /**
     * Vypise vsetky data podla najkratsieho casu z databazy do tabulky highscore.
     *
     * @throws SQLException SQL vynimka
     */
    public void selectDataTime() throws SQLException {
        Connection connectionDB = DriverManager.getConnection(url, userName, password);
        Statement statement = connectionDB.createStatement();

        // umoznuje preklik tlacitka TIME pre vypisovanie MAX / MIN casu
        if(Panel2_HighScore.timeMIN[0]) {
            commandSELECTByTIME = "SELECT * FROM highscore ORDER BY time ASC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.timeMIN[0] = false;
        } else {
            commandSELECTByTIME = "SELECT * FROM highscore ORDER BY time DESC LIMIT 10;";        // LIMIT 10 - prvych 10 dat
            Panel2_HighScore.timeMIN[0] = true;
        }

        ResultSet results = statement.executeQuery(commandSELECTByTIME);
        int number = 0;

        while (results.next()) {
            String name = results.getString("name");
            int score = results.getInt("score");
            int damageTaken = results.getInt("damage_taken");
            String time = results.getString("time");
            number++;

            Panel2_HighScore.txtPlayerNumber.append("       " + number + ".\n\n");
            Panel2_HighScore.txtPlayerName.append("            " + name + "\n\n");
            Panel2_HighScore.txtScore.append("              " + score + "\n\n");
            Panel2_HighScore.txtDamageTaken.append("            " + damageTaken + "\n\n");
            Panel2_HighScore.txtTime.append("          " + time + "\n\n");

        }
    }

    /**
     * Vycisti tabulku highscore.
     */
    public void resetHighScore() {
        Panel2_HighScore.txtPlayerNumber.setText("");
        Panel2_HighScore.txtPlayerName.setText("");
        Panel2_HighScore.txtScore.setText("");
        Panel2_HighScore.txtDamageTaken.setText("");
        Panel2_HighScore.txtTime.setText("");
    }
}
