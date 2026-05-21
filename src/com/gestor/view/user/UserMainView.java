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

import com.gestor.model.entity.Mesa;
import com.gestor.model.entity.Usuario;
import com.gestor.service.Reserva_Service;
import com.gestor.service.mesaService;

public class UserMainView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private Reserva_Service rs;
	private mesaService ms;

	private Usuario usuario;

	private final Color COLOR_FONDO = new Color(248, 249, 250);

	private final Font FUENTE_MESAS = new Font("Segoe UI", Font.BOLD, 18);

	private List<JButton> mesasList = new ArrayList<>();

	public UserMainView(Usuario usuario) {

		super("Reservar mesas");

		this.usuario = usuario;

		this.rs = new Reserva_Service();
		this.ms = new mesaService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(850, 850);

		setMinimumSize(new Dimension(650, 750));

		setLocationRelativeTo(null);

		contentPane = new JPanel();

		contentPane.setBackground(COLOR_FONDO);

		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout());

		JPanel mesasPanel = new JPanel(new GridLayout(4, 1, 0, 20));

		mesasPanel.setOpaque(false);

		List<Mesa> mesas = ms.obtenerMesasCreadas();

		int[] esquema = { 3, 2, 3, 2 };

		int indiceMesa = 0;

		for (int numMesas : esquema) {

			JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

			fila.setOpaque(false);

			for (int i = 0; i < numMesas && indiceMesa < mesas.size(); i++) {

				Mesa mesa = mesas.get(indiceMesa++);

				JButton btn = crearBotonOvalado(mesa.getNombre());

				btn.putClientProperty("idMesa", mesa.getId());

				if (mesa.isMesa_Reservada()) {

					btn.setBackground(Color.RED);

				} else {

					btn.setBackground(Color.GREEN);
				}

				btn.addActionListener(e -> {

					JButton boton = (JButton) e.getSource();
					int idMesa = (int) boton.getClientProperty("idMesa");

					if (boton.getBackground().equals(Color.GREEN)) {
						int opcion = JOptionPane.showConfirmDialog(this, "¿Reservar " + mesa.getNombre() + "?",
								"Reserva", JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
							rs.realizarReserva(usuario.getIdUsuario(), idMesa, LocalDateTime.now());

							ms.reservarMesa(idMesa);

							boton.setBackground(Color.RED);

							JOptionPane.showMessageDialog(this, "Mesa reservada correctamente");
						}

					} else {

						JOptionPane.showMessageDialog(this, "La mesa ya está reservada");
					}
				});

				mesasList.add(btn);

				fila.add(btn);
			}

			mesasPanel.add(fila);
		}

		contentPane.add(mesasPanel, BorderLayout.CENTER);

		actualizarTamanoMesas(mesasPanel.getWidth(), mesasPanel.getHeight());

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				actualizarTamanoMesas(mesasPanel.getWidth(), mesasPanel.getHeight());
			}
		});
	}

	private void actualizarTamanoMesas(int anchoPanel, int altoPanel) {

		int gapHorizontal = 30;

		int anchoIdeal = (anchoPanel - (gapHorizontal * 4)) / 3;

		int altoIdeal = (altoPanel - (20 * 4)) / 5;

		anchoIdeal = Math.max(120, Math.min(anchoIdeal, 250));

		altoIdeal = Math.max(70, Math.min(altoIdeal, 120));

		Dimension nuevaDimension = new Dimension(anchoIdeal, altoIdeal);

		for (JButton btn : mesasList) {

			btn.setPreferredSize(nuevaDimension);
		}

		contentPane.revalidate();
	}

	public List<JButton> getMesasList() {
		return mesasList;
	}

	public void setMesasList(List<JButton> mesasList) {
		this.mesasList = mesasList;
	}

	private JButton crearBotonOvalado(String texto) {

		JButton btn = new JButton(texto);

		btn.setFont(FUENTE_MESAS);

		btn.setForeground(Color.BLACK);

		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btn.setFocusPainted(false);

		btn.putClientProperty("JButton.buttonType", "roundRect");

		btn.putClientProperty("JButton.cornerRadius", 999);

		return btn;
	}
}