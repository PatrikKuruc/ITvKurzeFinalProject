package shooter.Hra;

import javax.swing.*;
import java.io.FileNotFoundException;

import shooter.Menu.ContentPanel;
import shooter.Menu.PlayerInfo;

public class PopupWindow {

    UserInput userInput;
    Handler handler;

    /**
     * Zostroji pop-up okno pre pauznutie hry
     *
     * @param userInput user input
     * @param handler   handler
     */
    public PopupWindow(UserInput userInput, Handler handler) {
        this.userInput = userInput;
        this.handler = handler;
        handler.pauseGame();

        JOptionPane.showMessageDialog(null, "Game is paused!\n" +
                "Press 'OK' to continue.", "PAUSE", JOptionPane.PLAIN_MESSAGE);

        handler.pauseGame();
    }

    /**
     * Zostorji pop-up okno pre gameover
     *
     * @param handler
     */
    public PopupWindow(Handler handler) {
        this.handler = handler;
        handler.pauseGame();


        int response = JOptionPane.showConfirmDialog(null, "GAME OVER\n" +
                "Do you want to play again?", "GAME OVER", JOptionPane.YES_NO_OPTION);

        // ak stlacime YES, hra sa vypne a zapne odznova
        if (response == JOptionPane.YES_OPTION) {
            ContentPanel.game.shutDown();
            try {
                ContentPanel.game = new Game();
                ContentPanel.game.run();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // ak stalcime NO, hra sa vypne a zapne sa nove okno PlayerInfo
        } else if (response == JOptionPane.NO_OPTION) {
            ContentPanel.game.shutDown();
            PlayerInfo playerInfo = new PlayerInfo(handler);
            playerInfo.run();
        }
    }
}
