package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ListUsersView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tabla;
	private JButton btnBack,btnDelete;
	private JTextField txSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListUsersView frame = new ListUsersView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListUsersView() {
		super("Usuarios Registrados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JPanel pnTabla=new JPanel();
		String[] columnas= {"Correo","Contraseña","Rol"};
		modelo=new DefaultTableModel(columnas,0);
		tabla=new JTable(modelo);
		JScrollPane sp=new JScrollPane(tabla);
		pnTabla.add(sp);
		JPanel pnBotones=new JPanel();
		btnBack=new JButton("Volver");
		btnDelete=new JButton("Borrar");
		pnBotones.add(btnBack);
		pnBotones.add(btnDelete);
		JPanel pnSearch=new JPanel();
		txSearch=new JTextField(20);
		txSearch.setToolTipText("Escribe un correo para buscar");
		pnSearch.add(txSearch);
		contentPane.add(pnSearch,BorderLayout.NORTH);
		
		
		contentPane.add(pnTabla,BorderLayout.CENTER);
		contentPane.add(pnBotones,BorderLayout.SOUTH);
		

	}
	public JTextField getSearch() {
		return txSearch;
	}
	public JTable getTabla() {
		return tabla;
	}
	public DefaultTableModel getModelo() {
		return modelo;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}

}
