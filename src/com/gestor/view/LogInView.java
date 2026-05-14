package com.gestor.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LogInView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 */
	public LogInView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel ButtonsPanel = new JPanel();
		contentPane.add(ButtonsPanel, BorderLayout.SOUTH);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ButtonsPanel.add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		ButtonsPanel.add(btnSignUp);
		
		JPanel TextFieldsPanel = new JPanel();
		contentPane.add(TextFieldsPanel, BorderLayout.CENTER);
		TextFieldsPanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(65, 71, 54, 13);
		TextFieldsPanel.add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(154, 70, 186, 18);
		TextFieldsPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(65, 128, 79, 13);
		TextFieldsPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 127, 186, 18);
		TextFieldsPanel.add(passwordField);

	}
}
