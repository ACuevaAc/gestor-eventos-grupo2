package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.Usuario;
import com.gestor.service.SecurityService;
import com.gestor.service.usuarioService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.signupadminView;

public class signupadminController {
	private signupadminView view;
	private AdminController cont;
	private usuarioService uService;
	
	public signupadminController(signupadminView v,AdminController c) {
		
		this.view=v;
		this.cont=c;
		this.uService=new usuarioService();
		
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
		Usuario user=new Usuario();
		user.setNombreUsuario(view.getTxtNombre().getText());
		user.setEmailUsuario(view.getTxtConfirmEmail().getText());
		user.setEdad(Integer.parseInt(view.getTxtEdad().getText()));
		String psw=String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash=SecurityService.hashString(psw);
		user.setPswUsuario(hash);
		user.setRolUsuario("ADMIN");
		
		boolean registrar=false;
		if(validacion()) {
			registrar=uService.registrar(user);
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
