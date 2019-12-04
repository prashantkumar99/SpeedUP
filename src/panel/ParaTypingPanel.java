package panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JSeparator;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;

import custom.AlwaysActiveCaret;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import speedup.typing.para.Para;

public class ParaTypingPanel extends JPanel implements ItemListener{
	private JLabel username;
	private JButton btnStart;
	private JButton btnReset;
	private JButton btnHome;
	private JTextPane paraPane;
	private JTextPane textPane;
	private JLabel letterPerMin;
	private JLabel wordPerMin;
	private JComboBox<Para> paraNo;
	private JComboBox<String> paraType;
	private JProgressBar marks;
	private JLabel timeElapsed;
	
	public static final String START_BTN="Start";
	public static final String PAUSE_BTN="Pause";
	public static final String RESET_BTN="Reset";
	public static final String HOME_BTN="Home";
	public static final String ADD_BTN="Add";
	public static final String REMOVE_BTN="Remove";
	private JTable wordParaTable;
	private JTable noParaTable;
	
	private DefaultComboBoxModel<Para> wordModel,noModel;
	private DefaultComboBoxModel<Para> customModel;
	private JLabel noOfLetter;
	private JLabel wrongLetter;
	private JLabel correctLetter;
	private JButton btnAdd;
	private JTable customParaTable;

