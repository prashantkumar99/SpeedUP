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
import java.awt.event.KeyListener;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;

public class LearnTypingPanel extends JPanel {
	private JButton btnHome;
	private JTabbedPane tabbedPane;

	public static final String HOME_BTN="Home";
	private JLabel username;
	private LessonPanel lessonPanel_14;
	/**
	 * Create the panel.
	 */
	public LearnTypingPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLearnTyping = new JLabel("Learn Typing");
		lblLearnTyping.setForeground(new Color(255, 99, 71));
		lblLearnTyping.setFont(new Font("Jokerman", Font.BOLD, 20));
		GridBagConstraints gbc_lblLearnTyping = new GridBagConstraints();
		gbc_lblLearnTyping.gridwidth = 2;
		gbc_lblLearnTyping.insets = new Insets(0, 0, 5, 5);
		gbc_lblLearnTyping.gridx = 0;
		gbc_lblLearnTyping.gridy = 0;
		add(lblLearnTyping, gbc_lblLearnTyping);
		
		username = new JLabel("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.anchor = GridBagConstraints.SOUTHWEST;
		gbc_username.gridx = 1;
		gbc_username.gridy = 0;
		add(username, gbc_username);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		add(tabbedPane, gbc_tabbedPane);
		
		LessonPanel lessonPanel = new LessonPanel();
		lessonPanel.setLessonPractiseText("aaaa aaaa aaaa aaaa");
		lessonPanel.setLessonText("Spacebar\r\n\r\nPlace your hands over the keyboard and curl your fingers (do not bend your wrists). Avoid resting your"
				+ " palm as this causes your wrist to bend \r\nPlace the tips of your fingers on the home keys, left hand little finger on the A key and "
				+ "the other fingers on the S, D and F keys, right hand little finger on the ; (semi-colon) key and other fingers on the L, K and J keys."
				+ " Keep your eyes on the screen. Do not look at the computer keyboard, press A with your left little finger 4 times and press spacebar"
				+ " with left (or right) thumb repeat the same until all the letters typed");
		lessonPanel.setLessonNo(1);
		tabbedPane.addTab("Lesson 1", null, lessonPanel, null);
		
		LessonPanel lessonPanel_1 = new LessonPanel();
		lessonPanel_1.setLessonPractiseText("asdf asdf asdf asdf");
		lessonPanel_1.setLessonText("Left Home Keys\r\na s d f  \r\n\r\nplace your fingers on home keys, starting with your left little finger on the A key,"
				+ " and ending with your right pinkie finger on the ; key. Rest both your thumbs on the SPACEBAR. \r\nBegin by moving only your left little"
				+ " finger (not your entire wrist), and strike the A key firmly once. Continue with the next finger on the S key, then the D key, then the "
				+ "F key. \r\n");
		lessonPanel_1.setLessonNo(2);
		tabbedPane.addTab("Lesson 2", null, lessonPanel_1, null);
		
		LessonPanel lessonPanel_2 = new LessonPanel();
		lessonPanel_2.setLessonPractiseText(";lkj ;lkj ;lkj ;lkj");
		lessonPanel_2.setLessonText("Right Home Keys\r\n; l k j  \r\n\r\nplace your fingers on home keys, starting with your left little finger on the A "
				+ "key, and ending with your right little finger on the ; key. Rest both your thumbs on the SPACEBAR. \r\nBegin by moving your right little "
				+ "finger and strike the ; key firmly once. Continue with the next finger on the L key, then the K key, then the J key. \r\n");
		lessonPanel_2.setLessonNo(3);
		tabbedPane.addTab("Lesson 3", null, lessonPanel_2, null);
		
		LessonPanel lessonPanel_3 = new LessonPanel();
		lessonPanel_3.setLessonPractiseText("asdfg ;lkjh asdfg ;lkjh");
		lessonPanel_3.setLessonText("Home Keys\r\na s d fgh j k l ;\r\n \r\nPlace your fingers on Home keys. \r\nBegin by moving your left little finger and "
				+ "strike the A key firmly once. Continue with the next finger on the S key, then the D key, then the F key and then G key with your left "
				+ "index finger. Repeat the action with your right hand, starting with your right little finger on the ; key, then continue with the next "
				+ "finger on the L key, then the K key, then the J key and then H key with your right index finger. \r\n");
		lessonPanel_3.setLessonNo(4);
		tabbedPane.addTab("Lesson 4", null, lessonPanel_3, null);
		
		LessonPanel lessonPanel_4 = new LessonPanel();
		lessonPanel_4.setLessonPractiseText("qwer qwer qwer qwer");
		lessonPanel_4.setLessonText("Left Upper Row\r\nq w e r  \r\n\r\nplace your fingers on home keys, \r\nBegin by moving your left little finger, and"
				+ " strike the Q key firmly once. Continue with the next finger on the W key, then the E key, then the R key. \r\n");
		lessonPanel_4.setLessonNo(5);
		tabbedPane.addTab("Lesson 5", null, lessonPanel_4, null);
		
		LessonPanel lessonPanel_5 = new LessonPanel();
		lessonPanel_5.setLessonPractiseText("poiu poiu poiu poiu");
		lessonPanel_5.setLessonText("Right Upper Row\r\np o i u  \r\n\r\nplace your fingers on home keys, \r\nBegin by moving your right little finger, "
				+ "and strike the P key firmly once. Continue with the next finger on the O key, then the I key, then the U key. \r\n");
		lessonPanel_5.setLessonNo(6);
		tabbedPane.addTab("Lesson 6", null, lessonPanel_5, null);
		
		LessonPanel lessonPanel_6 = new LessonPanel();
		lessonPanel_6.setLessonPractiseText("qwert poiuy qwert poiuy");
		lessonPanel_6.setLessonText("Upper Row\r\nq w e r t p o i u y \r\n\r\nPlace your fingers on Home keys, begin by moving your left little finger and "
				+ "strike the Q key firmly once. Continue with the next finger on the W key, then the E key, then the R key and then T key with your left "
				+ "index finger. Repeat the action with your right hand, starting with your right little finger on the P key, then continue with the next "
				+ "finger on the O key, then the I key, then the U key and then Y key with your right index finger. \r\n");
		lessonPanel_6.setLessonNo(7);
		tabbedPane.addTab("Lesson 7", null, lessonPanel_6, null);
		
		LessonPanel lessonPanel_7 = new LessonPanel();
		lessonPanel_7.setLessonPractiseText("zxcv zxcv zxcv zxcv");
		lessonPanel_7.setLessonText("Left Lower Row\r\nz x c v  \r\n\r\nplace your fingers on home keys, \r\nBegin by moving your left little finger, and "
				+ "strike the Z key firmly once. Continue with the next finger on the X key, then the C key, then the V key. \r\n");
		lessonPanel_7.setLessonNo(8);
		tabbedPane.addTab("Lesson 8", null, lessonPanel_7, null);
		
		LessonPanel lessonPanel_8 = new LessonPanel();
		lessonPanel_8.setLessonPractiseText("/.,m /.,m /.,m /.,m");
		lessonPanel_8.setLessonText("Right Lower Row\r\n/ . , m \r\n\r\nplace your fingers on home keys, \r\nBegin by moving your right little finger, and "
				+ "strike the / key firmly once. Continue with the next finger on the . key then the , key then the M key. \r\n");
		lessonPanel_8.setLessonNo(9);
		tabbedPane.addTab("Lesson 9", null, lessonPanel_8, null);
		
		LessonPanel lessonPanel_9 = new LessonPanel();
		lessonPanel_9.setLessonPractiseText("zxcvb /.,mn zxcvb /.,mn");
		lessonPanel_9.setLessonText("Lower Row\r\nz x c v b / . , m n\r\n\r\nPlace your fingers on Home keys, begin by moving your left little finger and"
				+ " strike the Z key firmly once, continue with the next finger on the X key, then the C key, then the V key and then B key with your left"
				+ " index finger. Repeat the action with your right hand, starting with your right little finger on the / key then continue with the next"
				+ " finger on the . key then the , key then the M key and then N key with your right index finger. \r\n");
		lessonPanel_9.setLessonNo(10);
		tabbedPane.addTab("Lesson 10", null, lessonPanel_9, null);
		
		LessonPanel lessonPanel_10 = new LessonPanel();
		lessonPanel_10.setLessonPractiseText("asdf qwer ;lkj poiu");
		lessonPanel_10.setLessonText("Home Keys and Upper Row\r\na s d f q w e r ; l k j p o i u\r\n\r\nPlace your fingers on Home keys and begin the exercise.\r\nStrike ASDFQWER keys with your left fingers\r\nStrike ;LKJPOIU keys with your right fingers");
		lessonPanel_10.setLessonNo(11);
		tabbedPane.addTab("Lesson 11", null, lessonPanel_10, null);
		
		LessonPanel lessonPanel_11 = new LessonPanel();
		lessonPanel_11.setLessonPractiseText("asdf zxcv ;lkj /.,m");
		lessonPanel_11.setLessonText("Home Keys and Lower Row\r\na s d f z x c v ; l k j / . , m\r\n\r\nPlace your fingers on Home keys and begin the "
				+ "exercise. \r\nStrike ASDFZXCV keys with your left fingers\r\nStrike ;LKJ/.,M keys with your right fingers\r\n");
		lessonPanel_11.setLessonNo(12);
		tabbedPane.addTab("Lesson 12", null, lessonPanel_11, null);
		
		LessonPanel lessonPanel_12 = new LessonPanel();
		lessonPanel_12.setLessonPractiseText("Asdf Jkl; Qwert Uiop Zxcv M,./");
		lessonPanel_12.setLessonText("Shift Key\r\nAsdf Jkl; Qwert Uiop Zxcv M,./\r\n\r\nPlace your fingers on Home keys and begin the exercise. \r\nTo "
				+ "capitalize AJQUZM letters press the letter and at the same time with your other hand press the shift key with your little finger. The "
				+ "shift key is to the left of Z for the left hand and to the right of / for the right hand");
		lessonPanel_12.setLessonNo(13);
		tabbedPane.addTab("Lesson 13", null, lessonPanel_12, null);
		
		LessonPanel lessonPanel_13 = new LessonPanel();
		lessonPanel_13.setLessonPractiseText("1234 5678 90-=");
		lessonPanel_13.setLessonText("Numeric Keys\r\n1234 5678 90-=\r\n\r\nPlace your fingers on Home keys and begin the exercise.\r\nStrike 123456 keys "
				+ "with your left fingers\r\nStrike 7890-= keys with your right fingers\r\n");
		lessonPanel_13.setLessonNo(14);
		tabbedPane.addTab("Lesson 14", null, lessonPanel_13, null);
		
		lessonPanel_14 = new LessonPanel();
		lessonPanel_14.setLessonPractiseText("The Quick Brown Fox Jumps over the lazy dog.");
		lessonPanel_14.setLessonText("All Keys\r\n\r\nThe Quick Brown Fox Jumps over the lazy dog.\r\n\r\nPlace your fingers on Home keys and begin the exercise. \r\n\r\nAfter then go to home and try Letter Typing.");
		lessonPanel_14.setLessonNo(15);
		tabbedPane.addTab("Lesson 15", null, lessonPanel_14, null);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		add(separator_1, gbc_separator_1);
		
		btnHome = new JButton("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.gridwidth = 2;
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 4;
		add(btnHome, gbc_btnHome);
	}
	public JLabel getUsername(){
		return username;
	}
	public void setActionLis(ActionListener al){
		btnHome.addActionListener(al);
	}
	public void setDocumentLis(DocumentListener dl,KeyListener kl){
		for(int i=0;i<tabbedPane.getTabCount();i++){
			LessonPanel lp=(LessonPanel) tabbedPane.getComponentAt(i);
			lp.getTextPane().getDocument().addDocumentListener(dl);
			lp.getTextPane().addKeyListener(kl);
		}
	}
	public void setNewFont(Font f){
		for(int i=0;i<tabbedPane.getTabCount();i++){
			LessonPanel lp=(LessonPanel) tabbedPane.getComponentAt(i);
			lp.getParaPane().setFont(f);
			lp.getTextPane().setFont(f);
		}
	}
	public JTextPane getTextPane(){
		LessonPanel lp= (LessonPanel) tabbedPane.getSelectedComponent();
		return lp.getTextPane();
	}
	public LessonPanel getLastLessonPnl(){
		return lessonPanel_14;
	}
	public JTextPane getParaPane(){
		LessonPanel lp= (LessonPanel) tabbedPane.getSelectedComponent();
		return lp.getParaPane();
	}
	public JTabbedPane getLessonTabbedPane(){
		return tabbedPane;
	}
	public JLabel getUsernameLabel(){
		return username;
	}
}
