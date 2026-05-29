package com.gestor.view.admin;

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

/**
 * @class signupadminView
 * @description Administrative registration window interface acting as the account creation wizard cockpit.
 * Handles demographic information fields validation matrices, captures credential input constraints,
 * manages responsive borders configurations, and exposes operational transaction hooks for administrative provisioning.
 */
public class signupadminView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTextField txtEmail;
	private JTextField txtConfirmEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JButton btnCreate;
	private JButton btnBack;

	private final Color COLOR_FONDO = new Color(24, 38, 64);
	private final Color COLOR_TEXTO = new Color(226, 232, 240);
	private final Color COLOR_PRINCIPAL = new Color(243, 244, 246);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);

	private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
	private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font FUENTE_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
	private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

	/**
     * @constructor
     * @description Constructs the administrative sign-up viewport container, mapping display boundary thresholds,
     * mounting structural input field managers, and binding action listeners to execute profile provisioning loops.
     */
	public signupadminView() {
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
		txtNombre.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtNombre);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(FUENTE_LABEL);
		lblEdad.setForeground(COLOR_TEXTO);
		formPanel.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setToolTipText("Debe ser mayor de edad");
		txtEdad.setFont(FUENTE_INPUT);
		txtEdad.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtEdad);

		JLabel lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(FUENTE_LABEL);
		lblEmail.setForeground(COLOR_TEXTO);
		formPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(FUENTE_INPUT);
		txtEmail.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtEmail);

		JLabel lblConfirmEmail = new JLabel("Confirmar Correo Electrónico");
		lblConfirmEmail.setFont(FUENTE_LABEL);
		lblConfirmEmail.setForeground(COLOR_TEXTO);
		formPanel.add(lblConfirmEmail);

		txtConfirmEmail = new JTextField();
		txtConfirmEmail.setFont(FUENTE_INPUT);
		txtConfirmEmail.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtConfirmEmail);

		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(FUENTE_LABEL);
		lblPass.setForeground(COLOR_TEXTO);
		formPanel.add(lblPass);

		txtPassword = new JPasswordField();
		txtPassword.setFont(FUENTE_INPUT);
		txtPassword.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtPassword);

		JLabel lblConfirmPass = new JLabel("Confirmar Contraseña");
		lblConfirmPass.setFont(FUENTE_LABEL);
		lblConfirmPass.setForeground(COLOR_TEXTO);
		formPanel.add(lblConfirmPass);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(FUENTE_INPUT);
		txtConfirmPassword.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txtConfirmPassword);

		contentPane.add(formPanel, BorderLayout.CENTER);

		JPanel actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		actionPanel.setOpaque(false);

		btnCreate = new JButton("Crear Cuenta");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setFont(FUENTE_BOTON);
		btnCreate.setForeground(COLOR_SECUNDARIO);
		btnCreate.setBackground(COLOR_PRINCIPAL);
		btnCreate.setFocusPainted(false);
		btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCreate.setPreferredSize(new Dimension(0, 45));
		btnCreate.setBorder(BorderFactory.createEmptyBorder());
		actionPanel.add(btnCreate);

		btnBack = new JButton("Volver al menú");
		btnBack.setFont(FUENTE_LABEL);
		btnBack.setForeground(COLOR_SECUNDARIO);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		actionPanel.add(btnBack);

		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}
	/**
     * @method getTxtNombre
     * @description Exposes the text tracking controller capturing the user's descriptive nomenclature string.
     * @returns {JTextField} Core alphanumeric entry component context.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @method setTxtNombre
     * @description Injects an operational interface string text input handling structural profile identities.
     * @param {JTextField} txtNombre - Target nomenclature text entry element reference.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @method getTxtEdad
     * @description Exposes the data tracker monitoring user chronological demographic parameters.
     * @returns {JTextField} Numeric tracking entry field.
     */
    public JTextField getTxtEdad() {
        return txtEdad;
    }

    /**
     * @method setTxtEdad
     * @description Binds a text tracking field module designated to capture administrative age boundaries metrics.
     * @param {JTextField} txtEdad - Target quantitative entry container.
     */
    public void setTxtEdad(JTextField txtEdad) {
        this.txtEdad = txtEdad;
    }

    /**
     * @method getTxtEmail
     * @description Exposes the primary data tracker field capturing account administration unique messaging channels.
     * @returns {JTextField} Unique coordinate locator input entry element.
     */
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * @method setTxtEmail
     * @description Binds an input text structure responsible for processing target unique communication parameters.
     * @param {JTextField} txtEmail - Target administrative electronic tracking contact.
     */
    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @method getTxtConfirmEmail
     * @description Exposes the duplicate checking string module validating structural coordinate inputs.
     * @returns {JTextField} Structural validation text checker interface component.
     */
    public JTextField getTxtConfirmEmail() {
        return txtConfirmEmail;
    }

    /**
     * @method setTxtConfirmEmail
     * @description Registers a specific text entry field to operate as an administrative verification checkpoint anchor.
     * @param {JTextField} txtConfirmEmail - Target duplicate coordinate confirmation field.
     */
    public void setTxtConfirmEmail(JTextField txtConfirmEmail) {
        this.txtConfirmEmail = txtConfirmEmail;
    }

    /**
     * @method getTxtPassword
     * @description Exposes the masked structural component masking credential verification tokens.
     * @returns {JPasswordField} Cryptographic entry security input resource tracking component.
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * @method setTxtPassword
     * @description Configures or installs a secure credential masking entry field handling pass tokens.
     * @param {JPasswordField} txtPassword - Target secure identification string token field.
     */
    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * @method getTxtConfirmPassword
     * @description Exposes the duplicate verification secure container checking structural pass credentials keys stability.
     * @returns {JPasswordField} Cryptographic token confirmation tracker reference.
     */
    public JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    /**
     * @method setTxtConfirmPassword
     * @description Configures an isolated masked field deployment designed to confirm identity credential tokens layout maps.
     * @param {JPasswordField} txtConfirmPassword - Target password double check field asset.
     */
    public void setTxtConfirmPassword(JPasswordField txtConfirmPassword) {
        this.txtConfirmPassword = txtConfirmPassword;
    }

    /**
     * @method getBtnCreate
     * @description Resolves the primary execution workflow node tasked with dispatching profile creation validations and structural insertion loops.
     * @returns {JButton} The administrative signup action button instance.
     */
    public JButton getBtnCreate() {
        return btnCreate;
    }

    /**
     * @method setBtnCreate
     * @description Replaces or wires the explicit creation workflow interaction button engine.
     * @param {JButton} btnCreate - Target account submission trigger management node.
     */
    public void setBtnCreate(JButton btnCreate) {
        this.btnCreate = btnCreate;
    }

    /**
     * @method getBtnBack
     * @description Returns the navigation menu rollback action button deployed to pop active views from the display frame stacks.
     * @returns {JButton} Visual cancellation workflow navigation element.
     */
    public JButton getBtnBack() {
        return btnBack;
    }

    /**
     * @method setBtnBack
     * @description Hooks a custom navigation callback node onto the application's interface panel matrix layout boundaries.
     * @param {JButton} btnBack - Target frame dismiss view resource trigger.
     */
    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

}
