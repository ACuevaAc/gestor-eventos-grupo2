package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.service.mesaService;
import com.gestor.view.user.UserMainView;

public class UserController {

	private UserMainView view;
	private mesaService ms;
	
	public UserController(UserMainView v) {
		this.view=v;
		ms=new mesaService();
		cargarMesasCreadas();
	}
	public void cargarMesasCreadas() {
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
	}
	


