package speedup.typing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import panel.LearnTypingPanel;
import panel.LessonPanel;
import speedup.Home;


public class LearnTyping implements ActionListener,DocumentListener,KeyListener{
	Home home;
	LearnTypingPanel pnl;
	
	public LearnTyping(Home home,LearnTypingPanel pnl) {
		this.home=home;
		this.pnl=pnl;
		
		pnl.setActionLis(this);
		pnl.setDocumentLis(this,this);
	}
	public void setUsername(String user){
		pnl.getUsername().setText(user);
	}
	private void writeSettingFile(String lessonNo){
		String str="Current="+lessonNo;
		File f=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\LearnTyping");
		try {
			f.createNewFile();
		} catch (IOException e) {
			
		}
		try(FileWriter fw=new FileWriter(f)){
			fw.write(lessonNo);
		} catch (IOException e) {
			home.dialog.showErrorDialog(home.frm, "Could not save Learn Typing data.", null);
			e.printStackTrace();
		}
	}
	public void saveSettings(){
		writeSettingFile(String.valueOf(pnl.getLessonTabbedPane().getSelectedIndex()));
	}
	public void loadSettings(){
		File f=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\LearnTyping");
		
			List<String> list=null;
			try {
				list = Files.readAllLines(f.toPath(),StandardCharsets.US_ASCII);
			} catch (IOException e1) {
				loadDefaultSetting();
				e1.printStackTrace();
			}
			for(String str:list){
				try {
					if(str.startsWith("Current"))pnl.getLessonTabbedPane().setSelectedIndex(Integer.valueOf(str.substring(8)));
					pnl.getTextPane().getDocument().addDocumentListener(this);
				} catch (NumberFormatException e) {
					pnl.getLessonTabbedPane().setSelectedIndex(0);
				}
			}
		
	}
	public void saveDefaultSetting(){
		loadDefaultSetting();
		saveSettings();
	}
	public void setNewFont(Font f){
		pnl.setNewFont(f);
	}
	public void loadDefaultSetting(){
		pnl.getLessonTabbedPane().setSelectedIndex(0);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case LearnTypingPanel.HOME_BTN:
			home.frm.showHomePanel();
			saveSettings();
			break;
		}
		
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		
	}
	@Override
	public void insertUpdate(DocumentEvent de) {
		String str=pnl.getParaPane().getText();
		if(str.equals(pnl.getTextPane().getText())){
			try {
				if(pnl.getLessonTabbedPane().getSelectedIndex()<15)pnl.getLessonTabbedPane().setSelectedIndex(pnl.getLessonTabbedPane().getSelectedIndex()+1);
				pnl.getLastLessonPnl().getLessonTextPane().setText("All Keys\r\n\r\nThe Quick Brown Fox Jumps over the lazy dog.\r\n\r\nPlace your fingers on Home keys and begin the exercise. \r\n\r\nAfter then go to home and try Letter Typing.");
			} catch (Exception e) {
				pnl.getLastLessonPnl().getLessonTextPane().setText("CONGRATULATIONS !\n\n\n\n\nNow you can practise other type Typing Lessons.");
			}
		}
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		try {
			if(!(ke.getKeyCode()==KeyEvent.CHAR_UNDEFINED)){
				String str=String.valueOf(ke.getKeyChar());
				int position=pnl.getTextPane().getCaretPosition();
				if(str.equals(String.valueOf(pnl.getParaPane().getText().charAt(position)))){
					if(home.options.isKeyStrokeSoundEnabled())home.sounds.playTypedLetter();
					Home.setLetterColor(pnl.getTextPane(),Color.BLACK);
				}
				else {
					if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
					if(home.options.isShowWrongLetterEnabled())Home.setLetterColor(pnl.getTextPane(),Color.RED);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
			if(home.options.isShowWrongLetterEnabled())Home.setLetterColor(pnl.getTextPane(),Color.RED);
		}
	}
}
