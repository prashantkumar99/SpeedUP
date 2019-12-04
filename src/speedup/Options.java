package speedup;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import custom.DMusic;
import panel.OptionsPanel;

public class Options implements ActionListener,ItemListener{
	final Home home;
	final OptionsPanel pnl;
	
	JFileChooser musicfile;
	
	Clip clip=null;
	
	public static final DMusic DEFAULT_MUSIC=new DMusic(new File("audio\\Default Music.wav"));
	
	public Options(Home hOme,OptionsPanel Pnl) {
		this.home=hOme;
		this.pnl=Pnl;
		
		pnl.setActionLis(this);
		
		pnl.getMusicBox().addItem(DEFAULT_MUSIC);
		pnl.getMusicBox().addItemListener(this);
		pnl.getMusicChBox().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(pnl.getMusicChBox().isSelected())home.playMusic();
				else home.stopMusic();
			}
		});
		
		
		musicfile=new JFileChooser();
		musicfile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		musicfile.setFileFilter(new FileNameExtensionFilter("WAV Music Files", "wav"));
		musicfile.setMultiSelectionEnabled(false);
		musicfile.setDialogTitle("Choose Music File");
	}
	void setUsername(String user){
		pnl.getUsername().setText(user);
	}
	void saveSettings(){
					File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\options");
					File mfile=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\musiclist");
					try {
						file.createNewFile();
						mfile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String str="Music="+pnl.getMusicChBox().isSelected()+"\r\n"
							+ "Key Stroke Sound="+pnl.getKeyStrokeSoundChBox().isSelected()+"\r\n"
							+ "Wrong Letter Sound="+pnl.getWrongLetterSoundChBox().isSelected()+"\r\n"
							+ "Show Wrong Letter="+pnl.getShowWrongLetterChBox().isSelected()+"\r\n"
							+ "Play Letter Sound="+pnl.getPlayLetterSoundChBox().isSelected()+"\r\n"
							+ "Bold="+pnl.getBoldBtn().isSelected()+"\r\n"
							+ "Font Size="+pnl.getFontSizeBox().getSelectedItem().toString()+"\r\n"
							+ "Font Selected="+pnl.getFontBox().getSelectedIndex()+"\r\n"
							+ "Selected Music="+pnl.getMusicBox().getSelectedIndex()+"\r\n"
							+ "Show Cursor="+pnl.getShowCursorInPara().isSelected();
					try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(mfile));
							FileWriter fw=new FileWriter(file)){
						fw.write(str);
						oos.writeObject(pnl.getMusicBox().getModel());
					} catch (IOException e) {
						home.dialog.showErrorDialog(home.frm, "Could not save Options data.", null);
						e.printStackTrace();
					}
	}
	void loadSettings(){
		File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\options");
		File mfile=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\musiclist");
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(mfile))){
			ComboBoxModel<DMusic> mmodel=(ComboBoxModel<DMusic>) ois.readObject();
			pnl.getMusicBox().setModel(mmodel);
			List<String> list=Files.readAllLines(file.toPath(),StandardCharsets.US_ASCII);
			for(String value:list){
				if(value.startsWith("Music")){
					pnl.getMusicChBox().setSelected(Boolean.valueOf(value.substring(6)));
					
					
				}
				if(value.startsWith("Key Stroke Sound"))pnl.getKeyStrokeSoundChBox().setSelected(Boolean.valueOf(value.substring(17)));
				if(value.startsWith("Wrong Letter Sound"))pnl.getWrongLetterSoundChBox().setSelected(Boolean.valueOf(value.substring(19)));
				if(value.startsWith("Show Wrong Letter"))pnl.getShowWrongLetterChBox().setSelected(Boolean.valueOf(value.substring(18)));
				if(value.startsWith("Play Letter Sound"))pnl.getPlayLetterSoundChBox().setSelected(Boolean.valueOf(value.substring(18)));
				if(value.startsWith("Bold"))pnl.getBoldBtn().setSelected(Boolean.valueOf(value.substring(5)));
				if(value.startsWith("Font Size"))pnl.getFontSizeBox().setSelectedItem(Integer.valueOf(value.substring(10)));
				if(value.startsWith("Font Selected"))pnl.getFontBox().setSelectedIndex(Integer.valueOf(value.substring(14)));
				if(value.startsWith("Selected Music"))pnl.getMusicBox().setSelectedIndex(Integer.valueOf(value.substring(15)));
				if(value.startsWith("Show Cursor"))pnl.getShowCursorInPara().setSelected(Boolean.valueOf(value.substring(12)));
			}
			if(isMusicEnabled())home.playMusic();
			DMusic d=pnl.getMusicBox().getItemAt(pnl.getMusicBox().getSelectedIndex());
			if(d.toString().equals("Default Music.wav"))pnl.setRemoveBtnEnabled(false);
		}catch (Exception e) {
			loadDefaultSettings();
			e.printStackTrace();
		}
	}
	void saveDefaultSettings(){
		loadDefaultSettings();
		saveSettings();
	}
	void loadDefaultSettings(){
		pnl.getMusicChBox().setSelected(true);
		pnl.getKeyStrokeSoundChBox().setSelected(true);
		pnl.getWrongLetterSoundChBox().setSelected(true);
		pnl.getFontBox().setSelectedIndex(0);
		pnl.getMusicBox().removeItemListener(this);
		pnl.getMusicBox().removeAllItems();
		pnl.getMusicBox().addItem(DEFAULT_MUSIC);
		pnl.getFontSizeBox().setSelectedIndex(5);
		pnl.getBoldBtn().setSelected(true);
		pnl.getMusicBox().addItemListener(this);
		pnl.getShowWrongLetterChBox().setSelected(true);
		pnl.getPlayLetterSoundChBox().setSelected(true);
		pnl.getShowCursorInPara().setSelected(true);
		if(isMusicEnabled())home.playMusic();
	}
	void addMusic(){
		if(musicfile.showDialog(home.frm,"Select")==JFileChooser.APPROVE_OPTION){
			File f=musicfile.getSelectedFile();
			//if(f.getName().endsWith(".wav")){
				DMusic d=new DMusic(f);
				boolean exist=false;
				for(int i=0;i<pnl.getMusicBox().getItemCount();i++){
					if(pnl.getMusicBox().getItemAt(i).getMusicFile().equals(d.getMusicFile()))exist=true;
				}
				if(exist)home.dialog.showInfoDialog(home.frm,"This is music is already present.","Info");
				else {
					pnl.getMusicBox().addItem(d);
					pnl.getMusicBox().setSelectedItem(d);	
				}
				
			//}
			//else home.dialog.showInfoDialog(home.frm,"Choose only wav music file.",null);
		}
	}
	void removeMusic(){
		pnl.getMusicBox().removeItemAt(pnl.getMusicBox().getSelectedIndex());
		pnl.getMusicBox().setSelectedItem(DEFAULT_MUSIC);
	}
	
	public boolean isMusicEnabled(){
		return pnl.getMusicChBox().isSelected();
	}
	public boolean isKeyStrokeSoundEnabled(){
		return pnl.getKeyStrokeSoundChBox().isSelected();
	}
	public boolean isWrongLetterSoundEnabled(){
		return pnl.getWrongLetterSoundChBox().isSelected();
	}
	public boolean isPlayLetterSoundEnabled(){
		return pnl.getPlayLetterSoundChBox().isSelected();
	}
	public boolean isShowWrongLetterEnabled(){
		return pnl.getShowWrongLetterChBox().isSelected();
	}
	public boolean isShowCursorInParaEnabled(){
		return pnl.getShowCursorInPara().isSelected();
	}
	public Font getFont(){
		Font sf=(Font) pnl.getFontBox().getSelectedItem();
		Font f=new Font(sf.getName(),pnl.getBoldBtn().isSelected()==true?Font.BOLD:Font.PLAIN,(Integer)pnl.getFontSizeBox().getSelectedItem());
		return f;
	}
	public Clip getMusic(){
		if(clip==null){
			DMusic d=(DMusic) pnl.getMusicBox().getItemAt(pnl.getMusicBox().getSelectedIndex());
			clip= DMusic.openMusic(d.getMusicFile());	
		}
		return clip;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case OptionsPanel.HOME_BTN:
			home.frm.showHomePanel();
			Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					
						saveSettings();
					
				}
			});
			t.start();
			break;
		case OptionsPanel.ADD_BTN:
			addMusic();
			break;
		case OptionsPanel.REMOVE_BTN:
			removeMusic();
			break;
		}
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED){
			clip=null;
			if(isMusicEnabled())home.playMusic();
			DMusic d=pnl.getMusicBox().getItemAt(pnl.getMusicBox().getSelectedIndex());
			if(d.toString().equals("Default Music.wav"))pnl.setRemoveBtnEnabled(false);
			else pnl.setRemoveBtnEnabled(true);
		}
		else {
			home.pauseMusic();
		}
	}
}
