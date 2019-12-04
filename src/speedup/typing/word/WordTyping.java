package speedup.typing.word;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import dialog.DDialog;
import panel.LetterTypingPanel;
import panel.WordTypingPanel;
import speedup.Home;
import speedup.timer.PerMin;
import speedup.timer.TimeLeft;
import speedup.typing.Typing;

public class WordTyping implements ActionListener,Typing,KeyListener,WindowFocusListener{
	Home home;
	WordTypingPanel pnl;
	PerMin perMin;
	TimeLeft timeLeft;
	Words words;
	public WordTyping(Home home,WordTypingPanel pnll) {
		this.home=home;
		this.pnl=pnll;
		
		pnl.setActionLis(this);
		pnl.getTextPane().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				if(ke.getKeyCode()==KeyEvent.VK_CONTROL){
					if(pnl.getStartBtn().getText().equals("Start"))startTyping();
					else pause();
				}
				
			}
		});
		pnl.getTextPane().setEditable(false);
		home.frm.addWindowFocusListener(this);
		
		perMin=new PerMin(this);
		timeLeft=new TimeLeft(pnl.getTimeLeftBar(), this);
		
		words=new Words();
	}
	@Override
	public void loadSettings() {
	
		File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\WordTyping");
		List<String> list=null;
		try {
			list = Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
		} catch (IOException e1) {
			loadDefaultSetting();
			home.dialog.showErrorDialog(home.frm, "Could not load Word Typing data.", null);
			e1.printStackTrace();
		}
		try{
			for(String value:list){
				if(value.startsWith("No Of Typed"))pnl.getNoOfTypedLabel().setText(value.substring(12));
				if(value.startsWith("Correct"))pnl.getCorrectValueLabel().setText(value.substring(8));
				if(value.startsWith("Per Min"))pnl.getPerMinLabel().setText(value.substring(8));
				if(value.startsWith("Wrong"))pnl.getWrongValueLabel().setText(value.substring(6));
				if(value.startsWith("Marks"))pnl.getMarksBar().setValue(Integer.valueOf(value.substring(6)));
				if(value.startsWith("Min Time"))pnl.getMinTimeBox().setSelectedIndex(Integer.valueOf(value.substring(9)));
			}
		}catch(Exception e){
			loadDefaultSetting();
		}
	}

	@Override
	public void saveSettings() {
		File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\WordTyping");
		String str="No Of Typed="+pnl.getNoOfTypedLabel().getText()+"\r\n"
				+ "Correct="+pnl.getCorrectValueLabel().getText()+"\r\n"
				+ "Per Min="+pnl.getPerMinLabel().getText()+"\r\n"
				+ "Wrong="+pnl.getWrongValueLabel().getText()+"\r\n"
				+ "Marks="+pnl.getMarksBar().getValue()+"\r\n"
				+ "Min Time="+pnl.getMinTimeBox().getSelectedIndex();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try(FileWriter fw=new FileWriter(file)){
			fw.write(str);
		} catch (IOException e) {
			home.dialog.showErrorDialog(home.frm, "Could not save Word Typing data.", null);
			e.printStackTrace();
		}
	}

	@Override
	public void loadDefaultSetting() {
		pnl.getMarksBar().setValue(0);
		pnl.getNoOfTypedLabel().setText("0");
		pnl.getCorrectValueLabel().setText("0");
		pnl.getWrongValueLabel().setText("0");
		pnl.getPerMinLabel().setText("0");
		pnl.getMinTimeBox().setSelectedIndex(5);
	}

	@Override
	public void saveDefaultSetting() {
		loadDefaultSetting();
		saveSettings();
	}

	@Override
	public void setNewFont(Font f) {
		pnl.getTextPane().setFont(f);
		pnl.getWordLabel().setFont(f);
	}
	@Override
	public void updateLetterPerMin(int noOfLetter) {
		
		
	}

	@Override
	public void updateWordPerMin(int noOfWord) {
		pnl.getPerMinLabel().setText(String.valueOf(noOfWord));
		
	}
	void updateNoOfTyped(){
		long i=Integer.valueOf(pnl.getNoOfTypedLabel().getText())+1;
		pnl.getNoOfTypedLabel().setText(String.valueOf(i));
	}
	void updateCorrect(){
		long i=Integer.valueOf(pnl.getCorrectValueLabel().getText())+1;
		pnl.getCorrectValueLabel().setText(String.valueOf(i));
	}
	void updateWrong(){
		long i=Integer.valueOf(pnl.getWrongValueLabel().getText())+1;
		pnl.getWrongValueLabel().setText(String.valueOf(i));
	}
	void updateMarks(){
		long no=Integer.valueOf(pnl.getNoOfTypedLabel().getText());
		long c=Integer.valueOf(pnl.getCorrectValueLabel().getText());
		int value=(int) ((c*100)/no);
		pnl.getMarksBar().setValue(value);
	}
	public void next(){
		pnl.getTextPane().setText(null);
		pnl.getWordLabel().setText(words.nextWord().toString());
		timeLeft.startTime((pnl.getMinTimeBox().getItemAt(pnl.getMinTimeBox().getSelectedIndex())*60));
	}
	void startTyping(){
		pnl.getTextPane().setEditable(true);
		pnl.setTypingMode(true);
		pnl.getStartBtn().setText("Pause");
		perMin.startPerMin();
		next();
		pnl.getTextPane().addKeyListener(this);
		pnl.getTextPane().requestFocus();
	}
	void pause(){
		pnl.getTextPane().setText(null);
		pnl.setTypingMode(false);
		pnl.getTextPane().setEditable(false);
		timeLeft.stopTime();
		pnl.getStartBtn().setText(LetterTypingPanel.START_BTN);
		perMin.stopPerMIn();
		pnl.getTextPane().removeKeyListener(this);
		pnl.getWordLabel().setText("Word");
	}
	void reset(){
		if(home.dialog.showQuestionDialog(home.frm, "Are you sure to restore all data.", null)==DDialog.APPROVE_BTN){
			loadDefaultSetting();
			timeLeft.stopTime();
			pnl.getTimeLeftBar().setValue(0);
			pnl.getTextPane().setText(null);
			pnl.getWordLabel().setText("Letter");
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case WordTypingPanel.HOME_BTN:
			home.frm.showHomePanel();
			pause();
			saveSettings();
			break;
		case WordTypingPanel.START_BTN:
			startTyping();
			break;
		case WordTypingPanel.PAUSE_BTN:
			pause();
			break;
		case WordTypingPanel.RESET_BTN:
			reset();
			break;
		}
	}
	public void setUsername(String name) {
		pnl.getUsernameLabel().setText(name);
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		if(!(ke.getKeyCode()==KeyEvent.CHAR_UNDEFINED)){
			String word=pnl.getWordLabel().getText(),text=pnl.getTextPane().getText();
			if(text.length()>=word.length()){
				perMin.updateNoOfWord();
				if(word.equals(text)){
					updateCorrect();
				}
				else {
					if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
					updateWrong();
				}
				updateNoOfTyped();
				updateMarks();
				timeLeft.stopTime();
				next();
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		if(!(ke.getKeyCode()==KeyEvent.CHAR_UNDEFINED)){
			String word=pnl.getWordLabel().getText(),text=pnl.getTextPane().getText();
				try {
					String fst=String.valueOf(ke.getKeyChar());
					String snd=String.valueOf(word.charAt(text.length()));
					if(fst.equals(snd)){
						if(home.options.isKeyStrokeSoundEnabled())home.sounds.playTypedLetter();
						Home.setLetterColor(pnl.getTextPane(),Color.BLACK);
					}
					else {
						if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
						if(home.options.isShowWrongLetterEnabled())Home.setLetterColor(pnl.getTextPane(),Color.RED);
					}
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
	}
	@Override
	public void windowGainedFocus(WindowEvent we) {
		
	}
	@Override
	public void windowLostFocus(WindowEvent we) {
		pause();
	}

}
