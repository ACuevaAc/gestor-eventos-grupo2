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

/**
 * @class ViewSummaryBook
 * @description Administrative history view viewport acting as a centralized logging console dashboard.
 * Houses structural tabular layouts presenting global venue booking collections, coordinates relational parameters
 * mapping customer identities to active physical layout assets, and provides navigation workflows to revert context states.
 */
public class ViewSummaryBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnBack;
	
	/**
     * @constructor
     * @description Constructs the logging registry frame container, registering structural frame bounds,
     * mounting tabular schema vectors to parse booking payloads, and configuring positional view containers.
     */
	public ViewSummaryBook() {
		super("Pedido del usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setLocationRelativeTo(null);
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

	/**
     * @method getModelo
     * @description Exposes the underlying operational layout schema tracking dynamic row adjustments.
     * @returns {DefaultTableModel} Data collection management grid array.
     */
	public DefaultTableModel getModelo() {
		return model;
	}

	/**
     * @method getTabla
     * @description Exposes the target interface table container deployed to present active booking entities.
     * @returns {JTable} Structured presentation layer layout component references.
     */
	public JTable getTabla() {
		return table;
	}

	/**
     * @method getBack
     * @description Resolves the explicit navigation callback node tasked with unwinding history layers from viewports.
     * @returns {JButton} Programmatic frame rollback interface node.
     */
	public JButton getBack() {
		return btnBack;
	}

}
