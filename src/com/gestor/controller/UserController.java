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
		this.view = v;
		ms = new TableService();
		loadCreatedTables();
	}

	public void loadCreatedTables() {
		List<JButton> list = view.getTablesList();
		List<Table> tables = ms.getCreatedTables();
       
		for(JButton button: list) {
			button.setBackground(null);
		}
       
       for(int i=0; i < list.size() && i < tables.size(); i++) {
			JButton button=list.get(i);
			Table table = tables.get(i);
    	   
			if(table.isBooked()) {
				button.setBackground(Color.red);
			} else {
				button.setBackground(Color.green);
			}
		}
    }
}
	


