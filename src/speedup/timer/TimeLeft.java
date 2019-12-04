package speedup.timer;

import javax.swing.JProgressBar;

import speedup.typing.Typing;

public class TimeLeft implements Runnable{
	JProgressBar bar;
	double time=0.0;
	Thread t;
	Typing lt;
	public TimeLeft(JProgressBar bar,Typing lt) {
		this.bar=bar;
		this.lt=lt;
		t=new Thread(this);
	}
	public void startTime(double time){
		this.time=time;
		
		t=new Thread(this);
		t.start();
	}
	public void stopTime(){
		t.stop();
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++){
			bar.setValue(i);
			try {
				Thread.sleep((long) (time*10));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		lt.next();
	}
}
