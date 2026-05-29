package com.gestor.view.user;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.gestor.model.entity.Product;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @class ListaDeProductos
 * @description Client-facing digital menu interface window managing transactional item selections.
 * Orchestrates pagination view states for menu browsing, tracks volume bounds via selection elements,
 * updates invoice metrics dynamically, and registers persistence boundary triggers for session termination or cancellations.
 */
public class ListaDeProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAñadir;
	private JButton btnTerminar;
	private JLabel lblPrecioTotal,lblFoto,lblPrecioNum;
	private DefaultListModel<Product> modelo;
	private JPanel panelBack;
	private JPanel panelProduct;
	private JPanel panelNext;
	private JButton btnBack;
	private JButton btnNext;
	private JComboBox<Integer> CantCB;
	private JLabel lblCantidad,lblTituloProducto,lblPrecio;
	private JPanel ExitPanel;
	private JButton btnExit;
	private JButton btnExitandClose;
	

	/**
     * @constructor
     * @description Constructs the digital catalog viewer canvas, establishing spatial footprint limits,
     * mounting directional layout containers, building structural pagination side elements, and rendering control triggers.
     */
	public ListaDeProductos() {
		super("Carta del restaurante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setMinimumSize(new Dimension(450, 450));
		setMaximumSize(new Dimension(450,450));
		setLocationRelativeTo(null);
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
		btnAñadir.setToolTipText("Añadir Producto");
		panel.add(btnAñadir, BorderLayout.CENTER);

		btnTerminar = new JButton("Pagar y terminar");
		btnTerminar.setToolTipText("Pagar la cuenta");
		panel.add(btnTerminar, BorderLayout.EAST);

		modelo = new DefaultListModel<Product>();

		JPanel panel_Lista = new JPanel(new BorderLayout());
		contentPane.add(panel_Lista, BorderLayout.CENTER);

		panelBack = new JPanel();
		panel_Lista.add(panelBack, BorderLayout.WEST);

		btnBack = new JButton("Back");
		GroupLayout gl_panelBack = new GroupLayout(panelBack);
		gl_panelBack.setHorizontalGroup(gl_panelBack.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBack.createSequentialGroup().addContainerGap().addComponent(btnBack)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panelBack.setVerticalGroup(gl_panelBack.createParallelGroup(Alignment.LEADING).addGroup(gl_panelBack
				.createSequentialGroup().addGap(102).addComponent(btnBack).addContainerGap(109, Short.MAX_VALUE)));
		panelBack.setLayout(gl_panelBack);

		panelProduct = new JPanel();
		panel_Lista.add(panelProduct, BorderLayout.CENTER);
		panelProduct.setLayout(null);

		lblTituloProducto = new JLabel("Nombre Producto");
		lblTituloProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloProducto.setBounds(83, 10, 103, 21);
		panelProduct.add(lblTituloProducto);

		lblFoto = new JLabel();
		lblFoto.setBounds(10, 41, 260, 131);
		panelProduct.add(lblFoto);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 210, 44, 12);
		panelProduct.add(lblCantidad);

		CantCB = new JComboBox<>();
		for (int i = 1; i <= 10; i++) {
			CantCB.addItem(i);
		}
		CantCB.setBounds(186, 206, 84, 20);
		panelProduct.add(CantCB);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 182, 44, 12);
		panelProduct.add(lblPrecio);

		lblPrecioNum = new JLabel("0€");
		lblPrecioNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioNum.setBounds(226, 182, 44, 12);
		panelProduct.add(lblPrecioNum);

		panelNext = new JPanel();
		panel_Lista.add(panelNext, BorderLayout.EAST);

		btnNext = new JButton("Next");
		GroupLayout gl_panelNext = new GroupLayout(panelNext);
		gl_panelNext.setHorizontalGroup(gl_panelNext.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNext.createSequentialGroup().addContainerGap().addComponent(btnNext)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panelNext.setVerticalGroup(gl_panelNext.createParallelGroup(Alignment.LEADING).addGroup(gl_panelNext
				.createSequentialGroup().addGap(105).addComponent(btnNext).addContainerGap(106, Short.MAX_VALUE)));
		panelNext.setLayout(gl_panelNext);
		
		ExitPanel = new JPanel();
		contentPane.add(ExitPanel, BorderLayout.NORTH);
		ExitPanel.setLayout(new BorderLayout(0, 0));
		
		btnExit = new JButton("Salir");
		btnExit.setToolTipText("Salir y no cancelar la reserva");
		ExitPanel.add(btnExit, BorderLayout.EAST);
		
		btnExitandClose = new JButton("Salir y cancelar");
		btnExitandClose.setToolTipText("Cancelar reserva e irse");
		btnExitandClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ExitPanel.add(btnExitandClose, BorderLayout.WEST);

	}
	
	/**
     * @method setPrecioNum
     * @description Assigns a tracking layout text wrapper to display single item quantitative calculations.
     * @param {JLabel} lblPrecioNum - Target single entity value display element reference.
     */
	public void setPrecioNum(JLabel lblPrecioNum) {
		this.lblPrecioNum=lblPrecioNum;
	}

	/**
     * @method getPrecioNum
     * @description Exposes the unique label management node rendering single commodity financial balances.
     * @returns {JLabel} Core data cell presenter object reference.
     */
	public JLabel getPrecioNum() {
		return lblPrecioNum;
	}

	/**
     * @method getCantCB
     * @description Exposes the numeric discrete selection box defining active target purchase limits.
     * @returns {JComboBox<Integer>} Dropdown option constraint element component tracking selections.
     */
	public JComboBox<Integer> getCantCB() {
		return CantCB;
	}

	/**
     * @method getLblFoto
     * @description Exposes the graphical canvas target layer assigned to display resource illustrations.
     * @returns {JLabel} Presentation bitmap wrapper element tracking catalog visuals.
     */
	public JLabel getLblFoto() {
		return lblFoto;
	}

	/**
     * @method setCantCB
     * @description Replaces or injects a custom discrete numerical collection combo selector handling order amounts.
     * @param {JComboBox<Integer>} cantCB - Target selection interface management module.
     */
	public void setCantCB(JComboBox<Integer> cantCB) {
		CantCB = cantCB;
	}

	/**
     * @method getBtnExit
     * @description Resolves the session suspension node letting a user depart while keeping order parameters locked.
     * @returns {JButton} The passive exit transaction button component.
     */
	public JButton getBtnExit() {
		return btnExit;
	}

	/**
     * @method setBtnExit
     * @description Registers an isolated interface control element assigned to dispatch non-destructive window exits.
     * @param {JButton} btnExit - Target exit management button module reference.
     */
	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}

	/**
     * @method getBtnExitandClose
     * @description Resolves the operational rollback action trigger deployed to wipe allocations and discard pending carts.
     * @returns {JButton} The critical session termination button asset.
     */
	public JButton getBtnExitandClose() {
		return btnExitandClose;
	}
	
	/**
     * @method setBtnExitandClose
     * @description Configures an absolute cleanup transaction trigger tasked with clearing reservation profiles.
     * @param {JButton} btnExitandClose - Target active validation rejection action element.
     */
    public void setBtnExitandClose(JButton btnExitandClose) {
        this.btnExitandClose = btnExitandClose;
    }

    /**
     * @method getLblCantidad
     * @description Exposes the static contextual label signaling capacity selection constraints inside forms.
     * @returns {JLabel} Label interface asset tracking labels placement.
     */
    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    /**
     * @method setLblCantidad
     * @description Changes text properties or visibility limits across information headcount markers.
     * @param {JLabel} lblCantidad - Target capacity text annotation anchor.
     */
    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    /**
     * @method getLblTituloProducto
     * @description Exposes the text header element rendering current product entry descriptive tags.
     * @returns {JLabel} The main naming presentation label model context reference.
     */
    public JLabel getLblTituloProducto() {
        return lblTituloProducto;
    }

    /**
     * @method setLblTituloProducto
     * @description Injects or overrides properties driving active text strings inside item title displays.
     * @param {JLabel} lblTituloProducto - Target text information element identifier mapping.
     */
    public void setLblTituloProducto(JLabel lblTituloProducto) {
        this.lblTituloProducto = lblTituloProducto;
    }

    /**
     * @method getLblPrecio
     * @description Exposes the tracking description placeholder field mapping basic currency symbols.
     * @returns {JLabel} Visual informational layout component context references.
     */
    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    /**
     * @method setLblPrecio
     * @description Sets properties directly into standard contextual labeling tracking static markers.
     * @param {JLabel} lblPrecio - Target textual description layer context mapping.
     */
    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    /**
     * @method getBtnAñadir
     * @description Exposes the structural operation action hook that dispatches line-item payload additions to active state carts.
     * @returns {JButton} Core data insertion interface trigger node.
     */
    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    /**
     * @method setBtnAñadir
     * @description Links an explicit tracking control block tasked with handling row insertion routines into selections.
     * @param {JButton} btnAñadir - Target interactive append button module.
     */
    public void setBtnAñadir(JButton btnAñadir) {
        this.btnAñadir = btnAñadir;
    }

    /**
     * @method getBtnTerminar
     * @description Resolves the primary checkpoint submission element deployed to invoke accounting workflows and checkout runs.
     * @returns {JButton} Final transaction workflow confirmation trigger.
     */
    public JButton getBtnTerminar() {
        return btnTerminar;
    }

    /**
     * @method setBtnTerminar
     * @description Binds the main confirmation button entity tasked with launching invoice generation cascades.
     * @param {JButton} btnTerminar - Target billing processing pipeline element.
     */
    public void setBtnTerminar(JButton btnTerminar) {
        this.btnTerminar = btnTerminar;
    }

    /**
     * @method getLblPrecioTotal
     * @description Exposes the complex compound text element displaying cumulative transaction balance parameters.
     * @returns {JLabel} Core summary metrics monitoring presentation node.
     */
    public JLabel getLblPrecioTotal() {
        return lblPrecioTotal;
    }

    /**
     * @method setLblPrecioTotal
     * @description Overwrites summary layout balance configurations with refreshed quantitative balance metrics.
     * @param {JLabel} lblPrecioTotal - Target macro-accounting update rendering panel.
     */
    public void setLblPrecioTotal(JLabel lblPrecioTotal) {
        this.lblPrecioTotal = lblPrecioTotal;
    }

    /**
     * @method getModelo
     * @description Exposes the baseline inventory abstraction index driving element arrays mapping behind selections.
     * @returns {DefaultListModel<Product>} The internal structure managing the catalog item data arrays.
     */
    public DefaultListModel<Product> getModelo() {
        return modelo;
    }

    /**
     * @method setModelo
     * @description Replaces the active collection model wrapper with an external domain item index.
     * @param {DefaultListModel<Product> } modelo - Target structural data mapping collection manager.
     */
    public void setModelo(DefaultListModel<Product> modelo) {
        this.modelo = modelo;
    }

    /**
     * @method getPanelProduct
     * @description Returns the absolute coordinate wrapper handling individual component positioning for items display.
     * @returns {JPanel} The core centralized visual card container.
     */
    public JPanel getPanelProduct() {
        return panelProduct;
    }

    /**
     * @method setPanelProduct
     * @description Adjusts or isolates layout anchors mapping within main single asset preview regions.
     * @param {JPanel} panelProduct - Target centralized graphic content view board wrapper.
     */
    public void setPanelProduct(JPanel panelProduct) {
        this.panelProduct = panelProduct;
    }

    /**
     * @method getBtnBack
     * @description Resolves the retrospective layout navigation boundary tracking left pagination iterations.
     * @returns {JButton} Backward sequence tracking index component.
     */
    public JButton getBtnBack() {
        return btnBack;
    }

    /**
     * @method setBtnBack
     * @description Enforces explicit interaction components to manage backward menu view shifts.
     * @param {JButton} btnBack - Target step-back navigation button resource.
     */
    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    /**
     * @method getBtnNext
     * @description Resolves the forward progress navigation node tracking right pagination shifts across catalogs.
     * @returns {JButton} Forward step advancement action trigger.
     */
    public JButton getBtnNext() {
        return btnNext;
    }

    /**
     * @method setBtnNext
     * @description Mounts the specific navigational control component responsible for advance indexing shifts.
     * @param {JButton} btnNext - Target step-forward interface interaction anchor.
     */
    public void setBtnNext(JButton btnNext) {
        this.btnNext = btnNext;
    }
}
