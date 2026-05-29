package com.gestor.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.User;
import com.gestor.service.UserService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.ListUsersView;

/**
 * @class ListUserController
 * @description Controller architectural component managing user administration grids,
 * handling real-time query filtration, selection extraction, cascade deletion workflows,
 * and contextual navigation state transitions.
 */
public class ListUserController {
	
	/**
     * @private
     * @type {ListUsersView}
     */
    private ListUsersView view;
    
    /**
     * @private
     * @type {UserService}
     */
    private UserService us;
	
	/**
     * @constructor
     * @description Initializes the user directory tracking sub-system context, establishes dependencies, 
     * triggers initial data matrix loading, and hooks action listeners to graphical trigger resources.
     * @param {ListUsersView} v - User directory tabular representation view container.
     */
	public ListUserController(ListUsersView v) {
		this.view=v;
		
		us = new UserService();
		
		loadTable();
		view.getBtnBack().addActionListener(e-> back());
		view.getBtnDelete().addActionListener(e-> delete());
		view.getSearch().addActionListener(e-> search());
		
		
	}

	/**
     * @method search
     * @description Extracts raw text criteria metrics from the search input widget component 
     * and evaluates whether to invoke filtering sequences or restore baseline system records.
     */
	public void search() {
		String text=view.getSearch().getText();
		List<User> list=us.getListByName(text);
		if(text.isEmpty()) {
			loadTable();
		} else
			filterUser(list);
		
	}

	/**
     * @method filterUser
     * @description Flushes the structural grid layer row indexes and renders a partial subset collection 
     * of matches extracted from user filtration queries.
     * @param {List<User>} list - Custom narrowed payload collection of target user entities matching search terms.
     */
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

	/**
     * @method loadTable
     * @description Purges interface matrix components and initiates synchronous analytical queries 
     * to populate the view model blueprint with the absolute dataset from identity registers.
     */
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

	/**
     * @method back
     * @description Disposes active user directory view resources to yield window operational focus 
     * back to primary administration dashboard control interfaces.
     */
	public void back() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}

	/**
     * @method delete
     * @description Evaluates grid layout selection states, extracts specific identity primary keys 
     * from targeting cells, prompts transactional deletions via core service layers, and refreshes the ui grid view.
     */
	public void delete() {
		JTable table = view.getTabla();
		int row = table.getSelectedRow();
		if(row!=-1) {
			String email = (String) table.getValueAt(row, 0);
			us.deleteFromEmail(email);
			loadTable();
		} else
			JOptionPane.showMessageDialog(null, "Selecciona un usuario");
	}

}
