package gameframework;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound sound1 = new Sound("/dodgy.wav");
	public static final Sound sound2 = new Sound("/brain.wav");
	public static final Sound sound3 = new Sound("/blaster.wav");
	public static final Sound sound4 = new Sound("/020.wav");
	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void play() {
		clip.play();
//		try {
//			new Thread() {
//				public void run() {
//					clip.play();
//				}
//			}.start();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}
}