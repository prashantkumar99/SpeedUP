package speedup.typing.para;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


import javax.swing.table.DefaultTableModel;

import dialog.DDialog;
import panel.ParaTypingPanel;
import speedup.Home;
import speedup.timer.PerMin;
import speedup.typing.Typing;

public class ParaTyping implements Typing,ActionListener,ItemListener,KeyListener,CaretListener,WindowFocusListener{
	Home home;
	ParaTypingPanel pnl;
	PerMin perMin;
	ParaTimer paraTimer;
	AddPara addPara;
	public ParaTyping(Home home,ParaTypingPanel pnll) {
		this.home=home;
		this.pnl=pnll;
		
		pnl.setActionLis(this);
		pnl.getTextPane().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				if(ke.getKeyCode()==KeyEvent.VK_CONTROL){
					if(pnl.getStartBtn().getText().equals("Start"))start();
					else pause();
				}
				
			}
		});
		pnl.getParaNo().setSelectedIndex(-1);
		pnl.getParaNo().addItemListener(this);
		pnl.getTextPane().addCaretListener(this);
		home.frm.addWindowFocusListener(this);
		
		perMin=new PerMin(this);
		paraTimer=new ParaTimer(this);
		addPara=new AddPara(home,this);
		
		loadDefaultSetting();
	}
	public void setCaretVisible(boolean visible){
		pnl.setCaretVisible(visible);
	}
	@Override
	public void updateLetterPerMin(int noOfLetter) {
		pnl.getLetterPerMin().setText(String.valueOf(noOfLetter));
	}
	@Override
	public void updateWordPerMin(int noOfWord) {
		pnl.getWordPerMin().setText(String.valueOf(noOfWord));
	}

	@Override
	public void loadSettings() {
		boolean showError=false;
		File f=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\WordParaTable");
		File f2=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\NumberParaTable");
		File f3=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\CustomParaTable");
		File f4=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\ParaTyping");
		File f5=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\CustomModel");
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f))){
			pnl.getWordParaTable().setModel((ParaDetailTable)ois.readObject());
		} catch (Exception e) {
			showError=true;
		}
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f2))){
			pnl.getNumberParaTable().setModel((ParaDetailTable)ois.readObject());
		} catch (Exception e) {
			showError=true;
		}
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f3))){
			pnl.getCustomParaTable().setModel((ParaDetailTable)ois.readObject());
		} catch (Exception e) {
			showError=true;
		}
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f5))){
			pnl.setCustomNoModel((DefaultComboBoxModel<Para>) ois.readObject());
		} catch (Exception e) {
			showError=true;
		}
		try {
			List<String> list=Files.readAllLines(f4.toPath(), StandardCharsets.US_ASCII);
			for(String val:list){
				try{
					if(val.startsWith("Para Type="))pnl.getParaType().setSelectedIndex(Integer.valueOf(val.substring(10)));
					if(val.startsWith("Para No=")){
						pnl.getParaNo().setSelectedIndex(Integer.valueOf(val.substring(8)));
						if(Integer.valueOf(val.substring(8))==-1)pnl.getStartBtn().setEnabled(false);
					}
				}catch(NumberFormatException npe){
					loadDefaultSetting();
				}
			}
		} catch (IOException e) {
			showError=true;
		}
		
		if(showError)home.dialog.showErrorDialog(home.frm, "Could not load Paragraph Typing data.", null);
	}
	@Override
	public void saveSettings() {
		boolean showError=false;
		File f=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\WordParaTable");
		File f2=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\NumberParaTable");
		File f3=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\CustomParaTable");
		File f4=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\ParaTyping");
		File f5=new File(Home.getUserFolder(),"user\\"+home.getUser()+"\\CustomModel");
		try {
			f.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			f4.createNewFile();
			f5.createNewFile();
		} catch (IOException e1) {
		}
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f))){
			oos.writeObject((ParaDetailTable)pnl.getWordParaTable().getModel());
		} catch (IOException e) {
			showError=true;
		}
		
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f2))){
			oos.writeObject((ParaDetailTable)pnl.getNumberParaTable().getModel());
		} catch (IOException e) {
			showError=true;
		}
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f3))){
			oos.writeObject((ParaDetailTable)pnl.getCustomParaTable().getModel());
		} catch (IOException e) {
			showError=true;
		}
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f5))){
			oos.writeObject(pnl.getCustomNoModel());
		} catch (IOException e) {
			showError=true;
			e.printStackTrace();
		}
		String str="Para Type="+pnl.getParaType().getSelectedIndex()+"\r\n"
				+ "Para No="+pnl.getParaNo().getSelectedIndex();
		try(FileWriter fw=new FileWriter(f4)){
			fw.write(str);
		} catch (IOException e) {
			showError=true;
		}
		if(showError)home.dialog.showErrorDialog(home.frm, "Could not save Paragraph Typing data.", null);
	}
	@Override
	public void loadDefaultSetting() {
		pnl.getParaPane().setText(null);
		pnl.getParaNo().setSelectedIndex(-1);
		pnl.getParaType().setSelectedIndex(1);
		pnl.getParaType().setSelectedIndex(-1);
		pnl.getLetterPerMin().setText("0");
		pnl.getWordPerMin().setText("0");
		pnl.getTimeElapsed().setText("0");
		pnl.getMarks().setValue(0);
		pnl.getCorrectLetter().setText("0");
		pnl.getWrongLetter().setText("0");
		pnl.getNoOfLetter().setText("0");
		pnl.getWordParaTable().setModel(new ParaDetailTable(100,pnl.getWordParaTable()));
		pnl.getNumberParaTable().setModel(new ParaDetailTable(20,pnl.getNumberParaTable()));
		pnl.getCustomParaTable().setModel(new ParaDetailTable(0, pnl.getCustomParaTable()));
		DefaultComboBoxModel<Para> model=new DefaultComboBoxModel<>();
		pnl.setCustomNoModel(model);
	}
	@Override
	public void saveDefaultSetting() {
		loadDefaultSetting();
		saveSettings();
	}
	@Override
	public void setNewFont(Font f) {
		pnl.getParaPane().setFont(f);
		pnl.getTextPane().setFont(f);
	}
	@Override
	public void setUsername(String name) {
		pnl.getUsername().setText(name);
	}
	void updateNoOfLetter(){
		long i=Long.valueOf(pnl.getNoOfLetter().getText())+1;
		pnl.getNoOfLetter().setText(""+i);
	}
	void updateCorrectLetter(){
		long i=Long.valueOf(pnl.getCorrectLetter().getText())+1;
		pnl.getCorrectLetter().setText(""+i);
	}
	void updateWrongLetter(){
		long i=Long.valueOf(pnl.getWrongLetter().getText())+1;
		pnl.getWrongLetter().setText(""+i);
	}
	void updateMarks(){
		long no=Long.valueOf(pnl.getNoOfLetter().getText())+1;
		long co=Long.valueOf(pnl.getCorrectLetter().getText())+1;
		int i=(int)((co*100)/no);
		pnl.getMarks().setValue(i);
	}
	void updateTimeElapsed(int min,int sec){
		pnl.getTimeElapsed().setText(min+" : "+sec);
	}
	void updateTable(int no){
		JTable model=null;
		if(pnl.getParaType().getSelectedItem().toString().equals("Word"))model=pnl.getWordParaTable();
		if(pnl.getParaType().getSelectedItem().toString().equals("Custom"))model=pnl.getCustomParaTable();
		if(pnl.getParaType().getSelectedItem().toString().equals("Number"))model=pnl.getNumberParaTable();
		model.getModel().setValueAt(pnl.getLetterPerMin().getText(),(no-1),1);
		model.getModel().setValueAt(pnl.getWordPerMin().getText(),(no-1),2);
		model.getModel().setValueAt(pnl.getNoOfLetter().getText(),(no-1),3);
		model.getModel().setValueAt(pnl.getCorrectLetter().getText(),(no-1),4);
		model.getModel().setValueAt(pnl.getWrongLetter().getText(),(no-1),5);
		model.getModel().setValueAt(pnl.getTimeElapsed().getText(),(no-1),6);
		model.getModel().setValueAt(String.valueOf(pnl.getMarks().getValue()),(no-1),7);
	}
	@Override
	public void next() {
		int se=pnl.getParaNo().getSelectedIndex()+1;
		if(se==pnl.getParaNo().getItemCount()){
			home.dialog.showInfoDialog(home.frm, pnl.getParaType().getSelectedItem().toString()+" para typing lessons are over.", null);
			updateTable(se);
			pnl.getParaNo().setSelectedIndex(-1);
			pause();
		}
		else {
			updateTable(se);
			pnl.getParaNo().setSelectedIndex(se);
			start();
		}
		
	}
	private void start() {
		pnl.getStartBtn().setText("Pause");
		if(pnl.getTextPane().getText().length()>0)paraTimer.resumeTimer();
		else paraTimer.startTimer();
		pnl.getTextPane().setEditable(true);
		pnl.getTextPane().requestFocus();
		perMin.startPerMin();
		pnl.getTextPane().addKeyListener(this);
		pnl.getParaPane().setCaretPosition(pnl.getTextPane().getCaretPosition());
	}
	private void reset() {
		pause();
		if(home.dialog.showQuestionDialog(home.frm, "Are you sure to restore data.","Reset")==DDialog.APPROVE_BTN){
			loadDefaultSetting();
			pnl.getTextPane().setText(null);
		}
	}
	private void pause() {
		paraTimer.pauseTimer();
		pnl.getStartBtn().setText("Start");
		pnl.getTextPane().setEditable(false);
		pnl.getTextPane().removeKeyListener(this);
		perMin.stopPerMIn();
	}
	void newPara(){
		pnl.getLetterPerMin().setText("0");
		pnl.getWordPerMin().setText("0");
		pnl.getTimeElapsed().setText("0");
		pnl.getMarks().setValue(0);
		pnl.getCorrectLetter().setText("0");
		pnl.getWrongLetter().setText("0");
		pnl.getNoOfLetter().setText("0");
		pnl.getTextPane().setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()){
		case ParaTypingPanel.START_BTN:
			start();
			break;
		case ParaTypingPanel.PAUSE_BTN:
			pause();
			break;
		case ParaTypingPanel.RESET_BTN:
			reset();
			break;
		case ParaTypingPanel.ADD_BTN:
			addPara.showAddParaDialog();
			break;
		case ParaTypingPanel.REMOVE_BTN:
			if(home.dialog.showQuestionDialog(home.frm, "Are you sure to remove paragraph \""+pnl.getParaNo().getSelectedItem()+"\".",null)==DDialog.APPROVE_BTN){
				DefaultTableModel model= (DefaultTableModel) pnl.getCustomParaTable().getModel();
				int se=pnl.getParaNo().getSelectedIndex();
				for(int i=0;i<model.getRowCount();i++){
					if(pnl.getCustomParaTable().getValueAt(i, 0).toString().equals(String.valueOf((se+1)))){
						model.removeRow(i);
					}
				}
				pnl.getParaNo().removeItemAt(pnl.getParaNo().getSelectedIndex());
				pnl.getTextPane().setText(null);
			}
			break;
		case ParaTypingPanel.HOME_BTN:
			pause();
			home.frm.showHomePanel();
			pnl.getTextPane().setText(null);
			saveSettings();
			break;
		}
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(pnl.getParaNo().getSelectedIndex()==-1)pnl.getStartBtn().setEnabled(false);
		else pnl.getStartBtn().setEnabled(true);
		if(ie.getStateChange()==ItemEvent.SELECTED){
			if(pnl.getParaType().getSelectedItem().toString().equals("Word"))pnl.getParaPane().setText(pnl.getParaNo().getItemAt(pnl.getParaNo().getSelectedIndex()).getParaText());
			else pnl.getParaPane().setText(pnl.getParaNo().getItemAt(pnl.getParaNo().getSelectedIndex()).getParaText());
			pnl.getParaPane().setCaretPosition(0);
			pnl.getParaPane().select(5,10);
			pnl.getTextPane().setText(null);
			newPara();
			pause();
		}
		else {
			pnl.getParaPane().setText(null);
			pnl.getTextPane().setText(null);
			pause();
			
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
			String para=pnl.getParaPane().getText();
			updateNoOfLetter();
			if(ke.getKeyChar()==' ')perMin.updateNoOfWord();
			perMin.updateNoOfLetter();
			try {
				int pos=pnl.getTextPane().getCaretPosition();
				if(ke.getKeyChar()=='\n')pos--;
				String fst=String.valueOf(ke.getKeyChar());
				String snd=para.substring(pos, (pos+1));
				if(fst.equals(snd)){
					if(home.options.isKeyStrokeSoundEnabled())home.sounds.playTypedLetter();
					Home.setLetterColor(pnl.getTextPane(),Color.BLACK);
					updateCorrectLetter();
				}
				else {
					if(home.options.isWrongLetterSoundEnabled())home.sounds.playWrongLetter();
					if(home.options.isShowWrongLetterEnabled())Home.setLetterColor(pnl.getTextPane(),Color.RED);
					updateWrongLetter();
				}
				updateMarks();
			} catch (Exception e) {
				
			}
			String par=pnl.getParaPane().getText();
			String text=pnl.getTextPane().getText()+1;
			if(par.length()==text.length()){
				next();
			}
		}
	}
	@Override
	public void caretUpdate(CaretEvent arg0) {
		int pos=pnl.getTextPane().getCaretPosition();
		pnl.getParaPane().setCaretPosition(pos);
	}
	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		
	}
	@Override
	public void windowLostFocus(WindowEvent arg0) {
		pause();
	}

}