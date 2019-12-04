package speedup.typing;

import java.awt.Font;

public interface Typing {
	void updateLetterPerMin(int noOfLetter);
	void updateWordPerMin(int noOfWord);
	void loadSettings();
	void saveSettings();
	void loadDefaultSetting();
	void saveDefaultSetting();
	void setNewFont(Font f);
	void setUsername(String name);
	public static final int WORDS_PER_MIN=0;
	public static final int LETTERS_PER_MIN=1;
	void next();
}
