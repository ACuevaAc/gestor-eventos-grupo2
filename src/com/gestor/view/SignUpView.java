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

public class SignUpView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtEmail;
	private JTextField txtConfirmEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	
	private final Color COLOR_FONDO = new Color(248, 249, 250);       
	private final Color COLOR_TEXTO = new Color(43, 43, 43);          
	private final Color COLOR_PRINCIPAL = new Color(230, 95, 43);     
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);  
	
	private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
	private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font FUENTE_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

	public SignUpView() {
		setTitle("Registro - Gestor de Restaurante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);
		setMinimumSize(new Dimension(550, 650));
		setLocationRelativeTo(null); 
		
		contentPane = new JPanel();
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50)); 
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20)); 
		
		JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		headerPanel.setOpaque(false);
		
		JLabel lblTitle = new JLabel("Crear Cuenta");
		lblTitle.setFont(FUENTE_TITULO);
		lblTitle.setForeground(COLOR_PRINCIPAL);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("Te damos la bienvenida a nuestra nueva experiencia gastronómica");
		lblSubtitle.setFont(FUENTE_LABEL);
		lblSubtitle.setForeground(COLOR_SECUNDARIO);
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblSubtitle);
		
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JPanel formPanel = new JPanel(new GridLayout(12, 1, 0, 5));
		formPanel.setOpaque(false);
		
		JLabel lblNombre = new JLabel("Nombre Completo");
		lblNombre.setFont(FUENTE_LABEL);
		lblNombre.setForeground(COLOR_TEXTO);
		formPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(FUENTE_INPUT);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtNombre);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(FUENTE_LABEL);
		lblEdad.setForeground(COLOR_TEXTO);
		formPanel.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setFont(FUENTE_INPUT);
		txtEdad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtEdad);
		
		JLabel lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(FUENTE_LABEL);
		lblEmail.setForeground(COLOR_TEXTO);
		formPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(FUENTE_INPUT);
		txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtEmail);
		
		JLabel lblConfirmEmail = new JLabel("Confirmar Correo Electrónico");
		lblConfirmEmail.setFont(FUENTE_LABEL);
		lblConfirmEmail.setForeground(COLOR_TEXTO);
		formPanel.add(lblConfirmEmail);
		
		txtConfirmEmail = new JTextField();
		txtConfirmEmail.setFont(FUENTE_INPUT);
		txtConfirmEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtConfirmEmail);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(FUENTE_LABEL);
		lblPass.setForeground(COLOR_TEXTO);
		formPanel.add(lblPass);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(FUENTE_INPUT);
		txtPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtPassword);
		
		JLabel lblConfirmPass = new JLabel("Confirmar Contraseña");
		lblConfirmPass.setFont(FUENTE_LABEL);
		lblConfirmPass.setForeground(COLOR_TEXTO);
		formPanel.add(lblConfirmPass);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(FUENTE_INPUT);
		txtConfirmPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtConfirmPassword);
		
		contentPane.add(formPanel, BorderLayout.CENTER);
		
		JPanel actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		actionPanel.setOpaque(false);
		
		JButton btnCreate = new JButton("Crear Cuenta");
		btnCreate.setFont(FUENTE_BOTON);
		btnCreate.setForeground(COLOR_SECUNDARIO);
		btnCreate.setBackground(COLOR_PRINCIPAL);
		btnCreate.setFocusPainted(false);
		btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCreate.setPreferredSize(new Dimension(0, 45)); 
		btnCreate.setBorder(BorderFactory.createEmptyBorder());
		actionPanel.add(btnCreate);
		
		JButton btnBack = new JButton("Volver al Login");
		btnBack.setFont(FUENTE_LABEL);
		btnBack.setForeground(COLOR_SECUNDARIO);
		btnBack.setContentAreaFilled(false); 
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		actionPanel.add(btnBack);
		
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
					SignUpView frame = new SignUpView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}