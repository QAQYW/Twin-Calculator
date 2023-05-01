package ui.music;

import java.applet.Applet;
import java.applet.AudioClip;
import ui.application.TwinCalcApp;
//import java.net.URL;

//import jmp123.player.*;

@SuppressWarnings("removal")
public class PlayMusic {
	public static void playMusic(String path) {
		java.net.URL url = TwinCalcApp.class.getResource(path);
		AudioClip aau = Applet.newAudioClip(url);
		aau.play();// ≤•∑≈“Ù∆µ
	}
	
	public static void playButtonMusic() {
		playMusic("/rsc/bgms/button.wav");
	}
	
	public static void playCorrectMusic() {
		playMusic("/rsc/bgms/correct.wav");
	}
	
	public static void playWrongMusic() {
		playMusic("/rsc/bgms/wrong.wav");
	}
}
