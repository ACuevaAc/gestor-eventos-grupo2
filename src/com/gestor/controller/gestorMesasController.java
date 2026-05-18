package com.gestor.controller;

import com.gestor.model.entity.Mesa;
import com.gestor.service.mesaService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.GestorMesasView;

public class gestorMesasController {
	private GestorMesasView view;
	private mesaService ms;
	
	public gestorMesasController(GestorMesasView v) {
		
		this.view=v;
		
		ms= new mesaService();
		v.getBtnRegistrarModificar().addActionListener(e-> registrarMesa());
		v.getBtnAtras().addActionListener(e-> volverMenu());
		
		
	}
	public void volverMenu() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	public void registrarMesa() {
		String nom=view.getTxtNombre().getText();
		int num_max=(int) view.getcBgente().getSelectedItem();
		Mesa mesa=new Mesa();
		mesa.setNum_max(num_max);
		mesa.setNombre(nom);
		mesa.setMesa_Reservada(false);
		ms.crearMesa(mesa);
		
		int id=mesa.getId();
	}

}
