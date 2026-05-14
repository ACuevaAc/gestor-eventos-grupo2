package com.gestor.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUpView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Create the frame.
	 */
	public SignUpView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel ButtonsPanel = new JPanel();
		contentPane.add(ButtonsPanel, BorderLayout.SOUTH);
		
		JButton btnCreate = new JButton("Create Account");
		ButtonsPanel.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		ButtonsPanel.add(btnBack);
		
		JPanel FormPanel = new JPanel();
		contentPane.add(FormPanel, BorderLayout.CENTER);
		FormPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(9, 15, 85, 31);
		FormPanel.add(lblNewLabel);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEdad.setBounds(10, 47, 85, 31);
		FormPanel.add(lblEdad);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 78, 60, 31);
		FormPanel.add(lblEmail);
		
		JLabel lblConfirmarEmail = new JLabel("Confirmar Email");
		lblConfirmarEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmarEmail.setBounds(10, 103, 143, 55);
		FormPanel.add(lblConfirmarEmail);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(10, 140, 133, 50);
		FormPanel.add(lblContrasea);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmarContrasea.setBounds(10, 184, 165, 28);
		FormPanel.add(lblConfirmarContrasea);
		
		textField = new JTextField();
		textField.setBounds(178, 22, 162, 21);
		FormPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(176, 53, 162, 21);
		FormPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(177, 85, 162, 21);
		FormPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(176, 120, 162, 21);
		FormPanel.add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 159, 164, 18);
		FormPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(176, 190, 164, 18);
		FormPanel.add(passwordField_1);

	}

}
