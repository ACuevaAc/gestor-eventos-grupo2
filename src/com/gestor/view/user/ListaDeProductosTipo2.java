package com.gestor.view.user;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.gestor.model.entity.Producto;

public class ListaDeProductosTipo2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAñadir;
	private JButton btnTerminar;
	private JLabel lblPrecioTotal;
	private JList<Producto> lista;
	private JScrollPane sCP;
	private DefaultListModel<Producto> modelo;
	/**
	 * Create the frame.
	 */
	public ListaDeProductosTipo2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblPrecioTotal = new JLabel("Precio Total:            €");
		panel.add(lblPrecioTotal, BorderLayout.WEST);
		
		btnAñadir = new JButton("Añadir");
		panel.add(btnAñadir, BorderLayout.CENTER);
		
		btnTerminar = new JButton("Terminar");
		panel.add(btnTerminar, BorderLayout.EAST);
		
		//metodo para leer productos AQUI
		modelo = new DefaultListModel<Producto>();
		lista = new JList<Producto>(modelo);
		sCP = new JScrollPane(lista);
		
		JPanel panel_Lista = new JPanel(new BorderLayout());
		panel_Lista.add(sCP,BorderLayout.CENTER);
		contentPane.add(panel_Lista, BorderLayout.CENTER);

	}

}
