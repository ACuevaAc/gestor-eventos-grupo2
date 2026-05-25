package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.usuarioService;
import com.gestor.view.LoginView;
import com.gestor.view.SignupView;

public class registerController {
	
	private usuarioService uService;
	private SignupView view;
	private loginController cont;

	public registerController(SignupView v, loginController loginController, usuarioService uService) {
		this.view=v;
		this.cont=loginController;
		this.uService=uService;
		
		view.getBtnCreate().addActionListener(e-> registrar());
		view.getBtnBack().addActionListener(e-> back());
	}
	public void back() {
		view.dispose();
		LoginView v=new LoginView();
		v.setVisible(true);
		new loginController(v,uService);
	}
	public boolean validacion() {
		String nom=view.getTxtName().getText();
		int edad=Integer.parseInt(view.getTxtAge().getText());

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
		User user=new User();

		user.setName(view.getTxtName().getText());
		user.setEmail(view.getTxtConfirmEmail().getText());
		user.setAge(Integer.parseInt(view.getTxtAge().getText()));
		String psw = String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash = SecurityService.hashString(psw);
		user.setPassword(hash);
		user.setRole("USER");
		
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
		    view.getTxtName().setText("");
		    view.getTxtAge().setText("");
		    view.getTxtEmail().setText("");
		    view.getTxtConfirmEmail().setText("");
		    view.getTxtPassword().setText("");
		    view.getTxtConfirmPassword().setText("");
	
	}

}