	/**
	 * Create the panel.
	 */
	public ParaTypingPanel() {
		setFocusTraversalPolicyProvider(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 151, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("Paragraph Typing");
		label.setForeground(new Color(255, 99, 71));
		label.setFont(new Font("Jokerman", Font.BOLD, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 3;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		username = new JLabel("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.anchor = GridBagConstraints.SOUTHEAST;
		gbc_username.gridx = 2;
		gbc_username.gridy = 0;
		add(username, gbc_username);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 0;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 0;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("TabbedPane.contentAreaColor"));
		tabbedPane.addTab("Current Report", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 62, 0, 0, 100, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblLetterMin = new JLabel("Letter \\ min = ");
		GridBagConstraints gbc_lblLetterMin = new GridBagConstraints();
		gbc_lblLetterMin.anchor = GridBagConstraints.EAST;
		gbc_lblLetterMin.insets = new Insets(0, 10, 5, 5);
		gbc_lblLetterMin.gridx = 0;
		gbc_lblLetterMin.gridy = 0;
		panel.add(lblLetterMin, gbc_lblLetterMin);
		
		letterPerMin = new JLabel("0");
		GridBagConstraints gbc_letterPerMin = new GridBagConstraints();
		gbc_letterPerMin.anchor = GridBagConstraints.WEST;
		gbc_letterPerMin.insets = new Insets(0, 0, 5, 5);
		gbc_letterPerMin.gridx = 1;
		gbc_letterPerMin.gridy = 0;
		panel.add(letterPerMin, gbc_letterPerMin);
		
		JLabel lblNoOfLetter = new JLabel("No of Letter = ");
		GridBagConstraints gbc_lblNoOfLetter = new GridBagConstraints();
		gbc_lblNoOfLetter.anchor = GridBagConstraints.EAST;
		gbc_lblNoOfLetter.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoOfLetter.gridx = 2;
		gbc_lblNoOfLetter.gridy = 0;
		panel.add(lblNoOfLetter, gbc_lblNoOfLetter);
		
		noOfLetter = new JLabel("0");
		GridBagConstraints gbc_noOfLetter = new GridBagConstraints();
		gbc_noOfLetter.anchor = GridBagConstraints.WEST;
		gbc_noOfLetter.insets = new Insets(0, 0, 5, 5);
		gbc_noOfLetter.gridx = 3;
		gbc_noOfLetter.gridy = 0;
		panel.add(noOfLetter, gbc_noOfLetter);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.VERTICAL;
		gbc_separator_1.gridheight = 4;
		gbc_separator_1.insets = new Insets(0, 0, 0, 5);
		gbc_separator_1.gridx = 4;
		gbc_separator_1.gridy = 0;
		panel.add(separator_1, gbc_separator_1);
		
		JLabel lblParagraph = new JLabel("Paragraph");
		GridBagConstraints gbc_lblParagraph = new GridBagConstraints();
		gbc_lblParagraph.gridwidth = 2;
		gbc_lblParagraph.insets = new Insets(0, 0, 5, 0);
		gbc_lblParagraph.gridx = 5;
		gbc_lblParagraph.gridy = 0;
		panel.add(lblParagraph, gbc_lblParagraph);
		
		JLabel lblWordMin = new JLabel("Word \\ min = ");
		GridBagConstraints gbc_lblWordMin = new GridBagConstraints();
		gbc_lblWordMin.anchor = GridBagConstraints.EAST;
		gbc_lblWordMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblWordMin.gridx = 0;
		gbc_lblWordMin.gridy = 1;
		panel.add(lblWordMin, gbc_lblWordMin);
		
		wordPerMin = new JLabel("0");
		GridBagConstraints gbc_wordPerMin = new GridBagConstraints();
		gbc_wordPerMin.anchor = GridBagConstraints.WEST;
		gbc_wordPerMin.insets = new Insets(0, 0, 5, 5);
		gbc_wordPerMin.gridx = 1;
		gbc_wordPerMin.gridy = 1;
		panel.add(wordPerMin, gbc_wordPerMin);
		
		JLabel lblCorrectLetter = new JLabel("Correct Letter = ");
		GridBagConstraints gbc_lblCorrectLetter = new GridBagConstraints();
		gbc_lblCorrectLetter.anchor = GridBagConstraints.EAST;
		gbc_lblCorrectLetter.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrectLetter.gridx = 2;
		gbc_lblCorrectLetter.gridy = 1;
		panel.add(lblCorrectLetter, gbc_lblCorrectLetter);
		
		correctLetter = new JLabel("0");
		GridBagConstraints gbc_correctLetter = new GridBagConstraints();
		gbc_correctLetter.anchor = GridBagConstraints.WEST;
		gbc_correctLetter.insets = new Insets(0, 0, 5, 5);
		gbc_correctLetter.gridx = 3;
		gbc_correctLetter.gridy = 1;
		panel.add(correctLetter, gbc_correctLetter);
		
		JLabel lblNo = new JLabel("Type");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.anchor = GridBagConstraints.EAST;
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 5;
		gbc_lblNo.gridy = 1;
		panel.add(lblNo, gbc_lblNo);
		
		String s[]={"Word","Number","Custom"};
		paraType = new JComboBox<>();
		for(String value:s)paraType.addItem(value);
		paraType.addItemListener(this);
		GridBagConstraints gbc_paraType = new GridBagConstraints();
		gbc_paraType.fill = GridBagConstraints.HORIZONTAL;
		gbc_paraType.insets = new Insets(0, 0, 5, 0);
		gbc_paraType.gridx = 6;
		gbc_paraType.gridy = 1;
		panel.add(paraType, gbc_paraType);
		
		JLabel lblTimeElapsed = new JLabel("Time Elapsed(min : sec)= ");
		GridBagConstraints gbc_lblTimeElapsed = new GridBagConstraints();
		gbc_lblTimeElapsed.anchor = GridBagConstraints.EAST;
		gbc_lblTimeElapsed.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeElapsed.gridx = 0;
		gbc_lblTimeElapsed.gridy = 2;
		panel.add(lblTimeElapsed, gbc_lblTimeElapsed);
		
		timeElapsed = new JLabel("0");
		GridBagConstraints gbc_timeElapsed = new GridBagConstraints();
		gbc_timeElapsed.anchor = GridBagConstraints.WEST;
		gbc_timeElapsed.insets = new Insets(0, 0, 5, 5);
		gbc_timeElapsed.gridx = 1;
		gbc_timeElapsed.gridy = 2;
		panel.add(timeElapsed, gbc_timeElapsed);
		
		JLabel lblWrongLetter = new JLabel("Wrong Letter = ");
		GridBagConstraints gbc_lblWrongLetter = new GridBagConstraints();
		gbc_lblWrongLetter.anchor = GridBagConstraints.EAST;
		gbc_lblWrongLetter.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrongLetter.gridx = 2;
		gbc_lblWrongLetter.gridy = 2;
		panel.add(lblWrongLetter, gbc_lblWrongLetter);
		
		wrongLetter = new JLabel("0");
		GridBagConstraints gbc_wrongLetter = new GridBagConstraints();
		gbc_wrongLetter.anchor = GridBagConstraints.WEST;
		gbc_wrongLetter.insets = new Insets(0, 0, 5, 5);
		gbc_wrongLetter.gridx = 3;
		gbc_wrongLetter.gridy = 2;
		panel.add(wrongLetter, gbc_wrongLetter);
		
		JLabel lblNo_1 = new JLabel("No.");
		GridBagConstraints gbc_lblNo_1 = new GridBagConstraints();
		gbc_lblNo_1.anchor = GridBagConstraints.EAST;
		gbc_lblNo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo_1.gridx = 5;
		gbc_lblNo_1.gridy = 2;
		panel.add(lblNo_1, gbc_lblNo_1);
		
		paraNo = new JComboBox<>();
		GridBagConstraints gbc_paraNo = new GridBagConstraints();
		gbc_paraNo.insets = new Insets(0, 0, 5, 0);
		gbc_paraNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_paraNo.gridx = 6;
		gbc_paraNo.gridy = 2;
		panel.add(paraNo, gbc_paraNo);
		
		
		JLabel lblMarks = new JLabel("Marks = ");
		GridBagConstraints gbc_lblMarks = new GridBagConstraints();
		gbc_lblMarks.anchor = GridBagConstraints.EAST;
		gbc_lblMarks.insets = new Insets(0, 0, 0, 5);
		gbc_lblMarks.gridx = 0;
		gbc_lblMarks.gridy = 3;
		panel.add(lblMarks, gbc_lblMarks);
		
		marks = new JProgressBar();
		marks.setStringPainted(true);
		GridBagConstraints gbc_marks = new GridBagConstraints();
		gbc_marks.gridwidth = 3;
		gbc_marks.fill = GridBagConstraints.HORIZONTAL;
		gbc_marks.insets = new Insets(0, 0, 0, 5);
		gbc_marks.gridx = 1;
		gbc_marks.gridy = 3;
		panel.add(marks, gbc_marks);
		
		JPanel panel_1 = new JPanel();

		panel_1.setBackground(UIManager.getColor("TabbedPane.contentAreaColor"));
		tabbedPane.addTab("Progress Report", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		panel_1.add(tabbedPane_1);
		
		JPanel panel_2 = new JPanel();
		
		tabbedPane_1.addTab("Word", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2, BorderLayout.CENTER);
		
		wordParaTable = new JTable();
		scrollPane_2.setViewportView(wordParaTable);
		
		JPanel panel_3 = new JPanel();
		
		tabbedPane_1.addTab("Number", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_3.add(scrollPane_3, BorderLayout.CENTER);
		
		noParaTable = new JTable();
		scrollPane_3.setViewportView(noParaTable);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Custom", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_4.add(scrollPane_4, BorderLayout.CENTER);
		
		customParaTable = new JTable();
		scrollPane_4.setViewportView(customParaTable);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 0;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		paraPane = new JTextPane();
		paraPane.setCaretColor(Color.RED);
		paraPane.setEditable(false);
		paraPane.setSelectionColor(Color.CYAN);
		paraPane.setForeground(Color.BLUE);
		paraPane.setFont(new Font("Dialog", Font.PLAIN, 15));
		paraPane.setBackground(Color.WHITE);
		scrollPane.setViewportView(paraPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 0;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Dialog", Font.BOLD, 15));
		textPane.setBackground(new Color(255, 248, 220));
		scrollPane_1.setViewportView(textPane);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 0;
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 5;
		add(separator_2, gbc_separator_2);
		
		btnStart = new JButton("Start");
		btnStart.setToolTipText("Ctrl");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 6;
		add(btnStart, gbc_btnStart);
		
		btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.anchor = GridBagConstraints.WEST;
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 6;
		add(btnReset, gbc_btnReset);
		
		btnHome = new JButton(HOME_BTN);
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridx = 2;
		gbc_btnHome.gridy = 6;
		add(btnHome, gbc_btnHome);
		
		wordModel=new DefaultComboBoxModel<>();
		for(int i=1;i<=100;i++){
			wordModel.addElement(new Para(new File("para\\word\\"+i+".txt"),i));
		}
		
		noModel=new DefaultComboBoxModel<>();
		for(int i=1;i<=20;i++){
			noModel.addElement(new Para(new File("para\\no\\"+i+".txt"),i));
		}
		
		customModel=new DefaultComboBoxModel<>();
		paraNo.setModel(wordModel);
		
		btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.gridwidth = 2;
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 3;
		panel.add(btnAdd, gbc_btnAdd);
	}
	public JLabel getUsername(){
		return username;
	}
	public void setActionLis(ActionListener al){
		btnAdd.addActionListener(al);
		btnHome.addActionListener(al);
		btnReset.addActionListener(al);
		btnStart.addActionListener(al);
	}
	public JLabel getLetterPerMin(){
		return letterPerMin;
	}
	public JLabel getWordPerMin(){
		return wordPerMin;
	}
	public JProgressBar getMarks(){
		return marks;
	}
	public JLabel getTimeElapsed(){
		return timeElapsed;
	}
	public JComboBox<String> getParaType(){
		return paraType;
	}
	public JComboBox<Para> getParaNo(){
		return paraNo;
	}
	public JTable getWordParaTable(){
		return wordParaTable;
	}
	public JTable getNumberParaTable(){
		return noParaTable;
	}
	public JTextPane getParaPane(){
		return paraPane;
	}
	public JTextPane getTextPane(){
		return textPane;
	}
	public JLabel getNoOfLetter(){
		return noOfLetter;
	}
	public JLabel getWrongLetter(){
		return wrongLetter;
	}
	public JLabel getCorrectLetter(){
		return correctLetter;
	}
	public JButton getStartBtn(){
		return btnStart;
	}
	public void setCaretVisible(boolean active){
		paraPane.setCaret(new AlwaysActiveCaret(active));
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED){
			getParaNo().setSelectedIndex(-1);
			if(paraType.getSelectedItem().toString().equals("Word")){
				paraNo.setModel(wordModel);
				btnAdd.setVisible(false);
			}
			if(paraType.getSelectedItem().toString().equals("Number")){
				paraNo.setModel(noModel);
				btnAdd.setVisible(false);
			}
			if(paraType.getSelectedItem().toString().equals("Custom")){
				paraNo.setModel(customModel);
				btnAdd.setVisible(true);
			}
		}
	}
	public JTable getCustomParaTable() {
		return customParaTable;
	}
	public DefaultComboBoxModel<Para> getCustomNoModel() {
		return customModel;
	}
	public void setCustomNoModel(DefaultComboBoxModel<Para> model) {
		customModel=model;
	}
}
