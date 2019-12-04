package dialog;

import javax.swing.JDialog;




import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AddParaDialog extends JDialog {
	private JButton btnChooseTextFile;
	private JTextPane textPane;
	private JButton btnOk;
	private JButton btnCancel;

	public static final String CHOOSE_TEXT_FILE_BTN="Choose text file";
	public static final String OK_BTN="OK";
	public static final String CANCEL_BTN="Cancel";

	/**
	 * Create the dialog.
	 */
	public AddParaDialog() {
		setModal(true);
		setTitle("Add Paragraph");
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		btnChooseTextFile = new JButton("Choose text file");
		GridBagConstraints gbc_btnChooseTextFile = new GridBagConstraints();
		gbc_btnChooseTextFile.insets = new Insets(5, 0, 5, 0);
		gbc_btnChooseTextFile.gridwidth = 2;
		gbc_btnChooseTextFile.gridx = 0;
		gbc_btnChooseTextFile.gridy = 0;
		getContentPane().add(btnChooseTextFile, gbc_btnChooseTextFile);
		
		JLabel lblOr = new JLabel("OR");
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 0);
		gbc_lblOr.gridwidth = 2;
		gbc_lblOr.gridx = 0;
		gbc_lblOr.gridy = 1;
		getContentPane().add(lblOr, gbc_lblOr);
		
		JLabel lblPasteTextHere = new JLabel("Paste text here.");
		GridBagConstraints gbc_lblPasteTextHere = new GridBagConstraints();
		gbc_lblPasteTextHere.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasteTextHere.gridwidth = 2;
		gbc_lblPasteTextHere.gridx = 0;
		gbc_lblPasteTextHere.gridy = 2;
		getContentPane().add(lblPasteTextHere, gbc_lblPasteTextHere);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setViewportView(textPane);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 5, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 4;
		getContentPane().add(separator, gbc_separator);
		
		btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 5;
		getContentPane().add(btnOk, gbc_btnOk);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 5;
		getContentPane().add(btnCancel, gbc_btnCancel);

	}
	public void setActionLis(ActionListener al){
		btnCancel.addActionListener(al);
		btnChooseTextFile.addActionListener(al);
		btnOk.addActionListener(al);
	}
	public JTextPane getTextPane(){
		return textPane;
	}

}
