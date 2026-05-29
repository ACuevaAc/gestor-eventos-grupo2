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

/**
 * @class SignupView
 * @description User enrollment configuration window acting as a secure profile enrollment canvas interface.
 * Implements granular form input structures dedicated to parsing identity metrics, provides compound padding borders 
 * for text input control components, isolates secure credential validation layers, and provides transactional 
 * navigation access points to commit registration payloads or rollback view contexts.
 */
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

	/**
     * @constructor
     * @description Constructs the registration canvas dashboard framework, initializing baseline view bounds, 
     * building structural layout containers to organize identity inputs, applying look parameters, 
     * and mounting interactive control action listeners.
     */
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

	/**
     * @method getTxtName
     * @description Exposes the visual input entry field responsible for gathering consumer legal names metadata.
     * @returns {JTextField} Graphical structural interface tracking alphanumeric names input.
     */
	public JTextField getTxtName() {
		return txtName;
	}

	/**
     * @method setTxtName
     * @description Injects an interactive user text entry handler mapping descriptive configuration tokens.
     * @param {JTextField} txtName - Target input descriptor processing asset component.
     */
	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	/**
     * @method getTxtAge
     * @description Exposes the text string input field allocated to capture user generation lifecycle variables.
     * @returns {JTextField} Graphical text field parsing age metrics integers.
     */
	public JTextField getTxtAge() {
		return txtAge;
	}

	/**
     * @method setTxtAge
     * @description Binds the target structural context component managing numerical metric validation boundaries.
     * @param {JTextField} txtAge - Target input element processing validation constants.
     */
	public void setTxtAge(JTextField txtAge) {
		this.txtAge = txtAge;
	}

	/**
     * @method getTxtEmail
     * @description Exposes the baseline contact channel routing descriptor tracking user electronic addresses.
     * @returns {JTextField} Alphanumeric identifier field element model.
     */
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	/**
     * @method setTxtEmail
     * @description Assigns a specific data capture layout engine tracking communications lookup strings.
     * @param {JTextField} txtEmail - Target user validation channel tracker resource.
     */
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	/**
     * @method getTxtConfirmEmail
     * @description Exposes the redundancy confirmation field configured to ensure matching communication metrics logs.
     * @returns {JTextField} Interface component capturing authentication entry clones.
     */
	public JTextField getTxtConfirmEmail() {
		return txtConfirmEmail;
	}

	/**
     * @method setTxtConfirmEmail
     * @description Configures an isolated validation entry tracker verifying user account routing parity.
     * @param {JTextField} txtConfirmEmail - Target duplicate communication verification canvas component.
     */
	public void setTxtConfirmEmail(JTextField txtConfirmEmail) {
		this.txtConfirmEmail = txtConfirmEmail;
	}

	/**
     * @method getTxtPassword
     * @description Exposes the secure credential storage string entry component processing input encryption tokens under mask properties.
     * @returns {JPasswordField} Structural verification key field reference.
     */
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	/**
     * @method setTxtPassword
     * @description Sets the secure signature generation text wrapper tracking validation keys parameters.
     * @param {JPasswordField} txtPassword - Target signature registration element structure.
     */
	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	/**
     * @method getTxtConfirmPassword
     * @description Exposes the defensive credential duplication field handling integrity string comparisons.
     * @returns {JPasswordField} Safety parity checker input field references.
     */
	public JPasswordField getTxtConfirmPassword() {
		return txtConfirmPassword;
	}

	/**
     * @method setTxtConfirmPassword
     * @description Configures a secondary structural validation component verifying access security parity.
     * @param {JPasswordField} txtConfirmPassword - Target redundant passcode verification container.
     */
	public void setTxtConfirmPassword(JPasswordField txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
	}

	/**
     * @method getBtnCreate
     * @description Resolves the baseline execution checkpoint trigger deploying validation pipelines and creating user entries.
     * @returns {JButton} Final transaction persistence submission element node.
     */
	public JButton getBtnCreate() {
		return btnCreate;
	}

	/**
     * @method setBtnCreate
     * @description Links the explicit profile execution control button component managing registration commits.
     * @param {JButton} btnCreate - Target profile pipeline verification trigger asset.
     */
	public void setBtnCreate(JButton btnCreate) {
		this.btnCreate = btnCreate;
	}

	/**
     * @method getBtnBack
     * @description Exposes the retrospective wizard navigation button deployed to exit enrollment sessions and rollback view hierarchies.
     * @returns {JButton} Programmatic structural layout fallback action anchor.
     */
	public JButton getBtnBack() {
		return btnBack;
	}

	/**
     * @method setBtnBack
     * @description Mounts the specific navigational fallback element tasked with reversing window contexts.
     * @param {JButton} btnBack - Target workflow reversion command button model.
     */
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