package custom;

import java.awt.Font;

public class DFont extends Font{
			Font f;
			public DFont(Font f) {
				super(f);
				this.f=f;
			}
			public String toString(){
				return f.getName();
			}
			
		}