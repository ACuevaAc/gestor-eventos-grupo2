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

/**
 * @class ListUsersView
 * @description Administrative dashboard window component providing view structures for profile registries.
 * Houses structural matrix grid components tracking system accounts data, registers alphanumeric filtering fields 
 * for targeted lookup strategies, and exposes execution handles for cascading identity erasures.
 */
public class ListUsersView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tabla;
	private JButton btnBack,btnDelete;
	private JTextField txSearch;

	/**
     * @method main
     * @static
     * @description Dispatches the visualization frame execution profile safely onto the structural 
     * Abstract Window Toolkit (AWT) event dispatching thread pool layer.
     * @param {String[]} args - Runtime startup command argument array inputs.
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
     * @constructor
     * @description Constructs the user directory viewer canvas, establishing frame size coordinates, 
     * initializing underlying grid architectures matching metadata bounds, and mounting action controllers.
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

	/**
     * @method getSearch
     * @description Exposes the unique validation text tracker capturing real-time identity matching lookup signatures.
     * @returns {JTextField} Alphanumeric search target field instance reference.
     */
	public JTextField getSearch() {
		return txSearch;
	}

	/**
     * @method getTabla
     * @description Exposes the graphical grid layout container rendering tabulated user profiles.
     * @returns {JTable} Visual grid presenter instance component.
     */
	public JTable getTabla() {
		return tabla;
	}

	/**
     * @method getModelo
     * @description Exposes the structural data mapping schema manager driving active row indices.
     * @returns {DefaultTableModel} Underlying model collection layout data array.
     */
	public DefaultTableModel getModelo() {
		return modelo;
	}

	/**
     * @method getBtnBack
     * @description Returns the navigation menu fallback button deployed to pop active frame view layers.
     * @returns {JButton} The visual cancel interface interaction node.
     */
	public JButton getBtnBack() {
		return btnBack;
	}

	/**
     * @method getBtnDelete
     * @description Resolves the user execution trigger tasked with dispatching persistent account purge operations.
     * @returns {JButton} The administrative deletion workflow button.
     */
	public JButton getBtnDelete() {
		return btnDelete;
	}

}
