package shooter.Hra;

import javax.swing.*;
import java.io.FileNotFoundException;

import shooter.Menu.ContentPanel;
import shooter.Menu.PlayerInfo;

public class PopupWindow {

    UserInput userInput;
    Handler handler;
    private SoundEffect soundEffect;

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
                "Press 'OK' to continue.", "PAUSE", JOptionPane.INFORMATION_MESSAGE);

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

        soundEffect = new SoundEffect();
        soundEffect.setFileGameOver();
        soundEffect.play();
        soundEffect.setVolume(-30);
        
        soundEffect = new SoundEffect();
        soundEffect.setFileLoser();
        soundEffect.play();

        JOptionPane.showMessageDialog(null, "You lost!\n" +
                "Press 'OK' to continue.", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

        soundEffect.setFileButtonClick();
        soundEffect.play();

        ContentPanel.game.shutDown();
        PlayerInfo playerInfo = new PlayerInfo(handler);
        playerInfo.run();

    }

    /**
     * Zostorji pop-up okno pri vyhrani hry.
     *
     * @param handler handler.
     */
    public PopupWindow(Handler handler, int zivotMama) {
        this.handler = handler;
        handler.pauseGame();

        soundEffect = new SoundEffect();
        soundEffect.setFileYouWon();
        soundEffect.play();
        soundEffect.setVolume(-15);
        
        soundEffect = new SoundEffect();
        soundEffect.setFileWinner();
        soundEffect.play();

        JOptionPane.showMessageDialog(null, "YOU WON!\n" +
                "Press 'OK' to continue.", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);

        soundEffect.setFileButtonClick();
        soundEffect.play();

        ContentPanel.game.shutDown();
        PlayerInfo playerInfo = new PlayerInfo(handler);
        playerInfo.run();

    }
}
