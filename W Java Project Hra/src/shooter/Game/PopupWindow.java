package shooter.Game;

import javax.swing.*;

import shooter.Menu.ContentPanel;
import shooter.Menu.PlayerInfo;

/**
 * Class PopWindow creates different pop-up windows depending on different actions in game.
 */
public class PopupWindow {

    UserInput userInput;
    Handler handler;
    private SoundEffect soundEffect;

    /**
     * Creates pop-ip window for paused game.
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
     * Creates pop-ip window for gameover.
     *
     * @param handler handler
     */
    public PopupWindow(Handler handler) {
        this.handler = handler;
        handler.pauseGame();

        soundEffect = new SoundEffect();
        soundEffect.setFileGameOver();
        soundEffect.play();
        soundEffect.setVolume(-15);

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
     * Creates pop-ip window for winned game.
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
