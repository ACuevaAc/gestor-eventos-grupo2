package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;

/**
 * @class AdminMainView
 * @description Primary administrative user interface frame container acting as the root cockpit viewport.
 * Controls visual layout distributions for venue floor plans, custom real-time fuzzy text matrix filtering components,
 * responsive structural resizing matrices, and explicit operational action triggers.
 */
public class AdminMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Color actionColor = new Color(51, 65, 85);
	private final Font tableFont = new Font("Segoe UI", Font.BOLD, 18);
	private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
	
	private JButton btnNewProduct;
	private JButton btnNewAdmin;
	private JButton btnCreateTable;
	private JButton btnEmptyAllTables;
	private JButton btnStats;
	private JButton btnListUsers;
	private JButton btnSummary;
	
	private List<JButton> tablesList = new ArrayList<>();
	private JPanel SearchPanel;
	private JTextField FilterTXT;

	/**
     * @constructor
     * @description Constructs the primary administrator container canvas, registers initial structural dimensions, 
     * mounts systemic layout managers, draws physical seating schemes, and wires live listener hooks to track 
     * UI interaction matrices.
     */
	public AdminMainView() {
		super("Administrador de mesas");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 1050);
		setMinimumSize(new Dimension(1050, 1050));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel tablesPanel = new JPanel(new GridLayout(4, 1, 0, 20));
		tablesPanel.setOpaque(false);

		int tablesCount = 1;
		int[] schema = { 3, 2, 3, 2 }; 

		for (int tablesNumber : schema) {
			JPanel file = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
			file.setOpaque(false);
			for (int i = 0; i < tablesNumber; i++) {
				JButton btn = createOvalButton ("Mesa " + tablesCount++);
				tablesList.add(btn);
				file.add(btn);
			}
			tablesPanel.add(file);
		}

		contentPane.add(tablesPanel, BorderLayout.CENTER);
		updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
			}
		});

		// Panel lateral izquierdo
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
		actionPanel.setOpaque(false);
		actionPanel.setBorder(new EmptyBorder(10, 0, 10, 40));

		btnCreateTable = new JButton("Create Table");
		stabilizeAction(btnCreateTable);
		btnCreateTable.setToolTipText("Crea nueva mesa del restaurante");
		
		btnNewAdmin = new JButton("New Admin");
		btnNewAdmin.setToolTipText("Crea un nuevo administrador");
		stabilizeAction(btnNewAdmin);
		
		btnEmptyAllTables = new JButton("Empty All Tables");
		stabilizeAction(btnEmptyAllTables);
		btnEmptyAllTables.setToolTipText("Limpia todas las mesas del restaurante");
		
		btnNewProduct = new JButton("Create New Product");
		btnNewProduct.setToolTipText("Crea un nuevo producto a la carta");
		stabilizeAction(btnNewProduct);
		
		btnStats = new JButton("Stats");
		btnStats.setToolTipText("Comprueba los graficos de las estadisticas");
		stabilizeAction(btnStats);
		
		btnListUsers = new JButton("Users");
		btnListUsers.setToolTipText("Busca/Elimina usuarios ");
		stabilizeAction(btnListUsers);
		
		btnSummary = new JButton("Summary books");
		btnSummary.setToolTipText("Resumen de las reservas");
		stabilizeAction(btnSummary);
	
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnCreateTable);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnNewAdmin);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnEmptyAllTables);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnNewProduct);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnStats);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnListUsers);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnSummary);
		actionPanel.add(Box.createVerticalGlue());
		
		contentPane.add(actionPanel, BorderLayout.WEST);
	
		SearchPanel = new JPanel();
		contentPane.add(SearchPanel, BorderLayout.NORTH);
		SearchPanel.setLayout(new BorderLayout(0, 0));
		
		FilterTXT = new JTextField();
		FilterTXT.setToolTipText("Busca la mesa por su nombre");
		FilterTXT.setColumns(10);
		FilterTXT.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());				
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());								
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());				
			}
		});
		SearchPanel.add(FilterTXT);
	}
	
	/**
     * @method getBtnSummary
     * @description Exposes the control trigger interface used to navigate toward structural reservation logs.
     * @returns {JButton} The target action button instance references.
     */
	public JButton getBtnSummary() {
		return btnSummary;
	}
	
	/**
     * @method getBtnListUsers
     * @description Exposes the control trigger interface tracking active profile search and erasure pathways.
     * @returns {JButton} The target action button instance references.
     */
	public JButton getBtnListUsers() {
		return btnListUsers;
	}

	/**
     * @method getBtnStats
     * @description Exposes the interaction component deployed to initialize analytical business chart rendering hooks.
     * @returns {JButton} The target action button instance references.
     */
	public JButton getBtnStats() {
		return btnStats;
	}

	/**
     * @method setBtnStats
     * @description Externally configures or replaces the structural button reference responsible for analytical stats display.
     * @param {JButton} btnStats - Target trigger element mapping.
     */
	public void setBtnStats(JButton btnStats) {
		this.btnStats = btnStats;
	}

	/**
     * @method getBtnNewProduct
     * @description Exposes the control interface tracking inventory insertion workflow menus.
     * @returns {JButton} The target action button instance references.
     */
	public JButton getBtnNewProduct() {
		return btnNewProduct;
	}

	/**
     * @method setBtnNewProduct
     * @description Binds an explicit interface button implementation to control card catalog expansions.
     * @param {JButton} btnNewProduct - Target interactive system action.
     */
	public void setBtnNewProduct(JButton btnNewProduct) {
		this.btnNewProduct = btnNewProduct;
	}

	/**
     * @method getBtnEmptyAllTables
     * @description Resolves the component trigger responsible for launching total cascade state erasure protocols across floor assets.
     * @returns {JButton} The target action button instance references.
     */
	public JButton getBtnEmptyAllTables() {
		return btnEmptyAllTables;
	}

	/**
     * @method setBtnEmptyAllTables
     * @description Encapsulates systemic access rules to bind transactional state clearing elements.
     * @param {JButton} btnEmptyAllTables - Target reset management button resource.
     */
	public void setBtnEmptyAllTables(JButton btnEmptyAllTables) {
		this.btnEmptyAllTables = btnEmptyAllTables;
	}
	
	/**
     * @method FilterTables
     * @private
     * @description Parses text entries to run immediate matching comparison operations across stored layout titles, 
     * changing visibility boolean states to hide elements that do not match the filter string.
     * @param {String} text - Input query character stream metrics processed for filtering parameters.
     */
	private void FilterTables(String text) {
		String filter = text.toLowerCase().trim();

		for (JButton btn : tablesList) {
			String nombreMesa = btn.getText().toLowerCase();

			if (nombreMesa.contains(filter)) {
				btn.setVisible(true);  
			} else {
				btn.setVisible(false); 
			}
		}

		contentPane.revalidate();
		contentPane.repaint();
	}
	
	/**
     * @method updateTablesSize
     * @private
     * @description Dynamically recalibrates width and height boundaries mapping across visual layout matrices,
     * maintaining proportional constraints during frame changes.
     * @param {int} widthPanel - Active real-time calculated width state value of the parent wrapper container.
     * @param {int} heightPanel - Active real-time calculated height state value of the parent wrapper container.
     */
	private void updateTablesSize (int widthPanel, int heightPanel) {
		int horizontalGap = 30;
		int width = (widthPanel - (horizontalGap * 4)) / 3;
		int height = (heightPanel - (20 * 4)) / 5;

		width = Math.max(120, Math.min(width, 250));
		height = Math.max(70, Math.min(height, 120));

		Dimension newDimension = new Dimension(width, height);

		for (JButton btn : tablesList) {
			btn.setPreferredSize(newDimension);
		}

		contentPane.revalidate();
	}

	/**
     * @method getBtnNewAdmin
     * @description Returns the graphical registration anchor deployed to create administrative accounts records.
     * @returns {JButton} The structural creation action component.
     */
	public JButton getBtnNewAdmin() {
		return btnNewAdmin;
	}
	
	/**
     * @method setBtnNewAdmin
     * @description Installs a target button blueprint wrapper designated to orchestrate admin signup cascades.
     * @param {JButton} btnNewAdmin - Target interactive menu action component.
     */
	public void setBtnNewAdmin(JButton btnNewAdmin) {
		this.btnNewAdmin = btnNewAdmin;
	}

	/**
     * @method getBtnCreateTable
     * @description Exposes the interactive frame component that initiates floor plan inventory expansion wizard configurations.
     * @returns {JButton} The structural infrastructure configuration component.
     */
	public JButton getBtnCreateTable() {
		return btnCreateTable;
	}

	/**
     * @method getBtnCreateTable
     * @description Exposes the interactive frame component that initiates floor plan inventory expansion wizard configurations.
     * @returns {JButton} The structural infrastructure configuration component.
     */
	public void setBtnCreateTable(JButton btnCreateTable) {
		this.btnCreateTable = btnCreateTable;
	}

	/**
     * @method getTablesList
     * @description Exposes the array layout collection holding physical item component references mapped to screen elements.
     * @returns {List<JButton>} Dynamic structural array cataloging visible interface interaction buttons.
     */
	public List<JButton> getTablesList() {
		return tablesList;
	}

	/**
     * @method setTablesList
     * @description Overwrites the structural tracking layout list with an external button tracking collection mapping framework.
     * @param {List<JButton>} tablesList - Target programmatic layout container collection.
     */
	public void setTablesList(List<JButton> tablesList) {
		this.tablesList = tablesList;
	}

	/**
     * @method createOvalButton
     * @private
     * @description Factoring mechanism configuration blueprint rendering isolated custom rounded graphic buttons matching designated text attributes.
     * @param {String} text - Title parameter assigned onto the face of the visual rendering button element.
     * @returns {JButton} Customized layout button configured for table visualizations.
     */
	private JButton createOvalButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(tableFont);
		btn.setForeground(Color.BLACK);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setFocusPainted(false);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 999);
		return btn;
	}

	/**
     * @method stabilizeAction
     * @private
     * @description Enforces strict unified geometric boundaries, standard color layouts, font choices, 
     * and flat layout scaling behaviors onto control menu button assets.
     * @param {JButton} btn - Target interface control element subjected to structural design constraints.
     */
	private void stabilizeAction(JButton btn) {
		Dimension d = new Dimension(200, 50);
		btn.setPreferredSize(d);
		btn.setMaximumSize(d);
		btn.setMinimumSize(d);
		btn.setAlignmentX(Box.LEFT_ALIGNMENT);
		
		btn.setFont(buttonFont);
		btn.setBackground(actionColor);
		btn.setForeground(Color.WHITE);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 20);
	}
}