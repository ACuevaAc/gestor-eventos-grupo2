package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.UserService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.signupadminView;

public class signupadminController {
	private signupadminView view;
	private AdminController cont;
	private UserService uService;
	
	public signupadminController(signupadminView v,AdminController c) {
		
		this.view=v;
		this.cont=c;
		this.uService=new UserService();
		
		view.getBtnCreate().addActionListener(e-> registrar());
		view.getBtnBack().addActionListener(e-> volver());
	}
	public void volver() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	public boolean validacion() {
		String nom=view.getTxtNombre().getText();
		int edad=Integer.parseInt(view.getTxtEdad().getText());

		String em=view.getTxtEmail().getText();
		String emConf=view.getTxtConfirmEmail().getText();
		
		String psw=String.valueOf(view.getTxtPassword().getPassword());
		String pswConf=String.valueOf(view.getTxtConfirmPassword().getPassword());
		
		if(nom.isEmpty()||edad<1||em.isEmpty()||psw.isEmpty()) {
			return false;
		}				
		return em.equals(emConf) && psw.equals(pswConf);
	}
	public void registrar() {
		User user = new User();
		user.setName(view.getTxtNombre().getText());
		user.setEmail(view.getTxtConfirmEmail().getText());
		user.setAge(Integer.parseInt(view.getTxtEdad().getText()));
		String psw = String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash = SecurityService.hashString(psw);
		user.setPassword(hash);
		user.setRole("ADMIN");
		
		boolean registrar=false;
		if(validacion()) {
			registrar=uService.register(user);
		}
		
		if(registrar) {
			JOptionPane.showMessageDialog(null, "Registrado con exito");
			limpiarCampos(view.getContentPane());
		} else {
			JOptionPane.showMessageDialog(null, "Error en el registro");
		}
	}
	public void limpiarCampos(Container cont) {
		    view.getTxtNombre().setText("");
		    view.getTxtEdad().setText("");
		    view.getTxtEmail().setText("");
		    view.getTxtConfirmEmail().setText("");
		    view.getTxtPassword().setText("");
		    view.getTxtConfirmPassword().setText("");
	
	}

}
