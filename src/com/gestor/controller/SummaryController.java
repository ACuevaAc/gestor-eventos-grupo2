package com.gestor.controller;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.SummaryBook;
import com.gestor.service.SummaryService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.ViewSummaryBook;

public class SummaryController {
	private ViewSummaryBook view;
	private SummaryService ss;
	
	public SummaryController(ViewSummaryBook v) {
		this.view=v;
		ss=new SummaryService();
		loadTable();
		view.getBack().addActionListener(e-> back());
	}
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
	public void back() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
		
	}
	

}
