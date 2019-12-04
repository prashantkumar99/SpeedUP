package speedup.typing.para;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Para implements Serializable{
	String paraText=null;
	int paraNo;
 	public Para(String paraText,int paraNo){
		this.paraText=paraText;
		this.paraNo=paraNo;
	}
	public Para(File paraFile,int paraNo){
		this.paraNo=paraNo;
		try(FileReader fr=new FileReader(paraFile)){
			String str="";
			int ch=0;
			 while (ch!=-1){
				ch=fr.read();
				if(ch != -1)str=str+String.valueOf((char)ch);
			}
			paraText=str;
		}catch (IOException e) {
			paraText="Paragraph is Missing.";
		}
	}
	String getParaText(){
		return paraText;
	}
	@Override
	public String toString() {
		return String.valueOf(paraNo);
	}
}
