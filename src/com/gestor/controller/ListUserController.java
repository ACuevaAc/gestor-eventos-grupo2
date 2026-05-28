package com.gestor.controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.User;
import com.gestor.service.UserService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.ListUsersView;

public class ListUserController {
	
	private ListUsersView view;
	private UserService us;
	
	public ListUserController(ListUsersView v) {
		this.view=v;
		
		us = new UserService();
		
		loadTable();
		view.getBtnBack().addActionListener(e-> back());
		view.getBtnDelete().addActionListener(e-> delete());
		view.getSearch().addActionListener(e-> search());
		
		
	}
	public void search() {
		String text=view.getSearch().getText();
		List<User> list=us.getListByName(text);
		if(text.isEmpty()) {
			loadTable();
		} else
			filterUser(list);
		
	}
	public void filterUser(List<User>list) {
		DefaultTableModel model=view.getModelo();
		model.setRowCount(0);
		
		for(User u:list) {
			model.addRow(new Object[] {
				u.getEmail(),
				u.getPassword(),
				u.getRole()	
			});
		}
	}
	public void loadTable() {
		DefaultTableModel model = view.getModelo();
		model.setRowCount(0);

		List<User> list = us.getListUsers();

		for(User us : list) {
			model.addRow(new Object[] {
				us.getEmail(),
				us.getPassword(),
				us.getRole()
			});
		}
	}
	public void back() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	public void delete() {
		JTable table = view.getTabla();
		int row = table.getSelectedRow();
		String email = (String) table.getValueAt(row, 0);
		
		us.deleteFromEmail(email);
		loadTable();
	}

}
