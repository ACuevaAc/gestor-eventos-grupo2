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

public class GestorMesasView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JButton btnRegistrarModificar;
	private JButton btnAtras;
	private JComboBox cBgente;
	/**
	 * Create the frame.
	 */
	public GestorMesasView() {
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
		panelBotones.add(btnRegistrarModificar);

		btnAtras = new JButton("Back");
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
	public JTextField getTxtID() {
		return txtID;
	}
	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	public JButton getBtnRegistrarModificar() {
		return btnRegistrarModificar;
	}
	public void setBtnRegistrarModificar(JButton btnRegistrarModificar) {
		this.btnRegistrarModificar = btnRegistrarModificar;
	}
	public JButton getBtnAtras() {
		return btnAtras;
	}
	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}
	public JComboBox getcBgente() {
		return cBgente;
	}
	public void setcBgente(JComboBox cBgente) {
		this.cBgente = cBgente;
	}

}
