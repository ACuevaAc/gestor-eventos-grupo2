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
		
		us=new UserService();
		
		loadTable();
		view.getBtnBack().addActionListener(e-> back());
		view.getBtnDelete().addActionListener(e-> delete());
		
		
	}
	public void loadTable() {
		DefaultTableModel modelo=view.getModelo();
		modelo.setRowCount(0);
		List<User> lista=us.getListUsers();
		for(User us:lista) {
			modelo.addRow(new Object[] {
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
		JTable tabla=view.getTabla();
		int fila=tabla.getSelectedRow();
		String email= (String) tabla.getValueAt(fila, 0);
		
		us.deleteFromEmail(email);
		loadTable();
	}

}
