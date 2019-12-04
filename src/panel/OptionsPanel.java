package panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JSeparator;

import java.awt.Insets;







import custom.DFont;
import custom.DMusic;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class OptionsPanel extends JPanel implements ItemListener,Serializable{
	private JCheckBox chckbxMusic;
	private JCheckBox chckbxKeyStrokeSound;
	private JCheckBox chckbxWrongLetterSound;
	private JComboBox<DFont> fontBox;
	private JLabel username;
	private JButton btnHome;

	
	private JComboBox<DMusic> musicBox;
	private JLabel lblSampleText;
	private JLabel lblFontSize;
	private JComboBox<Integer> fontSizeBox;
	private JToggleButton btnBold;
	
	
	
	public static final String HOME_BTN="Home";
	public static final String ADD_BTN="Add";
	public static final String REMOVE_BTN="Remove";
	
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JCheckBox chckbxShowWrongLetter;
	private JCheckBox chckbxPlayLetterSound;
	private JCheckBox chckbxShowCursorIn;
	
	/**
	 * Create the panel.
	 */
	public OptionsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 84, 151, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setForeground(new Color(255, 99, 71));
		lblOptions.setFont(new Font("Jokerman", Font.BOLD, 20));
		GridBagConstraints gbc_lblOptions = new GridBagConstraints();
		gbc_lblOptions.insets = new Insets(0, 0, 5, 0);
		gbc_lblOptions.gridwidth = 6;
		gbc_lblOptions.gridx = 0;
		gbc_lblOptions.gridy = 0;
		add(lblOptions, gbc_lblOptions);
		
		username = new JLabel("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.anchor = GridBagConstraints.SOUTHEAST;
		gbc_username.gridwidth = 6;
		gbc_username.gridx = 0;
		gbc_username.gridy = 1;
		add(username, gbc_username);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 6;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		
		chckbxMusic = new JCheckBox("Music");
		chckbxMusic.setSelected(true);
		GridBagConstraints gbc_chckbxMusic = new GridBagConstraints();
		gbc_chckbxMusic.anchor = GridBagConstraints.WEST;
		gbc_chckbxMusic.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMusic.gridx = 0;
		gbc_chckbxMusic.gridy = 3;
		add(chckbxMusic, gbc_chckbxMusic);
		
		musicBox = new JComboBox<DMusic>();
		GridBagConstraints gbc_musicBox = new GridBagConstraints();
		gbc_musicBox.gridwidth = 2;
		gbc_musicBox.insets = new Insets(0, 0, 5, 5);
		gbc_musicBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_musicBox.gridx = 1;
		gbc_musicBox.gridy = 3;
		add(musicBox, gbc_musicBox);
		
		btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 3;
		add(btnAdd, gbc_btnAdd);
		
		btnRemove = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 4;
		gbc_btnRemove.gridy = 3;
		add(btnRemove, gbc_btnRemove);
		
		chckbxKeyStrokeSound = new JCheckBox("Key Stroke Sound");
		chckbxKeyStrokeSound.setSelected(true);
		GridBagConstraints gbc_chckbxKeyStrokeSound = new GridBagConstraints();
		gbc_chckbxKeyStrokeSound.anchor = GridBagConstraints.WEST;
		gbc_chckbxKeyStrokeSound.gridwidth = 3;
		gbc_chckbxKeyStrokeSound.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxKeyStrokeSound.gridx = 0;
		gbc_chckbxKeyStrokeSound.gridy = 4;
		add(chckbxKeyStrokeSound, gbc_chckbxKeyStrokeSound);
		
		chckbxWrongLetterSound = new JCheckBox("Wrong Letter Sound");
		chckbxWrongLetterSound.setSelected(true);
		GridBagConstraints gbc_chckbxWrongLetterSound = new GridBagConstraints();
		gbc_chckbxWrongLetterSound.anchor = GridBagConstraints.WEST;
		gbc_chckbxWrongLetterSound.gridwidth = 3;
		gbc_chckbxWrongLetterSound.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWrongLetterSound.gridx = 0;
		gbc_chckbxWrongLetterSound.gridy = 5;
		add(chckbxWrongLetterSound, gbc_chckbxWrongLetterSound);
		
		chckbxShowWrongLetter = new JCheckBox("Show Wrong Letter in Red Color");
		chckbxShowWrongLetter.setSelected(true);
		GridBagConstraints gbc_chckbxShowWrongLetter = new GridBagConstraints();
		gbc_chckbxShowWrongLetter.anchor = GridBagConstraints.WEST;
		gbc_chckbxShowWrongLetter.gridwidth = 0;
		gbc_chckbxShowWrongLetter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxShowWrongLetter.gridx = 0;
		gbc_chckbxShowWrongLetter.gridy = 6;
		add(chckbxShowWrongLetter, gbc_chckbxShowWrongLetter);
		
		chckbxShowCursorIn = new JCheckBox("Show Cursor in Paragraph");
		chckbxShowCursorIn.setSelected(true);
		GridBagConstraints gbc_chckbxShowCursorIn = new GridBagConstraints();
		gbc_chckbxShowCursorIn.anchor = GridBagConstraints.WEST;
		gbc_chckbxShowCursorIn.gridwidth = 3;
		gbc_chckbxShowCursorIn.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxShowCursorIn.gridx = 0;
		gbc_chckbxShowCursorIn.gridy = 7;
		add(chckbxShowCursorIn, gbc_chckbxShowCursorIn);
		
		chckbxPlayLetterSound = new JCheckBox("Play Letter Sound");
		chckbxPlayLetterSound.setSelected(true);
		GridBagConstraints gbc_chckbxPlayLetterSound = new GridBagConstraints();
		gbc_chckbxPlayLetterSound.anchor = GridBagConstraints.WEST;
		gbc_chckbxPlayLetterSound.gridwidth = 2;
		gbc_chckbxPlayLetterSound.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPlayLetterSound.gridx = 0;
		gbc_chckbxPlayLetterSound.gridy = 8;
		add(chckbxPlayLetterSound, gbc_chckbxPlayLetterSound);
		
		JLabel lblFont = new JLabel("Font :");
		GridBagConstraints gbc_lblFont = new GridBagConstraints();
		gbc_lblFont.anchor = GridBagConstraints.EAST;
		gbc_lblFont.insets = new Insets(0, 0, 5, 5);
		gbc_lblFont.gridx = 0;
		gbc_lblFont.gridy = 9;
		add(lblFont, gbc_lblFont);
		Font f[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		
		fontBox = new JComboBox<>();
		for(Font value:f){
			fontBox.addItem(new DFont(value));
		}
		fontBox.addItemListener(this);
		GridBagConstraints gbc_fontBox = new GridBagConstraints();
		gbc_fontBox.gridwidth = 2;
		gbc_fontBox.insets = new Insets(0, 0, 5, 5);
		gbc_fontBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fontBox.gridx = 1;
		gbc_fontBox.gridy = 9;
		add(fontBox, gbc_fontBox);
		
		lblSampleText = new JLabel("Sample Text");
		GridBagConstraints gbc_lblSampleText = new GridBagConstraints();
		gbc_lblSampleText.gridwidth = 0;
		gbc_lblSampleText.gridheight = 2;
		gbc_lblSampleText.anchor = GridBagConstraints.WEST;
		gbc_lblSampleText.insets = new Insets(0, 5, 5, 0);
		gbc_lblSampleText.gridx = 3;
		gbc_lblSampleText.gridy = 9;
		add(lblSampleText, gbc_lblSampleText);
		
		lblFontSize = new JLabel("Font Size :");
		GridBagConstraints gbc_lblFontSize = new GridBagConstraints();
		gbc_lblFontSize.anchor = GridBagConstraints.EAST;
		gbc_lblFontSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontSize.gridx = 0;
		gbc_lblFontSize.gridy = 10;
		add(lblFontSize, gbc_lblFontSize);
		
		Integer i[]={8,10,12,14,18,20,25,30};
		fontSizeBox = new JComboBox<>();
		for(Integer value:i)fontSizeBox.addItem(value);
		fontSizeBox.setEditable(true);
		GridBagConstraints gbc_fontSizeBox = new GridBagConstraints();
		gbc_fontSizeBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fontSizeBox.insets = new Insets(0, 0, 5, 5);
		gbc_fontSizeBox.gridx = 1;
		gbc_fontSizeBox.gridy = 10;
		add(fontSizeBox, gbc_fontSizeBox);
		
		btnBold = new JToggleButton("B");
		btnBold.addItemListener(this);
		btnBold.setFont(new Font("Arial Black", Font.BOLD, 12));
		GridBagConstraints gbc_btnBold = new GridBagConstraints();
		gbc_btnBold.anchor = GridBagConstraints.WEST;
		gbc_btnBold.insets = new Insets(0, 0, 5, 5);
		gbc_btnBold.gridx = 2;
		gbc_btnBold.gridy = 10;
		add(btnBold, gbc_btnBold);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.anchor = GridBagConstraints.SOUTH;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 6;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 11;
		add(separator_1, gbc_separator_1);
		
		btnHome = new JButton("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridwidth = 6;
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 12;
		add(btnHome, gbc_btnHome);
		
		fontSizeBox.addItemListener(this);
	}
	public JLabel getUsername(){
		return username;
	}
	public void setActionLis(ActionListener al){
		btnHome.addActionListener(al);
		btnAdd.addActionListener(al);
		btnRemove.addActionListener(al);
	}
	public void setRemoveBtnEnabled(boolean enabled){
		btnRemove.setEnabled(enabled);
	}
	public JCheckBox getMusicChBox(){
		return chckbxMusic;
	}
	public JCheckBox getKeyStrokeSoundChBox(){
		return chckbxKeyStrokeSound;
	}
	public JCheckBox getWrongLetterSoundChBox(){
		return chckbxWrongLetterSound;
	}
	public JCheckBox getShowWrongLetterChBox(){
		return chckbxShowWrongLetter;
	}
	public JCheckBox getPlayLetterSoundChBox(){
		return chckbxPlayLetterSound;
	}
	public JComboBox<DMusic> getMusicBox(){
		return musicBox;
	}
	public JComboBox<DFont> getFontBox(){
		return fontBox;
	}
	public JComboBox<Integer> getFontSizeBox(){
		return fontSizeBox;
	}
	public JToggleButton getBoldBtn(){
		return btnBold;
	}
	public JCheckBox getShowCursorInPara(){
		return chckbxShowCursorIn;
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		//if(ie.getStateChange()==ItemEvent.SELECTED){
			Font f=(Font)fontBox.getItemAt(fontBox.getSelectedIndex());
			lblSampleText.setFont(new Font(f.getName(),getBoldBtn().isSelected()==true?Font.BOLD:Font.PLAIN,(Integer)getFontSizeBox().getSelectedItem()));
		//}
		
	}

}
