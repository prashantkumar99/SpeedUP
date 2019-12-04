package custom;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import dialog.DDialog;

public class DMusic implements Serializable{
	File musicFile;
	public DMusic(File musicfile){
		musicFile=musicfile;
		
	}
	public static Clip openMusic(File musicfile){
		Clip clip=null;
		try {
			
			clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(musicfile));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			DDialog d=new DDialog((JFrame)null);
			d.showErrorDialog((JFrame)null, "SpeedUP could not use this file for music.",null);
		}
		return clip;
	}
	public File getMusicFile(){
		return musicFile;
	}
	public String toString(){
		return musicFile.getName();
	}
}
