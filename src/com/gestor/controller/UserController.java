package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Table;
import com.gestor.service.TableService;
import com.gestor.view.user.UserMainView;

/**
 * @class UserController
 * @description Controller architectural component managing the standard user session ecosystem,
 * orchestrating real-time inventory visibility streams, database entity extraction mapping,
 * and structural grid UI component allocation styling.
 */
public class UserController {

	private UserMainView view;
	private TableService ms;
	
	/**
     * @constructor
     * @description Initializes the standard client operation tracking controller context,
     * builds down-stream operational service instances, and initiates visual inventory rendering pipelines.
     * @param {UserMainView} v - Primary user workspace frame interface container tracking session mappings.
     */
	public UserController(UserMainView v) {
		this.view = v;
		ms = new TableService();
		loadCreatedTables();
	}

	/**
     * @method loadCreatedTables
     * @description Flushes interactive workspace button matrices to a clean state, maps live historic metrics 
     * from database collections, and dynamically applies semantic signaling colors (RED/GREEN) to reflect resource allocation.
     */
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