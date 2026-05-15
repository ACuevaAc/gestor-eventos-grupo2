package com.gestor.controller;

import javax.swing.JOptionPane;

import com.gestor.model.entity.Usuario;
import com.gestor.service.usuarioService;
import com.gestor.view.loginView;
import com.gestor.view.signupView;

public class loginController {
	private loginView view;
	private usuarioService uService;
	
	public loginController(loginView v,usuarioService us) {
		this.view=v;
		this.uService=us;
		
		view.getBtnLogIn().addActionListener(e-> login());
		view.getBtnSignUp().addActionListener(e-> register());
	}
	public void register() {
		view.dispose();
		signupView v=new signupView();
		v.setVisible(true);
		new registerController(v,this,uService);
	}
	public void login() {
		String email=view.getTxtEmail().getText();
		String psw=String.valueOf(view.getPasswordField().getPassword());
		
		Usuario usuario=uService.login(email, psw);
		if(usuario!=null) {
			JOptionPane.showMessageDialog(null, "Login Correcto ");
		} else {
			JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
		}
		
	}
	

}
