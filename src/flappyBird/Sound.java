package flappyBird;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static File file = new File("C:/Users/Konrad/Desktop/java/FlappyBird/Sound/Chicken_Run_--_Opening_Escape.wav"); 
	public static File file2 = new File("C:/Users/Konrad/Desktop/java/FlappyBird/Sound/Polargeist.wav");;
	public static Clip clip, clip2;

	public static void startMenuMusic() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		clip.loop(Clip.LOOP_CONTINUOUSLY);;
	}
	
	public static void stopMenuMusic() {
		clip.stop();
	}
	
	public static void startGameMusic() {
		try {
			clip2 = AudioSystem.getClip();
			clip2.open(AudioSystem.getAudioInputStream(file2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		clip2.loop(Clip.LOOP_CONTINUOUSLY);;
	}
	
	public static void stopGameMusic() {
		clip2.stop();
	}
}
