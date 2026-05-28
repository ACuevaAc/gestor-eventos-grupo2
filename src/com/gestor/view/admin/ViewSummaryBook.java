package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewSummaryBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSummaryBook frame = new ViewSummaryBook();
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
	public ViewSummaryBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel pnTable=new JPanel();
		String [] columnas= {"ID Reserva","Mesa","Cliente","Fecha"};
		model=new DefaultTableModel(columnas,0);
		table=new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		pnTable.add(sp);
		contentPane.add(pnTable,BorderLayout.CENTER);
		JPanel pnBoton=new JPanel();
		btnBack=new JButton("Volver");
		pnBoton.add(btnBack);
		contentPane.add(pnBoton,BorderLayout.SOUTH);

	}
	public DefaultTableModel getModelo() {
		return model;
	}
	public JTable getTabla() {
		return table;
	}
	public JButton getBack() {
		return btnBack;
	}

}
