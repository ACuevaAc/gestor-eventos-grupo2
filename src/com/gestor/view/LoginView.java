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
import javax.swing.border.EmptyBorder;

/**
 * @class LoginView
 * @description Authentication gateway interface canvas serving as the initial security checkpoint viewport.
 * Houses structured input fields capturing user identity credentials, provides visual text masking properties 
 * for security assets protection, initializes custom interface style lookups, and exposes structural handles 
 * to wire controller actions and workflow redirections.
 */
public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField passwordField;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Color textColor = new Color(43, 43, 43);
	private final Color mainColor = new Color(230, 95, 43);
	private final Color secondaryColor = new Color(108, 117, 125);

	private final Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
	private final Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

	private JLabel lblWelcome;
	private JPanel headerPanel;
	private JLabel lblSubtitle;
	private JPanel formContainer;
	private JPanel formPanel;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JPanel actionPanel;
	private JButton btnLogIn;
	private JButton btnSignUp;

	/**
     * @constructor
     * @description Constructs the authentication frame window, enforcing window placement profiles, 
     * generating compound padded borders around credential fields, and establishing user action entry nodes.
     */
	public LoginView() {
		setTitle("Gestor de Restaurante - Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(450, 500);
		setMinimumSize(new Dimension(500, 550));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));

		headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		headerPanel.setOpaque(false);

		lblWelcome = new JLabel("¡Bienvenidos!");
		lblWelcome.setFont(titleFont);
		lblWelcome.setForeground(mainColor);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblWelcome);

		lblSubtitle = new JLabel("Introduce tus credenciales para acceder");
		lblSubtitle.setFont(labelFont);
		lblSubtitle.setForeground(secondaryColor);
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(lblSubtitle);

		contentPane.add(headerPanel, BorderLayout.NORTH);

		formContainer = new JPanel(new BorderLayout());
		formContainer.setOpaque(false);

		formPanel = new JPanel(new GridLayout(4, 1, 0, 5));
		formPanel.setOpaque(false);

		lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(labelFont);
		lblEmail.setForeground(textColor);
		formPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(inputFont);
		txtEmail.setPreferredSize(new Dimension(0, 35));
		txtEmail.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(txtEmail);

		lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(labelFont);
		lblPassword.setForeground(textColor);
		formPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(inputFont);
		passwordField.setPreferredSize(new Dimension(0, 35));
		passwordField.setBorder(
			BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);
		formPanel.add(passwordField);

		formContainer.add(formPanel, BorderLayout.NORTH);
		contentPane.add(formContainer, BorderLayout.CENTER);

		actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		actionPanel.setOpaque(false);

		btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.setFont(buttonFont);
		btnLogIn.setForeground(Color.white);
		btnLogIn.setBackground(mainColor);
		btnLogIn.setFocusPainted(false);
		btnLogIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogIn.setPreferredSize(new Dimension(0, 45));
		btnLogIn.setBorder(BorderFactory.createEmptyBorder());

		actionPanel.add(btnLogIn);
		btnSignUp = new JButton("Registrar Nuevo Usuario");
		btnSignUp.setFont(labelFont);
		btnSignUp.setForeground(mainColor);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setFocusPainted(false);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		actionPanel.add(btnSignUp);

		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}

	/**
     * @method getTxtEmail
     * @description Exposes the text input field responsible for collecting account identification tracking strings.
     * @returns {JTextField} Alphanumeric communication handle locator structure.
     */
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	/**
     * @method setTxtEmail
     * @description Binds an input data tracker handling user login nomenclature coordinates.
     * @param {JTextField} txtEmail - Target electronic messaging field resource.
     */
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	/**
     * @method getPasswordField
     * @description Exposes the text tracking container processing entry verification tokens under mask properties.
     * @returns {JPasswordField} Secure identity string component reference.
     */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
     * @method setPasswordField
     * @description Registers an isolated masked validation field asset tracking credential pass strings.
     * @param {JPasswordField} passwordField - Target masked interaction element framework.
     */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
     * @method getBtnLogIn
     * @description Resolves the primary execution trigger deploying confirmation loops onto input authorization credentials.
     * @returns {JButton} Session setup transaction action button.
     */
	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	/**
     * @method setBtnLogIn
     * @description Installs the explicit visual control button engine managing execution login workflows.
     * @param {JButton} btnLogIn - Target action processing trigger component.
     */
	public void setBtnLogIn(JButton btnLogIn) {
		this.btnLogIn = btnLogIn;
	}

	/**
     * @method getBtnSignUp
     * @description Returns the wizard navigation redirection link deployed to transition view contexts onto profile creation screens.
     * @returns {JButton} Interface redirection navigation button.
     */
	public JButton getBtnSignUp() {
		return btnSignUp;
	}

	/**
     * @method setBtnSignUp
     * @description Registers a specific navigation action element responsible for launching structural enrollment flows.
     * @param {JButton} btnSignUp - Target frame swap interaction node asset.
     */
	public void setBtnSignUp(JButton btnSignUp) {
		this.btnSignUp = btnSignUp;
	}

	public static void main(String[] args) {
		try {
			com.formdev.flatlaf.FlatLightLaf.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginView().setVisible(true);
			}
		});
	}
}