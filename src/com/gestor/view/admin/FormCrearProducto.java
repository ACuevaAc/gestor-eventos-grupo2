package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FormCrearProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JButton btnBuscarImagen;
	private File imagenSeleccionada;
	private JLabel lblRutaImagen;
	private ImageIcon iconoOriginal ;
	private Image imagenEscalada;
	private JButton btnCreate;
	private JButton btnBack;
	
	/**
	 * Create the frame.
	 */
	public FormCrearProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		btnCreate = new JButton("Create");
		panelBotones.add(btnCreate);

		btnBack = new JButton("Back");
		panelBotones.add(btnBack);

		JPanel panelForm = new JPanel();
		contentPane.add(panelForm, BorderLayout.CENTER);

		panelForm.setLayout(new GridLayout(4, 2, 5, 5));
		JLabel lblNombre = new JLabel("Nombre");
		panelForm.add(lblNombre);

		txtNombre = new JTextField();
		panelForm.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio");
		panelForm.add(lblPrecio);

		txtPrecio = new JTextField();
		panelForm.add(txtPrecio);
		txtPrecio.setColumns(10);

		JLabel lblImagen = new JLabel("Imagen");
		panelForm.add(lblImagen);

		btnBuscarImagen = new JButton("Seleccionar archivo...");
		panelForm.add(btnBuscarImagen);

		JLabel lblEspacioVacio = new JLabel("Vista previa:");
		panelForm.add(lblEspacioVacio);

		lblRutaImagen = new JLabel("Sin foto");
		panelForm.add(lblRutaImagen);

	}

	
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JButton getBtnBuscarImagen() {
		return btnBuscarImagen;
	}

	public void setBtnBuscarImagen(JButton btnBuscarImagen) {
		this.btnBuscarImagen = btnBuscarImagen;
	}

	public File getImagenSeleccionada() {
		return imagenSeleccionada;
	}

	public void setImagenSeleccionada(File imagenSeleccionada) {
		this.imagenSeleccionada = imagenSeleccionada;
	}

	public ImageIcon getIconoOriginal() {
		return iconoOriginal;
	}

	public void setIconoOriginal(ImageIcon iconoOriginal) {
		this.iconoOriginal = iconoOriginal;
	}

	public Image getImagenEscalada() {
		return imagenEscalada;
	}

	public void setImagenEscalada(Image imagenEscalada) {
		this.imagenEscalada = imagenEscalada;
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
		FormCrearProducto fp = new FormCrearProducto();
		fp.setVisible(true);
	}


	public JLabel getLblRutaImagen() {
		return lblRutaImagen;
	}


	public void setLblRutaImagen(JLabel lblRutaImagen) {
		this.lblRutaImagen = lblRutaImagen;
	}
	
}
