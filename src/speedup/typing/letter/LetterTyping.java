package speedup.typing.letter;

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
import speedup.Home;
import speedup.timer.PerMin;
import speedup.timer.TimeLeft;
import speedup.typing.Typing;

public class LetterTyping implements ActionListener,KeyListener,WindowFocusListener,Typing{
	Home home;
	final LetterTypingPanel pnl;
	Letters letter;
	TimeLeft timeLeft;
	PerMin perMin;
	public LetterTyping(Home home,LetterTypingPanel pnll) {
		this.home=home;
		this.pnl=pnll;
		
		pnl.setActionLis(this);
		pnl.getTextPane().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				if(ke.getKeyCode()==KeyEvent.VK_CONTROL){
					if(pnl.getStartBtn().getText().equals("Start"))startTyping();
					else pauseTyping();
				}
				
			}
		});
		pnl.getTextPane().setEditable(false);
		
		
		home.frm.addWindowFocusListener(this);
		
		letter=new Letters(); 
		
		timeLeft=new TimeLeft(pnl.getTimeLeftBar(),this);
		
		perMin=new PerMin(this);
	}
	public void setUsername(String user){
		pnl.getUsernameLabel().setText(user);
	}
	public void setNewFont(Font f){
		pnl.getTextPane().setFont(f);
		pnl.getLetterLabel().setFont(f);
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
		pnl.getLetterLabel().setText(letter.getNextLetter());
		timeLeft.startTime(pnl.getMinTimeBox().getItemAt(pnl.getMinTimeBox().getSelectedIndex()));
		if(home.options.isPlayLetterSoundEnabled())home.sounds.playLetter(pnl.getLetterLabel().getText().toLowerCase());
	}
	void startTyping(){
		letter.removeAll();
		boolean anyselected=false;
		if(pnl.getSmallLetterChBox().isSelected()){
			letter.addSmallLetter();
			anyselected=true;
		}
		if(pnl.getCapitalLetterChBox().isSelected()){
			letter.addCapitalLetter();
			anyselected=true;
		}
		if(pnl.getNumberChBox().isSelected()){
			letter.addNumber();
			anyselected=true;
		}
		if(pnl.getSymbolChBox().isSelected()){
			letter.addSymbol();
			anyselected=true;
		}
		if(anyselected){
			pnl.getTextPane().setEditable(true);
			pnl.getTextPane().requestFocus();
			pnl.setTypingMode(true);
			pnl.getStartBtn().setText("Pause");
			perMin.startPerMin();
			pnl.getTextPane().addKeyListener(this);
			next();
		}
		else home.dialog.showErrorDialog(home.frm, "Any kind of letter is not selected.", null);
	}
	void pauseTyping(){
		pnl.setTypingMode(false);
		pnl.getTextPane().setEditable(false);
		timeLeft.stopTime();
		pnl.getTextPane().removeKeyListener(this);
		pnl.getStartBtn().setText(LetterTypingPanel.START_BTN);
		perMin.stopPerMIn();
		pnl.getLetterLabel().setText("Letter");
	}
	void reset(){
		if(home.dialog.showQuestionDialog(home.frm, "Are you sure to restore all data.", null)==DDialog.APPROVE_BTN){
			loadDefaultSetting();
			timeLeft.stopTime();
			pnl.getTimeLeftBar().setValue(0);
			pnl.getTextPane().setText(null);
			pnl.getLetterLabel().setText("Letter");
		}
	}
	public void saveSettings(){
		String str="No Of Typed="+pnl.getNoOfTypedLabel().getText()+"\r\n"
				+ "Correct="+pnl.getCorrectValueLabel().getText()+"\r\n"
				+ "Per Min="+pnl.getPerMinLabel().getText()+"\r\n"
				+ "Wrong="+pnl.getWrongValueLabel().getText()+"\r\n"
				+ "Marks="+pnl.getMarksBar().getValue()+"\r\n"
				+ "Min Time="+pnl.getMinTimeBox().getSelectedIndex()+"\r\n"
				+ "Small Letter="+pnl.getSmallLetterChBox().isSelected()+"\r\n"
				+ "Capital Letter="+pnl.getCapitalLetterChBox().isSelected()+"\r\n"
				+ "Number="+pnl.getNumberChBox().isSelected()+"\r\n"
				+ "Symbol="+pnl.getSymbolChBox().isSelected();
		File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\LetterTyping");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try(FileWriter fw=new FileWriter(file)){
			fw.write(str);
		} catch (IOException e) {
			home.dialog.showErrorDialog(home.frm, "Could not save Letter Typing data.", null);
			e.printStackTrace();
		}
	}
	public void loadSettings(){
		File file=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\LetterTyping");
		List<String> list=null;
		try {
			list = Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
		} catch (IOException e1) {
			loadDefaultSetting();
			home.dialog.showErrorDialog(home.frm, "Could not load Letter Typing data.", null);
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
				if(value.startsWith("Small Letter"))pnl.getSmallLetterChBox().setSelected(Boolean.valueOf(value.substring(13)));
				if(value.startsWith("Capital Letter"))pnl.getCapitalLetterChBox().setSelected(Boolean.valueOf(value.substring(15)));
				if(value.startsWith("Number"))pnl.getNumberChBox().setSelected(Boolean.valueOf(value.substring(7)));
				if(value.startsWith("Symbol"))pnl.getSymbolChBox().setSelected(Boolean.valueOf(value.substring(7)));
			}
		}catch(Exception e){
			loadDefaultSetting();
			
		}
	}
	public void saveDefaultSetting(){
		loadDefaultSetting();
		saveSettings();
	}
	public void loadDefaultSetting() {
		pnl.getNoOfTypedLabel().setText("0");
		pnl.getCorrectValueLabel().setText("0");
		pnl.getWrongValueLabel().setText("0");
		pnl.getPerMinLabel().setText("0");
		pnl.getMarksBar().setValue(0);
		pnl.getSmallLetterChBox().setSelected(true);
		pnl.getCapitalLetterChBox().setSelected(true);
		pnl.getNumberChBox().setSelected(false);
		pnl.getSymbolChBox().setSelected(false);
		pnl.getMinTimeBox().setSelectedIndex(5);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch(cmnd){
		case LetterTypingPanel.START_BTN:
			startTyping();
			break;
		case LetterTypingPanel.PAUSE_BTN:
			pauseTyping();
			break;
		case LetterTypingPanel.RESET_BTN:
			reset();
			break;
		case LetterTypingPanel.HOME_BTN:
			home.frm.showHomePanel();
			pauseTyping();
			pnl.getTextPane().setText(null);
			pnl.getTimeLeftBar().setValue(0);
			saveSettings();
			break;
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
	}
	@Override
	public void keyReleased(KeyEvent ke) {
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		if(!(ke.getKeyCode()==KeyEvent.CHAR_UNDEFINED)){
			String str=String.valueOf(ke.getKeyChar());
			updateNoOfTyped();
			timeLeft.stopTime();
			perMin.updateNoOfLetter();
			if(pnl.getLetterLabel().getText().equals(str)){
				if(home.options.isKeyStrokeSoundEnabled())home.sounds.playTypedLetter();
				Home.setLetterColor(pnl.getTextPane(), Color.BLACK);
				updateCorrect();
			}
			else{
				if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
				if(home.options.isShowWrongLetterEnabled())Home.setLetterColor(pnl.getTextPane(), Color.RED);
				updateWrong();
			}
			updateMarks();
			next();
		}
	}
	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowLostFocus(WindowEvent arg0) {
		pauseTyping();
	}
	@Override
	public void updateLetterPerMin(int noOfLetter) {
		
		pnl.getPerMinLabel().setText(String.valueOf(noOfLetter));
	}
	@Override
	public void updateWordPerMin(int noOfWord) {
		
	}
}
