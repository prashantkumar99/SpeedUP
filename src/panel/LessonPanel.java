package panel;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JSeparator;
import javax.swing.event.DocumentListener;

public class LessonPanel extends JPanel {
	private JTextPane lessonTextPane;
	private JLabel lblLesson;
	private JPanel panel;
	private JTextPane paraText;
	private JTextPane textPane;
	private JSeparator separator;
	
	
	/**
	 * Create the panel.
	 */
	public LessonPanel() {
		setOpaque(false);
setLayout(new BorderLayout(0, 0));
		
		lblLesson = new JLabel("Lesson");
		lblLesson.setHorizontalAlignment(SwingConstants.CENTER);
		lblLesson.setVerticalAlignment(SwingConstants.TOP);
		lblLesson.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		add(lblLesson, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		
		lessonTextPane = new JTextPane();
		lessonTextPane.setEditable(false);
		lessonTextPane.setBorder(null);
		lessonTextPane.setOpaque(false);
		scrollPane.setViewportView(lessonTextPane);
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		paraText = new JTextPane();
		paraText.setForeground(Color.BLUE);
		paraText.setFont(new Font("Dialog", Font.PLAIN, 15));
		paraText.setEditable(false);
		paraText.setBackground(Color.WHITE);
		panel.add(paraText, BorderLayout.NORTH);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Dialog", Font.BOLD, 15));
		textPane.setBackground(new Color(255, 248, 220));
		panel.add(textPane, BorderLayout.SOUTH);
		
		separator = new JSeparator();
		panel.add(separator, BorderLayout.CENTER);
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				textPane.setText(null);
				textPane.requestFocus();
			}
		});
	}
	public void setLessonNo(int lessonNo){
		lblLesson.setText("Lesson "+String.valueOf(lessonNo));
	}
	public void setLessonText(String lessonText){
		lessonTextPane.setText(lessonText);
	}
	public JTextPane getLessonTextPane(){
		return lessonTextPane;
	}
	public void setLessonPractiseText(String ptext){
		this.paraText.setText(ptext);
	}
	public JTextPane getParaPane(){
		return this.paraText;
	}
	public JTextPane getTextPane(){
		return textPane;
	}
}
