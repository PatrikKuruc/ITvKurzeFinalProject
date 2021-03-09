package shooter.Hra;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Trieda SoundEffect pridava zvukove efekty do hry.
 */
public class SoundEffect {

    private Clip clip;

    public SoundEffect(){
    }

    /**
     * Pridava uvodnu zvucku pri otvorenom menu.
     */
    public void setFileMenuMusic(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\menu_music.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            setVolume(-20);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk strelby hlavnej postavy.
     */
    public void setFileShoot(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\shoot_sound.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk pri zodvihnuti itemu.
     */
    public void setFileItemPickUp(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\item_pick_up.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk pri prehrani hry.
     */
    public void setFileGameOver(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\gameover.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk pri vyhrani hry.
     */
    public void setFileYouWon(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\you_won.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk ked zomrie enemy
     */
    public void setFileZomebieDeath(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\zombie_death.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk ked sa spawn final boss
     */
    public void setFileFinalBossSpawn(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\final_boss_spawn.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pridava zvuk ked sa spawn final boss
     */
    public void setFileButtonClick(){
        File file = new File("D:\\W Java Project Hra_Current\\sound\\button_click.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Znizi(+) / zvysi(-) hlasitost daneho zvuku
     *
     * @param value hodnota hlasitosti
     */
    public void setVolume(int value){
        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(value);
    }

    /**
     * Spusti zuvkovy efekt.
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Vypne zvukovy efekt.
     */
    public void stopMusic(){
        clip.stop();
    }
}
