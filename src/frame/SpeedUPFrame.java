package frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.CardLayout;

import panel.AboutUsPanel;
import panel.HomePanel;
import panel.LearnTypingPanel;
import panel.LetterTypingPanel;
import panel.WordTypingPanel;
import panel.ParaTypingPanel;
import panel.OptionsPanel;

public class SpeedUPFrame extends JFrame {

	private JPanel contentPane;
	private HomePanel homePanel;
	private LetterTypingPanel letterTypingPanel;
	private WordTypingPanel wordTypingPanel;
	private ParaTypingPanel paraTypingPanel;
	private AboutUsPanel aboutUsPanel;
	private LearnTypingPanel learnTypingPanel;
	private CardLayout card;
	private OptionsPanel optionsPanel;
	/**
	 * Create the frame.
	 */
	public SpeedUPFrame() {
		setTitle("SpeedUP");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\speedup.png"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 722, 562);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		card=new CardLayout();
		contentPane.setLayout(card);
		
		homePanel = new HomePanel();
		contentPane.add(homePanel, "Home Panel");
		
		letterTypingPanel = new LetterTypingPanel();
		contentPane.add(letterTypingPanel, "Letter Typing Panel");
		
		wordTypingPanel = new WordTypingPanel();
		contentPane.add(wordTypingPanel, "Word Typing Panel");
		
		paraTypingPanel = new ParaTypingPanel();
		contentPane.add(paraTypingPanel, "Para Typing Panel");
		
		aboutUsPanel = new AboutUsPanel();
		contentPane.add(aboutUsPanel, "About Us Panel");
		
		optionsPanel = new OptionsPanel();
		contentPane.add(optionsPanel, "Options Panel");
		
		learnTypingPanel = new LearnTypingPanel();
		contentPane.add(learnTypingPanel, "Learn Typing Panel");
		
	}
	public void showAboutUsPanel(){
		card.show(contentPane, "About Us Panel");
	}
	public void showOptionsPanel(){
		card.show(contentPane, "Options Panel");
	}
	public void showLetterTypingPanel(){
		card.show(contentPane,"Letter Typing Panel");
	}
	public void showWordTypingPanel(){
		card.show(contentPane,"Word Typing Panel");
	}
	public void showParaTypingPanel(){
		card.show(contentPane,"Para Typing Panel");
	}
	public void showLearnTypingPanel(){
		card.show(contentPane,"Learn Typing Panel");
	}
	public void showHomePanel(){
		card.show(contentPane, "Home Panel");
	}
	public AboutUsPanel getAboutUsPanel(){
		return aboutUsPanel;
	}
	public OptionsPanel getOptionsPanel(){
		return optionsPanel;
	}
	public LetterTypingPanel getLetterTypingPanel(){
		return letterTypingPanel;
	}
	public LearnTypingPanel getLearnTypingPanel(){
		return learnTypingPanel;
	}
	public WordTypingPanel getWordTypingPanel(){
		return wordTypingPanel;
	}
	public ParaTypingPanel getParaTypingPanel(){
		return paraTypingPanel;
	}
	public HomePanel getHomePanel(){
		return homePanel;
	}
	public void showTypingGamePnl() {
		card.show(contentPane, "Typing Game Panel");
	}
}
