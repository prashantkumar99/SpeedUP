package speedup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import dialog.DDialog;
import panel.HomePanel;
import speedup.typing.LearnTyping;
import speedup.typing.letter.LetterTyping;
import speedup.typing.para.ParaTyping;
import speedup.typing.word.WordTyping;
import frame.SpeedUPFrame;


public class Home implements ActionListener,ItemListener{
	
	public static final int USER_LOADED=0;
	public static final int USER_UNLOADED=1;
	
	public int userStatus=USER_UNLOADED;
	
	public SpeedUPFrame frm;
	HomePanel homePnl;
	public DDialog dialog;
	
	public Sounds sounds;
	
	public Options options;
	public LearnTyping learnTyping;
	LetterTyping letterTyping;
	WordTyping wordTyping;
	ParaTyping paraTyping;
	public Home(SpeedUPFrame frm) {
		this.frm=frm;
		
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
			exit();	
			}
		});
		dialog=new DDialog(frm);
		options=new Options(this,frm.getOptionsPanel());
		homePnl=frm.getHomePanel();
		homePnl.setUserSelected(false);
		homePnl.setActionLis(this);
		
		
		loadUserList();
		homePnl.getUsernameBox().setSelectedIndex(-1);
		homePnl.getUsernameBox().addItemListener(this);

		
		new AboutUs(frm, frm.getAboutUsPanel());
		learnTyping=new LearnTyping(this, frm.getLearnTypingPanel());
		
		letterTyping=new LetterTyping(this, frm.getLetterTypingPanel());
		
		wordTyping=new WordTyping(this, frm.getWordTypingPanel());
		
		paraTyping=new ParaTyping(this, frm.getParaTypingPanel());
		
		sounds=new Sounds();
		
		
	}
	void removeUser(){
		if(dialog.showQuestionDialog(frm, "Are you sure to remove user \""+homePnl.getUsernameBox().getItemAt(homePnl.getUsernameBox().getSelectedIndex())+"\"","Remove User")==DDialog.APPROVE_BTN){
			File f=new File(getUserFolder(),"user\\"+homePnl.getUsernameBox().getSelectedItem().toString());
			File file[]=f.listFiles();
			for(File value:file)value.delete();
			f.delete();
			homePnl.getUsernameBox().removeItemAt(homePnl.getUsernameBox().getSelectedIndex());
			homePnl.getUsernameBox().setSelectedIndex(-1);
			unloadUser();
		}
	}
	void loadUser(){
		File f2=new File(getUserFolder(),"user");
		if(!f2.exists())f2.mkdir();
		final String name= homePnl.getUsernameBox().getSelectedItem().toString().trim();
		if(name.isEmpty())dialog.showErrorDialog(frm, "Username is invalid. Name must contain atleast one letter.", "Error");
		else {
			homePnl.getRemoveUserBtn().setEnabled(true);
			File f=new File(getUserFolder(),"user\\"+name);
			boolean exist=false;
			for(int i=0;i<homePnl.getUsernameBox().getItemCount();i++){
				if(homePnl.getUsernameBox().getItemAt(i).equals(name)){
					exist=true;
					break;
				}
			}
			if(exist){
				if(f.exists()){
					Thread t=new Thread(new Runnable() {
						
						@Override
						public void run() {
							homePnl.getLoadingLabel().setVisible(true);
							try {
								loadAllSettings();
								homePnl.getLoadingLabel().setVisible(false);
							} catch (Exception e) {
								homePnl.getLoadingLabel().setVisible(false);
								e.printStackTrace();
							}
							options.setUsername(name);
							letterTyping.setUsername(name);
							
							learnTyping.setUsername(name);
							wordTyping.setUsername(name);
							paraTyping.setUsername(name);
							
							homePnl.setUserSelected(true);
							userStatus=USER_LOADED;
						}
					});
					t.start();
				}
				else {
					f.mkdir();
					saveAllDefaultSettings();
					homePnl.getUsernameBox().insertItemAt(name, 0);
					dialog.showInfoDialog(frm, "User data not found. Default is loaded.", null);
					options.setUsername(name);
					letterTyping.setUsername(name);
					
					learnTyping.setUsername(name);
					wordTyping.setUsername(name);
					paraTyping.setUsername(name);
					
					homePnl.setUserSelected(true);
					userStatus=USER_LOADED;
				}
				
			}
			else {
				f.mkdir();
				saveAllDefaultSettings();
				homePnl.getUsernameBox().insertItemAt(name, 0);
				options.setUsername(name);
				letterTyping.setUsername(name);
				
				learnTyping.setUsername(name);
				wordTyping.setUsername(name);
				paraTyping.setUsername(name);
				
				homePnl.setUserSelected(true);
				userStatus=USER_LOADED;
			}
		}
	}
	public String getUser(){
		return homePnl.getUsernameBox().getSelectedItem().toString();
	}
	public void loadAllSettings() {
				options.loadSettings();
				letterTyping.loadSettings();
				learnTyping.loadSettings();
				wordTyping.loadSettings();
				paraTyping.loadSettings();

	}
	public void loadAllDefaultSettings(){
		options.loadDefaultSettings();
		letterTyping.loadDefaultSetting();
		learnTyping.loadDefaultSetting();
		wordTyping.loadDefaultSetting();
		paraTyping.loadDefaultSetting();
	}
	public void saveAllDefaultSettings() {
		options.saveDefaultSettings();
		letterTyping.saveDefaultSetting();
		learnTyping.saveDefaultSetting();
		wordTyping.saveDefaultSetting();
		paraTyping.saveDefaultSetting();
	}
	public void saveAllSettings() {
		options.saveSettings();
		letterTyping.saveSettings();
		learnTyping.saveSettings();
		wordTyping.saveSettings();
		paraTyping.saveSettings();
	}
	public void playMusic(){
		options.getMusic().start();
	}
	public void pauseMusic(){
		options.getMusic().stop();
	}
	public void stopMusic(){
		pauseMusic();
		options.getMusic().setFramePosition(0);
	}
	void unloadUser(){
		userStatus=USER_UNLOADED;
		homePnl.setUserSelected(false);
		loadAllDefaultSettings();
		stopMusic();
	}
	public int getUserStatus(){
		return userStatus;
	}
	void loadUserList(){
		File file=new File(getUserFolder(),"userlist");
		File file2=new File(getUserFolder(),"userlistCreated");
		if(file2.exists()){
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file))){
				homePnl.getUsernameBox().setModel((ComboBoxModel<String>)ois.readObject());
				
			} catch (Exception e) {
				dialog.showErrorDialog(frm, "Could not able to load user list.",null);
				file.delete();
				e.printStackTrace();
			} 
		}
	}
	void saveUserList(){
		File file2=new File(getUserFolder(),"userlistCreated");
		try {
			file2.createNewFile();
		} catch (IOException e1) {
			
		}
		File file=new File(getUserFolder(),"userlist");
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(homePnl.getUsernameBox().getModel());
		} catch (IOException e) {
			dialog.showErrorDialog(frm, "Could not able to save user list.",null);
		}
	}
	public static void setLetterColor(JTextPane te,Color color){
		SimpleAttributeSet a=new SimpleAttributeSet();
		StyleConstants.setForeground(a, color);
		te.setCharacterAttributes(a,false);
	}
	void exit(){
		frm.setVisible(false);
		stopMusic();
				if(getUserStatus()==USER_LOADED){
					saveAllSettings();
				}
				
				saveUserList();
	System.exit(0);
	}
	
	public static File getUserFolder(){
		File f=new File(System.getProperty("user.home")+"\\SpeedUP");
		if(!f.exists())f.mkdir();
		return f;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case HomePanel.LEARN_TYPING_BTN:
			learnTyping.setNewFont(options.getFont());
			frm.showLearnTypingPanel();
			break;
		case HomePanel.LETTER_TYPING_BTN:
			letterTyping.setNewFont(options.getFont());
			frm.showLetterTypingPanel();
			break;
		case HomePanel.WORD_TYPING_BTN:
			wordTyping.setNewFont(options.getFont());
			frm.showWordTypingPanel();
			break;
		case HomePanel.PARA_TYPING_BTN:
			paraTyping.setNewFont(options.getFont());
			paraTyping.setCaretVisible(options.isShowCursorInParaEnabled());
			frm.showParaTypingPanel();
			break;
		case HomePanel.ABOUT_US_BTN:
			frm.showAboutUsPanel();
			break;
		case HomePanel.OPTIONS_BTN:
			frm.showOptionsPanel();
			break;
		case HomePanel.REMOVE_BTN:
			removeUser();
			break;
		case HomePanel.EXIT_BTN:
			exit();
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED){
			loadUser();
		}
		else unloadUser();
	}

}
