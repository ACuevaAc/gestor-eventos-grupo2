package com.gestor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

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

public class SignupView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JTextField txtConfirmEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JButton btnCreate;
	private JButton btnBack;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Color textColor = new Color(43, 43, 43);
	private final Color mainColor = new Color(230, 95, 43);
	private final Color secondaryColor = new Color(108, 117, 125);

	private final Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
	private final Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

	public SignupView() {
		setTitle("Registro - Gestor de Restaurante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);
		setMinimumSize(new Dimension(550, 650));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));

		JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		headerPanel.setOpaque(false);

		JLabel lblTitle = new JLabel("Crear Cuenta");
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(mainColor);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblTitle);

		JLabel lblSubtitle = new JLabel("Te damos la bienvenida a nuestra nueva experiencia gastronómica");
		lblSubtitle.setFont(labelFont);
		lblSubtitle.setForeground(secondaryColor);
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblSubtitle);

		contentPane.add(headerPanel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridLayout(12, 1, 0, 5));
		formPanel.setOpaque(false);

		JLabel lblName = new JLabel("Nombre Completo");
		lblName.setFont(labelFont);
		lblName.setForeground(textColor);
		formPanel.add(lblName);

		txtName = new JTextField();
		txtName.setFont(inputFont);
		txtName.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtName);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(labelFont);
		lblEdad.setForeground(textColor);
		formPanel.add(lblEdad);

		txtAge = new JTextField();
		txtAge.setFont(inputFont);
		txtAge.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtAge);

		JLabel lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(labelFont);
		lblEmail.setForeground(textColor);
		formPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(inputFont);
		txtEmail.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtEmail);

		JLabel lblConfirmEmail = new JLabel("Confirmar Correo Electrónico");
		lblConfirmEmail.setFont(labelFont);
		lblConfirmEmail.setForeground(textColor);
		formPanel.add(lblConfirmEmail);

		txtConfirmEmail = new JTextField();
		txtConfirmEmail.setFont(inputFont);
		txtConfirmEmail.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtConfirmEmail);

		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(labelFont);
		lblPass.setForeground(textColor);
		formPanel.add(lblPass);

		txtPassword = new JPasswordField();
		txtPassword.setFont(inputFont);
		txtPassword.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtPassword);

		JLabel lblConfirmPass = new JLabel("Confirmar Contraseña");
		lblConfirmPass.setFont(labelFont);
		lblConfirmPass.setForeground(textColor);
		formPanel.add(lblConfirmPass);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(inputFont);
		txtConfirmPassword.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtConfirmPassword);

		contentPane.add(formPanel, BorderLayout.CENTER);

		JPanel actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		actionPanel.setOpaque(false);

		btnCreate = new JButton("Crear Cuenta");
		btnCreate.setFont(buttonFont);
		btnCreate.setForeground(secondaryColor);
		btnCreate.setBackground(mainColor);
		btnCreate.setFocusPainted(false);
		btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCreate.setPreferredSize(new Dimension(0, 45));
		btnCreate.setBorder(BorderFactory.createEmptyBorder());
		actionPanel.add(btnCreate);

		btnBack = new JButton("Volver al Login");
		btnBack.setFont(labelFont);
		btnBack.setForeground(secondaryColor);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		actionPanel.add(btnBack);

		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtAge() {
		return txtAge;
	}

	public void setTxtAge(JTextField txtAge) {
		this.txtAge = txtAge;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtConfirmEmail() {
		return txtConfirmEmail;
	}

	public void setTxtConfirmEmail(JTextField txtConfirmEmail) {
		this.txtConfirmEmail = txtConfirmEmail;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JPasswordField getTxtConfirmPassword() {
		return txtConfirmPassword;
	}

	public void setTxtConfirmPassword(JPasswordField txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}

	public void setBtnCreate(JButton btnCreate) {
		this.btnCreate = btnCreate;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
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
					SignupView frame = new SignupView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}