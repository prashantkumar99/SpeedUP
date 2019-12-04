package panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;


import java.awt.Color;

import javax.swing.JSeparator;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;


public class HomePanel extends JPanel {
	private JComboBox<String> username;
	private JButton btnLearnTyping;
	private JButton btnLetterTyping;
	private JButton btnWordTyping;
	private JButton btnParagraphTyping;
	private JButton btnOptions;
	private JButton btnAboutUs;
	private JButton btnExit;
	
	public static final String LEARN_TYPING_BTN="Learn Typing";
	public static final String LETTER_TYPING_BTN="Letter Typing";
	public static final String WORD_TYPING_BTN="Word Typing";
	public static final String PARA_TYPING_BTN="Paragraph Typing";
	public static final String OPTIONS_BTN="Options";
	public static final String ABOUT_US_BTN="About Us";
	public static final String REMOVE_BTN="Remove";
	public static final String EXIT_BTN="Exit";
	public static final String TYPING_GAME_BTN = "Typing Game";
	
	private JSeparator separator_2;
	private JButton btnRemove;
	private JLabel lblLoading;

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{346, 0, 231, 22, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSpeedup = new JLabel("SpeedUP");
		lblSpeedup.setForeground(new Color(255, 0, 0));
		lblSpeedup.setFont(new Font("Jokerman", Font.BOLD, 25));
		GridBagConstraints gbc_lblSpeedup = new GridBagConstraints();
		gbc_lblSpeedup.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpeedup.gridwidth = 5;
		gbc_lblSpeedup.gridx = 0;
		gbc_lblSpeedup.gridy = 0;
		add(lblSpeedup, gbc_lblSpeedup);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 5;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		lblLoading = new JLabel("Loading ...");
		lblLoading.setVisible(false);
		GridBagConstraints gbc_lblLoading = new GridBagConstraints();
		gbc_lblLoading.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoading.gridx = 0;
		gbc_lblLoading.gridy = 2;
		add(lblLoading, gbc_lblLoading);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		add(lblUsername, gbc_lblUsername);
		
		username = new JComboBox<>();
		username.setEditable(true);
		username.setSelectedIndex(-1);
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.gridx = 2;
		gbc_username.gridy = 2;
		add(username, gbc_username);
		
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridwidth = 2;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 2;
		add(btnRemove, gbc_btnRemove);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 5;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		add(separator_1, gbc_separator_1);
		
		btnLearnTyping = new JButton("Learn Typing");
		btnLearnTyping.setEnabled(false);
		btnLearnTyping.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_btnLearnTyping = new GridBagConstraints();
		gbc_btnLearnTyping.insets = new Insets(0, 0, 5, 0);
		gbc_btnLearnTyping.fill = GridBagConstraints.BOTH;
		gbc_btnLearnTyping.gridwidth = 5;
		gbc_btnLearnTyping.gridx = 0;
		gbc_btnLearnTyping.gridy = 4;
		add(btnLearnTyping, gbc_btnLearnTyping);
		
		btnLetterTyping = new JButton("Letter Typing");
		btnLetterTyping.setEnabled(false);
		btnLetterTyping.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_btnLetterTyping = new GridBagConstraints();
		gbc_btnLetterTyping.insets = new Insets(0, 0, 5, 0);
		gbc_btnLetterTyping.fill = GridBagConstraints.BOTH;
		gbc_btnLetterTyping.gridwidth = 5;
		gbc_btnLetterTyping.gridx = 0;
		gbc_btnLetterTyping.gridy = 5;
		add(btnLetterTyping, gbc_btnLetterTyping);
		
		btnWordTyping = new JButton("Word Typing");
		btnWordTyping.setEnabled(false);
		btnWordTyping.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_btnWordTyping = new GridBagConstraints();
		gbc_btnWordTyping.fill = GridBagConstraints.BOTH;
		gbc_btnWordTyping.gridwidth = 5;
		gbc_btnWordTyping.insets = new Insets(0, 0, 5, 0);
		gbc_btnWordTyping.gridx = 0;
		gbc_btnWordTyping.gridy = 6;
		add(btnWordTyping, gbc_btnWordTyping);
		
		btnParagraphTyping = new JButton("Paragraph Typing");
		btnParagraphTyping.setEnabled(false);
		btnParagraphTyping.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_btnParagraphTyping = new GridBagConstraints();
		gbc_btnParagraphTyping.insets = new Insets(0, 0, 5, 0);
		gbc_btnParagraphTyping.fill = GridBagConstraints.BOTH;
		gbc_btnParagraphTyping.gridwidth = 5;
		gbc_btnParagraphTyping.gridx = 0;
		gbc_btnParagraphTyping.gridy = 7;
		add(btnParagraphTyping, gbc_btnParagraphTyping);
		
		separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 5;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 8;
		add(separator_2, gbc_separator_2);
		
		btnOptions = new JButton("Options");
		btnOptions.setEnabled(false);
		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.anchor = GridBagConstraints.WEST;
		gbc_btnOptions.insets = new Insets(0, 0, 0, 5);
		gbc_btnOptions.gridx = 0;
		gbc_btnOptions.gridy = 9;
		add(btnOptions, gbc_btnOptions);
		
		btnAboutUs = new JButton("About Us");
		GridBagConstraints gbc_btnAboutUs = new GridBagConstraints();
		gbc_btnAboutUs.gridwidth = 2;
		gbc_btnAboutUs.anchor = GridBagConstraints.EAST;
		gbc_btnAboutUs.insets = new Insets(0, 0, 0, 5);
		gbc_btnAboutUs.gridx = 2;
		gbc_btnAboutUs.gridy = 9;
		add(btnAboutUs, gbc_btnAboutUs);
		
		btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.WEST;
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 9;
		add(btnExit, gbc_btnExit);

	}
	public JLabel getLoadingLabel(){
		return lblLoading;
	}
	public void setActionLis(ActionListener al){
		btnAboutUs.addActionListener(al);
		btnExit.addActionListener(al);
		btnLearnTyping.addActionListener(al);
		btnLetterTyping.addActionListener(al);
		btnParagraphTyping.addActionListener(al);
		btnOptions.addActionListener(al);
		btnWordTyping.addActionListener(al);
		btnRemove.addActionListener(al);
	}
	public void setUserSelected(boolean selected){
		btnLearnTyping.setEnabled(selected);
		btnLetterTyping.setEnabled(selected);
		btnOptions.setEnabled(selected);
		btnParagraphTyping.setEnabled(selected);
		btnWordTyping.setEnabled(selected);
		btnRemove.setEnabled(selected);
	}
	public JComboBox<String> getUsernameBox(){
		return username;
	}
	public JButton getRemoveUserBtn(){
		return btnRemove;
	}
}
