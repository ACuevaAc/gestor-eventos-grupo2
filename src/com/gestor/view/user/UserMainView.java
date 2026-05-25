package com.gestor.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gestor.controller.UserTableController;
import com.gestor.model.entity.Table;
import com.gestor.model.entity.User;
import com.gestor.service.Reserva_Service;
import com.gestor.service.mesaService;

public class UserMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Reserva_Service rs;
	private mesaService ms;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Font tableFont = new Font("Segoe UI", Font.BOLD, 18);
	
	private List<JButton> tablesList = new ArrayList<>();

	public UserMainView(User user) {

		super("Reservar mesas");

		this.rs = new Reserva_Service();
		this.ms = new mesaService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 850);
		setMinimumSize(new Dimension(650, 750));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel tablesPanel = new JPanel(new GridLayout(4, 1, 0, 20));
		tablesPanel.setOpaque(false);
		List<Table> tables = ms.obtenerMesasCreadas();

		int[] scheme = { 3, 2, 3, 2 };
		int tableIndex = 0;

		for (int numMesas : scheme) {
			JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
			row.setOpaque(false);

			for (int i = 0; i < numMesas && tableIndex < tables.size(); i++) {
				Table table = tables.get(tableIndex++);
				JButton btn = createOvalButton(table.getName());
				btn.putClientProperty("idMesa", table.getId());

				if (table.isBooked()) btn.setBackground(Color.RED);
				else btn.setBackground(Color.GREEN);

				btn.addActionListener(e -> {
					JButton button = (JButton) e.getSource();
					int tableId = (int) button.getClientProperty("idMesa");

					if (button.getBackground().equals(Color.GREEN)) {
						int opcion = JOptionPane.showConfirmDialog(this, "¿Reservar " + table.getName() + "?", "Reserva", JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
							rs.realizarReserva(user.getId(), tableId, LocalDateTime.now());
							ms.reservarMesa(tableId);
							button.setBackground(Color.RED);
							JOptionPane.showMessageDialog(this, "Mesa reservada correctamente");
							dispose();
							ListaDeProductos v=new ListaDeProductos();
							v.setVisible(true);
							new UserTableController(v,tableId);
							
						}
					} else {
						JOptionPane.showMessageDialog(this, "La mesa ya está reservada");
					}
				});

				tablesList.add(btn);
				row.add(btn);
			}

			tablesPanel.add(row);
		}

		contentPane.add(tablesPanel, BorderLayout.CENTER);
		updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
			}
		});
	}

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

	public List<JButton> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<JButton> tablesList) {
		this.tablesList = tablesList;
	}

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
}