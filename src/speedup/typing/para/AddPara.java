package speedup.typing.para;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import speedup.Home;
import dialog.AddParaDialog;
import dialog.DDialog;

public class AddPara implements ActionListener{
	Home home;
	AddParaDialog dialog;
	ParaTyping pt;
	JFileChooser textFile;
	DDialog dilog;
	AddPara(Home home,ParaTyping pt){
		this.home=home;
		this.pt=pt;
		
		dialog=new AddParaDialog();
		dialog.setActionLis(this);
		
		textFile=new JFileChooser();
		textFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		textFile.setFileFilter(new FileNameExtensionFilter("Text File (.txt)", "txt"));
		textFile.setMultiSelectionEnabled(false);
		textFile.setDialogTitle("Choose Paragraph");
		
		dilog=new DDialog(pt.home.frm);
	}
	void chooseTextFile(){
		if(textFile.showDialog(home.frm, "Select")==JFileChooser.APPROVE_OPTION){
			File f=textFile.getSelectedFile();
			if(f.getName().endsWith(".txt")){
				String text=null;
				try(FileReader fr=new FileReader(f)){
					String str="";
					int ch=0;
					 while (ch!=-1){
						ch=fr.read();
						if(ch != -1)str=str+String.valueOf((char)ch);
					}
					text=str;
				}catch (IOException e) {
					text="Paragraph is Missing.";
				}
				dialog.getTextPane().setText(text);
			}
			else dilog.showErrorDialog(pt.home.frm, "This file could not be used as paragraph.", null);
		}
		
	}
	void addPara(){
		if(dialog.getTextPane().getText().length()>0){
			pt.pnl.getParaNo().addItem(new Para(dialog.getTextPane().getText(),(pt.pnl.getParaNo().getItemCount()+1)));
			pt.pnl.getParaNo().setSelectedIndex((pt.pnl.getParaNo().getItemCount()-1));
			ParaDetailTable model=(ParaDetailTable) pt.pnl.getCustomParaTable().getModel();
			String table[]=new String[8];
			for(int j=0;j<8;j++){
				if(j==0){
					table[j]=""+(pt.pnl.getParaNo().getItemCount());
				}
				else table[j]="0";
			}
			model.addRow(table);
			dialog.setVisible(false);
		}
		else dilog.showErrorDialog(pt.home.frm, "Paragraph is not valid.", null);
	}
	void showAddParaDialog(){
		dialog.setLocationRelativeTo(home.frm);
		dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()){
		case AddParaDialog.OK_BTN:
			addPara();
			break;
		case AddParaDialog.CANCEL_BTN:
			dialog.getTextPane().setText(null);
			dialog.setVisible(false);
			break;
		case AddParaDialog.CHOOSE_TEXT_FILE_BTN:
			chooseTextFile();
			break;
		}
		
	}
	
}
