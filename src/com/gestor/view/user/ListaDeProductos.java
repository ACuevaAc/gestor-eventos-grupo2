package com.gestor.view.user;

import java.awt.BorderLayout;

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

import com.gestor.model.entity.Producto;

public class ListaDeProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAñadir;
	private JButton btnTerminar;
	private JLabel lblPrecioTotal,lblFoto,lblPrecioNum;
	private DefaultListModel<Producto> modelo;
	private JPanel panelBack;
	private JPanel panelProduct;
	private JPanel panelNext;
	private JButton btnBack;
	private JButton btnNext;
	private JComboBox<Integer> CantCB;
	private JLabel lblCantidad,lblTituloProducto,lblPrecio;

	/**
	 * Create the frame.
	 */
	public ListaDeProductos() {
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

		// metodo para leer productos AQUI
		modelo = new DefaultListModel<Producto>();

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

		// de test Icon foto = new ImageIcon("resources/ñam.jpg");
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

	}

	public static void main(String[] args) {
		ListaDeProductos ldp = new ListaDeProductos();
		ldp.setVisible(true);
	}
	
	public void setPrecioNum(JLabel lblPrecioNum) {
		this.lblPrecioNum=lblPrecioNum;
	}
	public JLabel getPrecioNum() {
		return lblPrecioNum;
	}
	public JComboBox<Integer> getCantCB() {
		return CantCB;
	}
	public JLabel getLblFoto() {
		return lblFoto;
	}

	public void setCantCB(JComboBox<Integer> cantCB) {
		CantCB = cantCB;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public JLabel getLblTituloProducto() {
		return lblTituloProducto;
	}

	public void setLblTituloProducto(JLabel lblTituloProducto) {
		this.lblTituloProducto = lblTituloProducto;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public JButton getBtnAñadir() {
		return btnAñadir;
	}

	public void setBtnAñadir(JButton btnAñadir) {
		this.btnAñadir = btnAñadir;
	}

	public JButton getBtnTerminar() {
		return btnTerminar;
	}

	public void setBtnTerminar(JButton btnTerminar) {
		this.btnTerminar = btnTerminar;
	}

	public JLabel getLblPrecioTotal() {
		return lblPrecioTotal;
	}

	public void setLblPrecioTotal(JLabel lblPrecioTotal) {
		this.lblPrecioTotal = lblPrecioTotal;
	}

	public DefaultListModel<Producto> getModelo() {
		return modelo;
	}

	public void setModelo(DefaultListModel<Producto> modelo) {
		this.modelo = modelo;
	}

	public JPanel getPanelProduct() {
		return panelProduct;
	}

	public void setPanelProduct(JPanel panelProduct) {
		this.panelProduct = panelProduct;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
	
}
