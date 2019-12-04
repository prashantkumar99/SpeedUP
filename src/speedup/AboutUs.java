package speedup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.AboutUsPanel;
import frame.SpeedUPFrame;

public class AboutUs implements ActionListener {
	SpeedUPFrame frm;
	AboutUsPanel pnl;
	public AboutUs(SpeedUPFrame frm,AboutUsPanel pnl){
		this.frm=frm;
		this.pnl=pnl;
		
		pnl.setActionLis(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmnd=ae.getActionCommand();
		switch (cmnd) {
		case AboutUsPanel.HOME_BTN:
			frm.showHomePanel();
			break;
		}
	}

}
