package custom;

import javax.swing.text.DefaultCaret;

public class AlwaysActiveCaret extends DefaultCaret {
	boolean active=true;
	
	public AlwaysActiveCaret(boolean active){
		this.active=active;
	}
	@Override
	public boolean isVisible() {
	return active;
	}

	
}
