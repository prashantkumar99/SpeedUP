
import javax.swing.SwingUtilities;
import speedup.Home;
import dialog.DDialog;
import frame.SpeedUPFrame;

class SpeedUPLauncher {
	public static void main(String args[]){
		final SpeedUPFrame frame=new SpeedUPFrame();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					DDialog d=new DDialog(frame);
					d.showErrorDialog(frame, "Could not able to launch SpeedUP.","SpeedUP - Error");
				}
				
			}
		});
		new Home(frame);
	}
}
