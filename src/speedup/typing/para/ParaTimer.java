package speedup.typing.para;

public class ParaTimer implements Runnable{
	ParaTyping pt;
	int min;
	Thread t;
	public ParaTimer(ParaTyping pt){
		this.pt=pt;
		min=0;
		t=new Thread(this);
	}
	void startTimer(){
		t=new Thread(this);
		min=0;
		t.start();
	}
	void pauseTimer(){
		t.suspend();
		
	}
	void resumeTimer(){
		t.resume();
	}

	@Override
	public void run() {
		for(;;){
			for(int i=1;i<=60;i++){
				pt.updateTimeElapsed(min, i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			min++;
		}
	}

}
