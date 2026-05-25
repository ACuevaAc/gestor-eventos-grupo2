package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Mesa;
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
		List<JButton> lista = view.getTablesList();
       List<Mesa> mesas=ms.obtenerMesasCreadas();
       
       for(JButton boton: lista) {
    	   boton.setBackground(null);
       }
       
       for(int i=0;i<lista.size()&& i< mesas.size();i++) {
    	   JButton boton=lista.get(i);
    	   Mesa mesa=mesas.get(i);
    	   
    	   
    	   if(mesa.isMesa_Reservada()) {
    		   boton.setBackground(Color.red);
    	   } else {
    		   boton.setBackground(Color.green);
    	   }
       }
       
    }
	}
	


