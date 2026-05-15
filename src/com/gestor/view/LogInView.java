package com.gestor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LogInView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private final Color COLOR_FONDO = new Color(248, 249, 250);       
	private final Color COLOR_TEXTO = new Color(43, 43, 43);          
	private final Color COLOR_PRINCIPAL = new Color(230, 95, 43);     
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);  
	
	private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
	private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font FUENTE_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

	public LogInView() {
		setTitle("Gestor de Restaurante - Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(450, 500);
		setMinimumSize(new Dimension(500, 550));
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50)); 
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20)); 
		
		JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		headerPanel.setOpaque(false);
		
		JLabel lblWelcome = new JLabel("¡Bienvenidos!");
		lblWelcome.setFont(FUENTE_TITULO);
		lblWelcome.setForeground(COLOR_PRINCIPAL);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblWelcome);
		
		JLabel lblSubtitle = new JLabel("Introduce tus credenciales para acceder");
		lblSubtitle.setFont(FUENTE_LABEL);
		lblSubtitle.setForeground(COLOR_SECUNDARIO);
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblSubtitle);
		
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JPanel formContainer = new JPanel(new BorderLayout());
		formContainer.setOpaque(false);
		
		JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 5));
		formPanel.setOpaque(false);
		
		JLabel lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(FUENTE_LABEL);
		lblEmail.setForeground(COLOR_TEXTO);
		formPanel.add(lblEmail);
		
		textField = new JTextField();
		textField.setFont(FUENTE_INPUT);
		textField.setPreferredSize(new Dimension(0, 35));
		textField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(textField);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(FUENTE_LABEL);
		lblPassword.setForeground(COLOR_TEXTO);
		formPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(FUENTE_INPUT);
		passwordField.setPreferredSize(new Dimension(0, 35));
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(passwordField);
		
		formContainer.add(formPanel, BorderLayout.NORTH);
		contentPane.add(formContainer, BorderLayout.CENTER);
		
		JPanel actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		actionPanel.setOpaque(false);
		
		JButton btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.setFont(FUENTE_BOTON);
		btnLogIn.setForeground(COLOR_PRINCIPAL);
		btnLogIn.setBackground(COLOR_PRINCIPAL);
		btnLogIn.setFocusPainted(false);
		btnLogIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogIn.setPreferredSize(new Dimension(0, 45)); 
		btnLogIn.setBorder(BorderFactory.createEmptyBorder());
		actionPanel.add(btnLogIn);
		
		JButton btnSignUp = new JButton("Registrar Nuevo Usuario");
		btnSignUp.setFont(FUENTE_LABEL);
		btnSignUp.setForeground(COLOR_PRINCIPAL);
		btnSignUp.setContentAreaFilled(false); 
		btnSignUp.setBorderPainted(false);
		btnSignUp.setFocusPainted(false);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		actionPanel.add(btnSignUp);
		
		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInView frame = new LogInView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}