package speedup.timer;

import speedup.typing.Typing;

public class PerMin implements Runnable {
	int letter,word;
	Typing ty;
	Thread t;
	public PerMin(Typing ty) {
		letter=0;
		word=0;
		this.ty=ty;
		t=new Thread(this);
	}
	public void updateNoOfLetter(){
		letter++;
	}
	public void updateNoOfWord(){
		word++;
	}
	public void startPerMin(){
		t=new Thread(this);
		t.start();
	}
	public void stopPerMIn(){
		t.stop();
		letter=0;
		word=0;
	}
	@Override
	public void run() {
		for(int t=0;t>-1;t++){
			for(int i=1;i<=60;i++){
				if(i<1){
					ty.updateLetterPerMin(letter);
					ty.updateWordPerMin(word);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
			ty.updateLetterPerMin(letter);
			ty.updateWordPerMin(word);
			letter=0;
			word=0;
		}
	}

}
