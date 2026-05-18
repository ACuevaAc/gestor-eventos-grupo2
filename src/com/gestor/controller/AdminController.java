package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.service.mesaService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.GestorMesasView;

public class AdminController {
	private AdminMainView view;
	private mesaService ms;
	
	
	public AdminController(AdminMainView v) {
		this.view=v;
		this.ms=new mesaService();
		
		actualizarColoresMesas();
		view.getBtnCreateTable().addActionListener(e-> crearMesa());
		view.getBtnNewAdmin().addActionListener(e-> createNewAdmin());
	}
	
    public void actualizarColoresMesas() {
        List<JButton> lista = view.getMesasList();
        int totalMesas = ms.obtenerIdsMesas(); 
        for (JButton boton : lista) {
            boton.setBackground(null); 
        }
        int limite = Math.min(totalMesas, lista.size());
        for (int i = 0; i < limite; i++) {
            lista.get(i).setBackground(Color.GREEN);
        }
        view.repaint();
    }

	public AdminMainView getAdminView() {
		return view;
	}
	public void crearMesa() {
		view.setVisible(false);
		GestorMesasView v=new GestorMesasView();
		v.setVisible(true);
		new gestorMesasController(v,this);
		
	}
	public void createNewAdmin() {
		
	}
}

