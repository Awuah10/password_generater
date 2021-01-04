package com.password.generator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class generator_gui {

	private JFrame frame;
	private JTextField passwordLengthTextField;
	private JTextField generatedPasswordTextField;

	private String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String lowercase = "abcdefghijklmnopqrstuvxyz"; 
	private String stringNumbers = "0123456789";
	private String ascii = ".?*&^%$£!:@~#;";

	private String mainString = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					generator_gui window = new generator_gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public generator_gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MaxDevsPASS");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Close");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Character set:");
		lblNewLabel.setBounds(30, 25, 110, 14);
		lblNewLabel.setForeground(Color.BLUE);
		frame.getContentPane().add(lblNewLabel);

		/**
		 * handles lowercase checkbox
		 */
		JCheckBox lowercaseCheckBox = new JCheckBox("Lowercase(a-z)");
		lowercaseCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == 1) {
					mainString += lowercase;
				}
			}
		});
		lowercaseCheckBox.setBounds(186, 49, 128, 23);
		frame.getContentPane().add(lowercaseCheckBox);

		/**
		 * handles the numbers checkbox
		 */
		JCheckBox numbersCheckBox = new JCheckBox("Numbers(0-9)");
		numbersCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == 1) {
					mainString += stringNumbers;
				}
			}
		});
		numbersCheckBox.setBounds(377, 49, 134, 23);
		frame.getContentPane().add(numbersCheckBox);

		/**
		 * handles the uppercase checkbox
		 */
		JCheckBox uppercaseCheckBox = new JCheckBox("Uppercase(A-Z)");
		uppercaseCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == 1) {
					mainString += uppercase;
				}
			}
		});
		uppercaseCheckBox.setBounds(185, 77, 129, 23);
		frame.getContentPane().add(uppercaseCheckBox);

		/**
		 * handles the ascii checkbox
		 */
		JCheckBox asciiCheckBox = new JCheckBox("ASCII symbols(e.g. $\u00A3&*)");
		asciiCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == 1) {
					mainString += ascii;
				}
			}
		});
		asciiCheckBox.setBounds(377, 77, 188, 23);
		frame.getContentPane().add(asciiCheckBox);

		JLabel lblNewLabel_1 = new JLabel("Password length:");
		lblNewLabel_1.setBounds(30, 168, 116, 14);
		frame.getContentPane().add(lblNewLabel_1);

		passwordLengthTextField = new JTextField();
		passwordLengthTextField.setText("10");
		passwordLengthTextField.setBounds(207, 164, 27, 23);
		frame.getContentPane().add(passwordLengthTextField);
		passwordLengthTextField.setColumns(10);

		/**
		 * handles the minus button
		 */
		JButton minusButton = new JButton("-");
		minusButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				int number = Integer.parseInt(passwordLengthTextField.getText());

				if(number <= 10) {
					JOptionPane.showMessageDialog(frame, "Password length cannot be below 10 characters");
				}else {
					number--;
					passwordLengthTextField.setText(String.valueOf(number));
				}

			}
		});
		minusButton.setBounds(156, 164, 41, 23);
		frame.getContentPane().add(minusButton);

		/**
		 * handles the plus button
		 */
		JButton plusButton = new JButton("+");
		plusButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				int number = Integer.parseInt(passwordLengthTextField.getText());

				if(number >= 20) {
					JOptionPane.showMessageDialog(frame, "Password length cannot be exceed 20 characters");
				}else {
					number++;
					passwordLengthTextField.setText(String.valueOf(number));
				}
			}
		});
		plusButton.setBounds(244, 164, 41, 23);
		frame.getContentPane().add(plusButton);

		JLabel lblNewLabel_2 = new JLabel("Generated Password:");
		lblNewLabel_2.setBounds(30, 266, 134, 14);
		frame.getContentPane().add(lblNewLabel_2);	

		JButton resetButton = new JButton("Reset");
		resetButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				mainString = " ";
				passwordLengthTextField.setText("10");
				lowercaseCheckBox.setSelected(false);
				uppercaseCheckBox.setSelected(false);
				numbersCheckBox.setSelected(false);
				asciiCheckBox.setSelected(false);
			}
		});

		resetButton.setBounds(515, 355, 89, 23);
		frame.getContentPane().add(resetButton);


		generatedPasswordTextField = new JTextField();
		generatedPasswordTextField.setBounds(128, 294, 511, 29);
		frame.getContentPane().add(generatedPasswordTextField);
		generatedPasswordTextField.setColumns(10);

		/**
		 * generates the password based on user's choice of characters
		 */
		JButton generatePasswordButton = new JButton("Generate Password");
		generatePasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String SALTCHARS = mainString;
				StringBuilder salt = new StringBuilder();
				Random rnd = new Random();
				while (salt.length() < Integer.parseInt(passwordLengthTextField.getText())) { 
					int index = (int) (rnd.nextFloat() * SALTCHARS.length());
					salt.append(SALTCHARS.charAt(index));
				}
				String saltStr = salt.toString();
				generatedPasswordTextField.setText(saltStr); 

			}
		});
		generatePasswordButton.setBounds(207, 355, 152, 23);
		frame.getContentPane().add(generatePasswordButton);

		/**
		 * handles to saving
		 */
		JButton savePasswordButton = new JButton("Save");
		savePasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(generatedPasswordTextField.getText().isEmpty())
					JOptionPane.showMessageDialog(frame, "Please generate a password first");
				else {
					try {
						String site = JOptionPane.showInputDialog(frame, "Enter the website you are using password for");
						saveToFile(site, generatedPasswordTextField.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		savePasswordButton.setBounds(388, 355, 89, 23);
		frame.getContentPane().add(savePasswordButton);

	}

	/**
	 * saves the details to the created file
	 * @param siteName
	 * @param password
	 * @throws IOException 
	 */
	public void saveToFile(String siteName, String password) throws IOException {

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\\\Users\\\\awua1\\\\Documents\\\\Java_Projects\\\\MaxDevsPass.txt", true)));
			out.println("\n"+siteName + ": " + password);
			out.close();
			System.out.println("saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
