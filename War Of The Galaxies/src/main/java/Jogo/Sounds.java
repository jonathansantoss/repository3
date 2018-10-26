package Jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sounds {
	
	public Sounds() {
	}
	
	public void setSound(String sound) {
		URL url = getClass().getResource(sound+".wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();
	}
}
