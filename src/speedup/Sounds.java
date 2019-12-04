package speedup;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import dialog.DDialog;



public class Sounds {
	TreeMap<String, Clip> soundList;
	DDialog dialog=new DDialog((JFrame)null);
	
	Clip wrongLetter,typedLetter;
	public Sounds(){
			
			soundList=new TreeMap<>();
			try {
				for(char ch='a';ch!='z';ch++){
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("audio\\letters\\"+ch+".wav")));
				soundList.put(String.valueOf(ch), clip);
				}
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				
			}
		
		
			try {
				for(int i=0;i<10;i++){
				Clip clip=AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("audio\\letters\\"+i+".wav")));
				soundList.put(String.valueOf(i), clip);
				}
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				
			}
		try {
			wrongLetter=AudioSystem.getClip();
			wrongLetter.open(AudioSystem.getAudioInputStream(new File("audio\\wrong.wav")));
			
			typedLetter=AudioSystem.getClip();
			typedLetter.open(AudioSystem.getAudioInputStream(new File("audio\\type.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			
		}
	}
	public void playLetter(String letter){
		try {
			soundList.get(letter).loop(0);
			soundList.get(letter).setFramePosition(0);
		} catch (Exception e) {
			
		}

	}
	public void playTypedLetter(){
		typedLetter.setFramePosition(0);
		typedLetter.loop(0);
		
	}
	public void playWrongLetter(){
		wrongLetter.setFramePosition(0);
		wrongLetter.loop(0);
		
	}
}
