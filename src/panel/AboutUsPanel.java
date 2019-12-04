package panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JSeparator;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AboutUsPanel extends JPanel {
	private JButton btnHome;
	
	public static final String HOME_BTN="Home";
	/**
	 * Create the panel.
	 */
	public AboutUsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{123, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAboutUs = new JLabel("About Us");
		lblAboutUs.setForeground(new Color(255, 99, 71));
		lblAboutUs.setFont(new Font("Jokerman", Font.BOLD, 20));
		GridBagConstraints gbc_lblAboutUs = new GridBagConstraints();
		gbc_lblAboutUs.insets = new Insets(0, 0, 5, 0);
		gbc_lblAboutUs.gridwidth = 3;
		gbc_lblAboutUs.gridx = 0;
		gbc_lblAboutUs.gridy = 0;
		add(lblAboutUs, gbc_lblAboutUs);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		JLabel lblFounder = new JLabel("Founder");
		GridBagConstraints gbc_lblFounder = new GridBagConstraints();
		gbc_lblFounder.anchor = GridBagConstraints.WEST;
		gbc_lblFounder.insets = new Insets(0, 10, 5, 5);
		gbc_lblFounder.gridx = 0;
		gbc_lblFounder.gridy = 2;
		add(lblFounder, gbc_lblFounder);
		
		JLabel lblPrashantKumar = new JLabel(": Prashant Kumar");
		GridBagConstraints gbc_lblPrashantKumar = new GridBagConstraints();
		gbc_lblPrashantKumar.anchor = GridBagConstraints.WEST;
		gbc_lblPrashantKumar.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrashantKumar.gridx = 1;
		gbc_lblPrashantKumar.gridy = 2;
		add(lblPrashantKumar, gbc_lblPrashantKumar);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 0;
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 3;
		add(separator_2, gbc_separator_2);
		
		JLabel lblCoFounder = new JLabel("Co - Founder");
		GridBagConstraints gbc_lblCoFounder = new GridBagConstraints();
		gbc_lblCoFounder.anchor = GridBagConstraints.WEST;
		gbc_lblCoFounder.insets = new Insets(0, 10, 5, 5);
		gbc_lblCoFounder.gridx = 0;
		gbc_lblCoFounder.gridy = 4;
		add(lblCoFounder, gbc_lblCoFounder);
		
		JLabel lblVikrantAgarwal = new JLabel(": Vikrant Agarwal");
		GridBagConstraints gbc_lblVikrantAgarwal = new GridBagConstraints();
		gbc_lblVikrantAgarwal.anchor = GridBagConstraints.WEST;
		gbc_lblVikrantAgarwal.insets = new Insets(0, 0, 5, 5);
		gbc_lblVikrantAgarwal.gridx = 1;
		gbc_lblVikrantAgarwal.gridy = 4;
		add(lblVikrantAgarwal, gbc_lblVikrantAgarwal);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridwidth = 0;
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 5;
		add(separator_3, gbc_separator_3);
		
		JLabel lblCompany = new JLabel("Company");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.anchor = GridBagConstraints.WEST;
		gbc_lblCompany.insets = new Insets(0, 10, 5, 5);
		gbc_lblCompany.gridx = 0;
		gbc_lblCompany.gridy = 6;
		add(lblCompany, gbc_lblCompany);
		
		JLabel lblDazzle = new JLabel(": Dazzle");
		GridBagConstraints gbc_lblDazzle = new GridBagConstraints();
		gbc_lblDazzle.anchor = GridBagConstraints.WEST;
		gbc_lblDazzle.insets = new Insets(0, 0, 5, 5);
		gbc_lblDazzle.gridx = 1;
		gbc_lblDazzle.gridy = 6;
		add(lblDazzle, gbc_lblDazzle);
		
		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.gridwidth = 0;
		gbc_separator_4.insets = new Insets(0, 0, 5, 0);
		gbc_separator_4.gridx = 0;
		gbc_separator_4.gridy = 7;
		add(separator_4, gbc_separator_4);
		
		JLabel lblContact = new JLabel("Contact");
		GridBagConstraints gbc_lblContact = new GridBagConstraints();
		gbc_lblContact.anchor = GridBagConstraints.WEST;
		gbc_lblContact.insets = new Insets(0, 10, 5, 5);
		gbc_lblContact.gridx = 0;
		gbc_lblContact.gridy = 8;
		add(lblContact, gbc_lblContact);
		
		JLabel lblPrashantkumarymailcom = new JLabel(": prashantkumar000@ymail.com");
		GridBagConstraints gbc_lblPrashantkumarymailcom = new GridBagConstraints();
		gbc_lblPrashantkumarymailcom.anchor = GridBagConstraints.WEST;
		gbc_lblPrashantkumarymailcom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrashantkumarymailcom.gridx = 1;
		gbc_lblPrashantkumarymailcom.gridy = 8;
		add(lblPrashantkumarymailcom, gbc_lblPrashantkumarymailcom);
		
		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.gridwidth = 0;
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 9;
		add(separator_5, gbc_separator_5);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.anchor = GridBagConstraints.SOUTH;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 10;
		add(separator_1, gbc_separator_1);
		
		btnHome = new JButton("Home");
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.anchor = GridBagConstraints.EAST;
		gbc_btnHome.gridwidth = 3;
		gbc_btnHome.gridx = 0;
		gbc_btnHome.gridy = 11;
		add(btnHome, gbc_btnHome);

	}
	public void setActionLis(ActionListener al){
		btnHome.addActionListener(al);
	}

}
