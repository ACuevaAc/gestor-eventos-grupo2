package com.gestor.controller;

import javax.swing.JOptionPane;

import com.gestor.model.entity.Usuario;
import com.gestor.service.usuarioService;
import com.gestor.view.loginView;
import com.gestor.view.signupView;
import com.gestor.view.admin.AdminMainView;

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
	    String email = view.getTxtEmail().getText();
	    String psw = String.valueOf(view.getPasswordField().getPassword());
	    
	    Usuario usuario = uService.login(email, psw);
	    if (usuario != null) {
	        if ("ADMIN".equalsIgnoreCase(usuario.getRolUsuario())) {
	            System.out.println("LOG- ADMIN");
	            view.dispose();
	            AdminMainView v = new AdminMainView();
	            v.setVisible(true);
	            new AdminController(v);
	        } else if ("USER".equalsIgnoreCase(usuario.getRolUsuario())) {
	            System.out.println("LOG- ROL USER");                
	        } else {
	            JOptionPane.showMessageDialog(null, "Error: El usuario no tiene un rol válido asignado.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
	    }
	}


}
