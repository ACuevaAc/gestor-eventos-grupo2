package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Table;
import com.gestor.service.TableService;
import com.gestor.view.user.UserMainView;

public class UserController {

	private UserMainView view;
	private TableService ms;
	
	public UserController(UserMainView v) {
		this.view=v;
		ms=new TableService();
		cargarMesasCreadas();
	}
	public void cargarMesasCreadas() {
		List<JButton> lista = view.getTablesList();
       List<Table> mesas=ms.getCreatedTables();
       
       for(JButton boton: lista) {
    	   boton.setBackground(null);
       }
       
       for(int i=0;i<lista.size()&& i< mesas.size();i++) {
    	   JButton boton=lista.get(i);
    	   Table mesa=mesas.get(i);
    	   
    	   
    	   if(mesa.isBooked()) {
    		   boton.setBackground(Color.red);
    	   } else {
    		   boton.setBackground(Color.green);
    	   }
       }
       
    }
	}
	


