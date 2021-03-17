package shooter.Menu;

import shooter.Game.GameTimer;
import shooter.Game.Handler;

import java.sql.*;

/**
 * Class Database serves for connection with database, saving and loadings its data.
 */
public class Database {

    private String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11397996";
    private String userName = "sql11397996";
    private String password = "fSHPAdExP1";
    private String commandSELECTBySCORE;
    private String commandSELECTByDAMAGE;
    private String commandSELECTByTIME;
    private String createTABLE;
    private String createTABLE2;
    private Connection connectionDB = DriverManager.getConnection(url, userName, password);
    private Statement statement = connectionDB.createStatement();
    private Statement statement2 = connectionDB.createStatement();
    Handler handler;

    /**
     * Calls the database.
     *
     * @param handler handler
     * @throws SQLException SQLException
     */
    public Database(Handler handler) throws SQLException {
        this.handler = handler;

        // creates the table "highscore" in the database, if it does not exist
        createTABLE = "create table if not exists highscore(" +
                "name varchar(50)," +
                "score int," +
                "damage_taken int," +
                "time varchar(10));";
        statement.executeUpdate(createTABLE);

    }

    /**
     * Creates default table "settings" in the database
     * @throws SQLException
     */
    private void createSettingsTable() throws SQLException {
    	createTABLE2 = "create table if not exists settings(" +
                "name varchar(20)," +
                "value varchar(50));";
        statement2.executeUpdate(createTABLE2);        
        statement2.executeUpdate("insert into settings value('Speed','3');");
        statement2.executeUpdate("insert into settings value('PlayerImagePath','obr/hrac/default/3.png');");
        statement2.executeUpdate("insert into settings value('Difficulty','1');");
        statement2.executeUpdate("insert into settings value('FPS','120');");
        statement2.executeUpdate("insert into settings value('Map','defaultMap.txt');");
	}

	/**
     * Inserts the data into database.
     *
     * @throws SQLException SQLException
     */
    public void insertData() throws SQLException {
        int score = handler.finalScore - (GameTimer.getSecond() + handler.damageTaken);
        String commandINSERT = "insert into highscore value('" + PlayerInfo.txtName.getText() + "'," + score + ", "
                + handler.damageTaken + ", '" + GameTimer.getDdMinute() + ":" + GameTimer.getDdSecond() + "');";

        statement.executeUpdate(commandINSERT);
    }

    /**
     * Lists all the data according to score value.
     *
     * @throws SQLException SQLException
     */
    public void selectDataScore() throws SQLException {
        // allows to swiwtch data ASC / DESC
        if (Panel2_HighScore.scoreMAX[0]) {
            commandSELECTBySCORE = "SELECT * FROM highscore ORDER BY score DESC LIMIT 10;";
            Panel2_HighScore.scoreMAX[0] = false;
        } else {
            commandSELECTBySCORE = "SELECT * FROM highscore ORDER BY score ASC LIMIT 10;";
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
     * Lists all the data according to damage_taken value.
     *
     * @throws SQLException SQLException
     */
    public void selectDataDamage() throws SQLException {
        // allows to swiwtch data ASC / DESC
        if (Panel2_HighScore.damageTakenMIN[0]) {
            commandSELECTByDAMAGE = "SELECT * FROM highscore ORDER BY damage_taken ASC LIMIT 10;";
            Panel2_HighScore.damageTakenMIN[0] = false;
        } else {
            commandSELECTByDAMAGE = "SELECT * FROM highscore ORDER BY damage_taken DESC LIMIT 10;";
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
     * Lists all the data according to time value.
     *
     * @throws SQLException SQLException
     */
    public void selectDataTime() throws SQLException {
        // allows to swiwtch data ASC / DESC
        if (Panel2_HighScore.timeMIN[0]) {
            commandSELECTByTIME = "SELECT * FROM highscore ORDER BY time ASC LIMIT 10;";
            Panel2_HighScore.timeMIN[0] = false;
        } else {
            commandSELECTByTIME = "SELECT * FROM highscore ORDER BY time DESC LIMIT 10;";
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
     * Resets the text in the highscore table.
     */
    public void resetHighScore() {
        Panel2_HighScore.txtPlayerNumber.setText("");
        Panel2_HighScore.txtPlayerName.setText("");
        Panel2_HighScore.txtScore.setText("");
        Panel2_HighScore.txtDamageTaken.setText("");
        Panel2_HighScore.txtTime.setText("");
    }

    /**
     * Updates settings table.
     * @param parameter parameter to update
     * @param value value of the parameter
     */
	public void updateSettings(String parameter, String value) {
		String updateSettings = "update settings set value = '" + value +"' where name = '" + parameter + "';";
		
		try {
			statement2.executeUpdate(updateSettings);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void test() {
		try {
			
			ResultSet tabulky = statement2.executeQuery("show tables");
			while (tabulky.next()) {
	            String name = tabulky.getString(1);
	            System.out.println(name);
	            
	            
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
