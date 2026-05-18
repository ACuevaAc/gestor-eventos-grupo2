package com.gestor.view.admin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormCrearProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	
	private File imagenSeleccionada;
	private JLabel lblRutaImagen;

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
		
		JButton btnCreate = new JButton("Create");
		panelBotones.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
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
		
		JButton btnBuscarImagen = new JButton("Seleccionar archivo...");
		panelForm.add(btnBuscarImagen);
		
		JLabel lblEspacioVacio = new JLabel("Vista previa:");
		panelForm.add(lblEspacioVacio);
		
		lblRutaImagen = new JLabel("Sin foto");
		panelForm.add(lblRutaImagen);
		
		btnBuscarImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
				selector.setFileFilter(filtro);
				
				int resultado = selector.showOpenDialog(FormCrearProducto.this);
				
				if (resultado == JFileChooser.APPROVE_OPTION) {
					imagenSeleccionada = selector.getSelectedFile();
					
					ImageIcon iconoOriginal = new ImageIcon(imagenSeleccionada.getAbsolutePath());
					Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
					
					lblRutaImagen.setText(""); 
					lblRutaImagen.setIcon(new ImageIcon(imagenEscalada));
				}
			}
		});
	}
	
	public static void main (String[]args) {
		FormCrearProducto fp = new FormCrearProducto();
		fp.setVisible(true);
	}
}
