package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UIManager;

public class LetterTypingPanel extends JPanel {
	
	private JLabel username;
	private JLabel noOfTyped;
	private JLabel correct;
	private JProgressBar marks;
	private JLabel perMin;
	private JLabel wrong;
	private JCheckBox chckbxSmallLetters;
	private JCheckBox chckbxCapitalLetters;
	private JCheckBox chckbxNumbers;
	private JCheckBox chckbxSymbols;
	private JComboBox<Double> minTime;
	private JLabel letter;
	private JTextPane textPane;
	private JProgressBar timeLeft;
	private JButton btnStart;
	private JButton btnReset;
	private JButton btnHome;
	
	public static final String START_BTN="Start";
	public static final String PAUSE_BTN="Pause";
	public static final String RESET_BTN="Reset";
	public static final String HOME_BTN="Home";
	/**
	 * Create the panel.
	 */
	public LetterTypingPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 46, 54};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTyping = new JLabel("Letter Typing");
		lblTyping.setForeground(new Color(255, 99, 71));
		lblTyping.setFont(new Font("Jokerman", Font.BOLD, 20));
		GridBagConstraints gbc_lblTyping = new GridBagConstraints();
		gbc_lblTyping.insets = new Insets(0, 0, 5, 0);
		gbc_lblTyping.gridheight = 2;
		gbc_lblTyping.gridwidth = 9;
		gbc_lblTyping.gridx = 0;
		gbc_lblTyping.gridy = 0;
		add(lblTyping, gbc_lblTyping);
		
		username = new JLabel("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 0);
		gbc_username.anchor = GridBagConstraints.SOUTHEAST;
		gbc_username.gridwidth = 9;
		gbc_username.gridx = 0;
		gbc_username.gridy = 1;
		add(username, gbc_username);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 9;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		
		JLabel lblProgressReport = new JLabel("Progress Report");
		GridBagConstraints gbc_lblProgressReport = new GridBagConstraints();
		gbc_lblProgressReport.gridwidth = 6;
		gbc_lblProgressReport.insets = new Insets(0, 0, 5, 5);
		gbc_lblProgressReport.gridx = 0;
		gbc_lblProgressReport.gridy = 3;
		add(lblProgressReport, gbc_lblProgressReport);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.VERTICAL;
		gbc_separator_1.gridheight = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 6;
		gbc_separator_1.gridy = 3;
		add(separator_1, gbc_separator_1);
		
		chckbxSmallLetters = new JCheckBox("Small Letters");
		chckbxSmallLetters.setSelected(true);
		GridBagConstraints gbc_chckbxSmallLetters = new GridBagConstraints();
		gbc_chckbxSmallLetters.anchor = GridBagConstraints.WEST;
		gbc_chckbxSmallLetters.gridwidth = 2;
		gbc_chckbxSmallLetters.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSmallLetters.gridx = 7;
		gbc_chckbxSmallLetters.gridy = 3;
		add(chckbxSmallLetters, gbc_chckbxSmallLetters);
		
		JLabel lblMin = new JLabel("Letter \\ min = ");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.gridwidth = 2;
		gbc_lblMin.anchor = GridBagConstraints.EAST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 0;
		gbc_lblMin.gridy = 4;
		add(lblMin, gbc_lblMin);
		
		perMin = new JLabel("0");
		GridBagConstraints gbc_perMin = new GridBagConstraints();
		gbc_perMin.anchor = GridBagConstraints.WEST;
		gbc_perMin.insets = new Insets(0, 0, 5, 5);
		gbc_perMin.gridx = 2;
		gbc_perMin.gridy = 4;
		add(perMin, gbc_perMin);
		
		JLabel lblNoOfTyped = new JLabel("No. of Letter Typed = ");
		GridBagConstraints gbc_lblNoOfTyped = new GridBagConstraints();
		gbc_lblNoOfTyped.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfTyped.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfTyped.gridx = 4;
		gbc_lblNoOfTyped.gridy = 4;
		add(lblNoOfTyped, gbc_lblNoOfTyped);
		
		noOfTyped = new JLabel("0");
		GridBagConstraints gbc_noOfTyped = new GridBagConstraints();
		gbc_noOfTyped.anchor = GridBagConstraints.WEST;
		gbc_noOfTyped.insets = new Insets(0, 0, 5, 5);
		gbc_noOfTyped.gridx = 5;
		gbc_noOfTyped.gridy = 4;
		add(noOfTyped, gbc_noOfTyped);
		
		chckbxCapitalLetters = new JCheckBox("Capital Letters");
		chckbxCapitalLetters.setSelected(true);
		GridBagConstraints gbc_chckbxCapitalLetters = new GridBagConstraints();
		gbc_chckbxCapitalLetters.anchor = GridBagConstraints.WEST;
		gbc_chckbxCapitalLetters.gridwidth = 2;
		gbc_chckbxCapitalLetters.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxCapitalLetters.gridx = 7;
		gbc_chckbxCapitalLetters.gridy = 4;
		add(chckbxCapitalLetters, gbc_chckbxCapitalLetters);
		
		JLabel lblCorrect = new JLabel("Correct Letter = ");
		GridBagConstraints gbc_lblCorrect = new GridBagConstraints();
		gbc_lblCorrect.gridwidth = 2;
		gbc_lblCorrect.anchor = GridBagConstraints.EAST;
		gbc_lblCorrect.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrect.gridx = 0;
		gbc_lblCorrect.gridy = 5;
		add(lblCorrect, gbc_lblCorrect);
		
		correct = new JLabel("0");
		GridBagConstraints gbc_correct = new GridBagConstraints();
		gbc_correct.anchor = GridBagConstraints.WEST;
		gbc_correct.insets = new Insets(0, 0, 5, 5);
		gbc_correct.gridx = 2;
		gbc_correct.gridy = 5;
		add(correct, gbc_correct);
		
		JLabel lblWrong = new JLabel("Wrong Letter = ");
		GridBagConstraints gbc_lblWrong = new GridBagConstraints();
		gbc_lblWrong.anchor = GridBagConstraints.EAST;
		gbc_lblWrong.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrong.gridx = 4;
		gbc_lblWrong.gridy = 5;
		add(lblWrong, gbc_lblWrong);
		
		chckbxNumbers = new JCheckBox("Numbers");
		GridBagConstraints gbc_chckbxNumbers = new GridBagConstraints();
		gbc_chckbxNumbers.anchor = GridBagConstraints.WEST;
		gbc_chckbxNumbers.gridwidth = 2;
		gbc_chckbxNumbers.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNumbers.gridx = 7;
		gbc_chckbxNumbers.gridy = 5;
		add(chckbxNumbers, gbc_chckbxNumbers);
		
		JLabel lblMarks = new JLabel("Marks = ");
		GridBagConstraints gbc_lblMarks = new GridBagConstraints();
		gbc_lblMarks.gridwidth = 2;
		gbc_lblMarks.anchor = GridBagConstraints.EAST;
		gbc_lblMarks.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarks.gridx = 0;
		gbc_lblMarks.gridy = 6;
		add(lblMarks, gbc_lblMarks);
		
		marks = new JProgressBar();
		marks.setStringPainted(true);
		GridBagConstraints gbc_marks = new GridBagConstraints();
		gbc_marks.gridwidth = 4;
		gbc_marks.fill = GridBagConstraints.HORIZONTAL;
		gbc_marks.insets = new Insets(0, 0, 5, 5);
		gbc_marks.gridx = 2;
		gbc_marks.gridy = 6;
		add(marks, gbc_marks);
		
		wrong = new JLabel("0");
		GridBagConstraints gbc_wrong = new GridBagConstraints();
		gbc_wrong.anchor = GridBagConstraints.WEST;
		gbc_wrong.insets = new Insets(0, 0, 5, 5);
		gbc_wrong.gridx = 5;
		gbc_wrong.gridy = 5;
		add(wrong, gbc_wrong);
		
		chckbxSymbols = new JCheckBox("Symbols");
		GridBagConstraints gbc_chckbxSymbols = new GridBagConstraints();
		gbc_chckbxSymbols.anchor = GridBagConstraints.WEST;
		gbc_chckbxSymbols.gridwidth = 2;
		gbc_chckbxSymbols.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSymbols.gridx = 7;
		gbc_chckbxSymbols.gridy = 6;
		add(chckbxSymbols, gbc_chckbxSymbols);
		
		JLabel lblMinimumTimeFor = new JLabel("Minimum time for Letter each to type(sec) = ");
		GridBagConstraints gbc_lblMinimumTimeFor = new GridBagConstraints();
		gbc_lblMinimumTimeFor.anchor = GridBagConstraints.EAST;
		gbc_lblMinimumTimeFor.gridwidth = 5;
		gbc_lblMinimumTimeFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinimumTimeFor.gridx = 3;
		gbc_lblMinimumTimeFor.gridy = 7;
		add(lblMinimumTimeFor, gbc_lblMinimumTimeFor);
		
		Double d[]={new Double(0.2),new Double(0.5),new Double(0.7),new Double(1.0),new Double(2.0),new Double(3.0),new Double(4.0),new Double(5.0)};
		minTime = new JComboBox<>();
		for(Double value:d)minTime.addItem(value);
		GridBagConstraints gbc_minTime = new GridBagConstraints();
		gbc_minTime.insets = new Insets(0, 0, 5, 0);
		gbc_minTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_minTime.gridx = 8;
		gbc_minTime.gridy = 7;
		add(minTime, gbc_minTime);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 9;
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 8;
		add(separator_2, gbc_separator_2);
		
		letter = new JLabel("Letter");
		letter.setForeground(Color.BLUE);
		letter.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_letter = new GridBagConstraints();
		gbc_letter.insets = new Insets(0, 0, 5, 0);
		gbc_letter.gridwidth = 9;
		gbc_letter.gridx = 0;
		gbc_letter.gridy = 9;
		add(letter, gbc_letter);
		
		JLabel lblTimeLeft = new JLabel("Time Left :");
		GridBagConstraints gbc_lblTimeLeft = new GridBagConstraints();
		gbc_lblTimeLeft.anchor = GridBagConstraints.EAST;
		gbc_lblTimeLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeLeft.gridx = 3;
		gbc_lblTimeLeft.gridy = 10;
		add(lblTimeLeft, gbc_lblTimeLeft);
		
		timeLeft = new JProgressBar();
		timeLeft.setBackground(UIManager.getColor("ProgressBar.foreground"));
		timeLeft.setForeground(UIManager.getColor("ProgressBar.background"));
		GridBagConstraints gbc_timeLeft = new GridBagConstraints();
		gbc_timeLeft.insets = new Insets(0, 0, 5, 0);
		gbc_timeLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeLeft.gridwidth = 5;
		gbc_timeLeft.gridx = 4;
		gbc_timeLeft.gridy = 10;
		add(timeLeft, gbc_timeLeft);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 11;
		add(scrollPane, gbc_scrollPane);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Modern No. 20", Font.BOLD, 16));
		textPane.setBackground(new Color(245, 245, 220));
		scrollPane.setViewportView(textPane);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.gridwidth = 9;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 12;
		add(separator_3, gbc_separator_3);
		
		btnStart = new JButton("Start");
		btnStart.setToolTipText("Ctrl");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.anchor = GridBagConstraints.WEST;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 13;
		add(btnStart, gbc_btnStart);
		
		btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.anchor = GridBagConstraints.WEST;
		gbc_btnReset.gridwidth = 2;
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 13;
		add(btnReset, gbc_btnReset);
		
		btnHome = new JButton("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridwidth = 2;
		gbc_btnHome.gridx = 7;
		gbc_btnHome.gridy = 13;
		add(btnHome, gbc_btnHome);
	}
	public void setActionLis(ActionListener al){
		btnHome.addActionListener(al);
		btnReset.addActionListener(al);
		btnStart.addActionListener(al);
	}
	public void setTypingMode(boolean started){
		chckbxSmallLetters.setEnabled(!started);
		chckbxCapitalLetters.setEnabled(!started);
		chckbxNumbers.setEnabled(!started);
		chckbxSymbols.setEnabled(!started);
		minTime.setEnabled(!started);
		
	}
	public JLabel getUsernameLabel(){
		return username;
	}
	public JLabel getNoOfTypedLabel(){
		return noOfTyped;
	}
	public JLabel getCorrectValueLabel(){
		return correct;
	}
	public JLabel getWrongValueLabel(){
		return wrong;
	}
	public JLabel getPerMinLabel(){
		return perMin;
	}
	public JProgressBar getMarksBar(){
		return marks;
	}
	public JButton getStartBtn(){
		return btnStart;
	}
	public JComboBox<Double> getMinTimeBox(){
		return minTime;
	}
	public JLabel getLetterLabel(){
		//change this letter to word in word typing 
		return letter;
	}
	public JProgressBar getTimeLeftBar(){
		return timeLeft;
	}
	public JTextPane getTextPane(){
		return textPane;
	}
	//till now can be copied to word typing
	
	public JCheckBox getSmallLetterChBox(){
		return chckbxSmallLetters;
	}
	public JCheckBox getCapitalLetterChBox(){
		return chckbxCapitalLetters;
	}
	public JCheckBox getNumberChBox(){
		return chckbxNumbers;
	}
	public JCheckBox getSymbolChBox(){
		return chckbxSymbols;
	}
	
}
