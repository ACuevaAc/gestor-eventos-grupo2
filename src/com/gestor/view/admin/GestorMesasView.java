package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @class GestorMesasView
 * @description Administrative window user interface configuration panel acting as a seating property manager.
 * Handles structural inputs for creating and altering layout asset models, managing identity indices,
 * text title fields, and selective volumetric headcount constraints via combo box boundaries.
 */
public class GestorMesasView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JButton btnRegistrarModificar;
	private JButton btnAtras;
	private JComboBox cBgente;

	/**
     * @constructor
     * @description Constructs the asset deployment form canvas, sets window positioning constraints, 
     * registers structural layout managers, mounts validation fields, and populates discrete capacity selectors.
     */
	public GestorMesasView() {
		super("Crear Mesa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		btnRegistrarModificar = new JButton("Register");
		btnRegistrarModificar.setToolTipText("Añadir Mesa");
		panelBotones.add(btnRegistrarModificar);

		btnAtras = new JButton("Back");
		btnAtras.setToolTipText("Volver");
		panelBotones.add(btnAtras);

		JPanel panelMesa = new JPanel();
		contentPane.add(panelMesa, BorderLayout.CENTER);
		panelMesa.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel lblId = new JLabel("Id");
		panelMesa.add(lblId);

		txtID = new JTextField();
		txtID.setEditable(false);
		panelMesa.add(txtID);
		txtID.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		panelMesa.add(lblNombre);

		txtNombre = new JTextField();
		panelMesa.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblGente = new JLabel("Máximo de comensales");
		panelMesa.add(lblGente);

		cBgente = new JComboBox<>();
		for (int i = 1; i <= 10; i++) {
			cBgente.addItem(i);
		}
		panelMesa.add(cBgente);

	}

	/**
     * @method getTxtID
     * @description Exposes the unique identity sequence tracker presentation element.
     * @returns {JTextField} Read-only unique identifier tracking field.
     */
	public JTextField getTxtID() {
		return txtID;
	}

	/**
     * @method setTxtID
     * @description Injects an operational layout text object mapping identity indices.
     * @param {JTextField} txtID - Target unique identifier presentation property.
     */
	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}

	/**
     * @method getTxtNombre
     * @description Exposes the input field monitoring the user-assigned title string for the asset.
     * @returns {JTextField} Core visual text entry tracker for asset naming.
     */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
     * @method setTxtNombre
     * @description Overwrites or replaces the descriptive layout component handling resource nomenclature.
     * @param {JTextField} txtNombre - Target text interface configuration reference.
     */
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	/**
     * @method getBtnRegistrarModificar
     * @description Resolves the dual-state interaction transaction hook dispatched to commit creation or alteration mutations.
     * @returns {JButton} The primary persistence workflow trigger button.
     */
	public JButton getBtnRegistrarModificar() {
		return btnRegistrarModificar;
	}

	/**
     * @method setBtnRegistrarModificar
     * @description Binds the explicit control trigger implementation handling systemic state confirmations.
     * @param {JButton} btnRegistrarModificar - Target execution submission action component.
     */
	public void setBtnRegistrarModificar(JButton btnRegistrarModificar) {
		this.btnRegistrarModificar = btnRegistrarModificar;
	}

	/**
     * @method getBtnAtras
     * @description Returns the navigation fallback workflow button deployed to unwind window views.
     * @returns {JButton} Programmatic frame rollback action anchor.
     */
	public JButton getBtnAtras() {
		return btnAtras;
	}

	/**
     * @method setBtnAtras
     * @description Assigns the specific layout node responsible for managing back-step UI interactions.
     * @param {JButton} btnAtras - Target navigational cancellation trigger.
     */
	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	/**
     * @method getcBgente
     * @description Exposes the selection matrix controlling quantitative integer thresholds for guest limits.
     * @returns {JComboBox} The dropdown multi-option constraint manager element.
     */
	public JComboBox getcBgente() {
		return cBgente;
	}

	/**
     * @method setcBgente
     * @description Replaces the selective dropdown structural interface mapping asset volume limits.
     * @param {JComboBox} cBgente - Target capacity configuration selection box.
     */
	public void setcBgente(JComboBox cBgente) {
		this.cBgente = cBgente;
	}

}
