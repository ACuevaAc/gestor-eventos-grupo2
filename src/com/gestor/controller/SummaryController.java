package com.gestor.controller;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.SummaryBook;
import com.gestor.service.SummaryService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.ViewSummaryBook;

/**
 * @class SummaryController
 * @description Controller architectural component managing reservation logging summaries,
 * handling relational dataset parsing for tabular structural interfaces, data grid population,
 * and standard dashboard view transitions.
 */
public class SummaryController {
	private ViewSummaryBook view;
	private SummaryService ss;
	
	/**
     * @constructor
     * @description Initializes the administrative tracking booking log controller context,
     * builds down-stream operational service instances, populates view matrices, and binds layout listeners.
     * @param {ViewSummaryBook} v - Tabular registration summary log frame view container.
     */
	public SummaryController(ViewSummaryBook v) {
		this.view=v;
		ss=new SummaryService();
		loadTable();
		view.getBack().addActionListener(e-> back());
	}

	/**
     * @method loadTable
     * @description Flushes index row states across GUI data tables, requests historic assignment metrics 
     * from data persistence endpoints, and iterates raw records into structural table interface rows.
     */
	public void loadTable() {
		DefaultTableModel model=view.getModelo();
		model.setRowCount(0);
		List<SummaryBook> lista=ss.getAllSummaryBooks();
		for(SummaryBook book:lista) {
			model.addRow(new Object[] {
					book.getBookId(),
					book.getTableName(),
					book.getUserName(),
					book.getReservationDate()
			});
		}
	}

	/**
     * @method back
     * @description Dismantles view telemetry framework elements to transfer operational flow visibility 
     * back to the parent dashboard management window loop.
     */
	public void back() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
		
	}
	

}
