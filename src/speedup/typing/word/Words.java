package speedup.typing.word;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

import dialog.DDialog;

class Words {
	Vector<String> wordList;
	DDialog dialog;
	Random r;
	public Words() {
		wordList=new Vector<>();
		dialog = new DDialog((JFrame)null);
		r=new Random();
		File file=new File("data\\word list.txt");
		
		try {
			List<String> list=Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
			for(String value:list){
				wordList.add(value);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			wordList.add(new String("The"));
			wordList.add(new String("Quick"));
			wordList.add(new String("Brown"));
			wordList.add(new String("Fox"));
			wordList.add(new String("Jumps"));
			wordList.add(new String("Over"));
			wordList.add(new String("Lazy"));
			wordList.add(new String("Dog"));
			
		}
	}
	String nextWord(){
		return wordList.elementAt(r.nextInt(wordList.size()));
	}
}
