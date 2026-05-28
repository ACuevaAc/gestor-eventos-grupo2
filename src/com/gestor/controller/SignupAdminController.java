package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.UserService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.signupadminView;

public class SignupAdminController {
	private signupadminView view;
	private AdminController cont;
	private UserService uService;
	
	public SignupAdminController(signupadminView v,AdminController c) {
		
		this.view=v;
		this.cont=c;
		this.uService=new UserService();
		
		view.getBtnCreate().addActionListener(e-> register());
		view.getBtnBack().addActionListener(e-> goBack());
	}
	public void goBack() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	public boolean validation() {
		String name = view.getTxtNombre().getText();
		int age = Integer.parseInt(view.getTxtEdad().getText());

		String email = view.getTxtEmail().getText();
		String emailConfirm = view.getTxtConfirmEmail().getText();

		String password = String.valueOf(view.getTxtPassword().getPassword());
		String passwordConfirm = String.valueOf(view.getTxtConfirmPassword().getPassword());

		if(name.isEmpty() || age < 1 || email.isEmpty() || password.isEmpty()) {
			return false;
		}				
		return email.equals(emailConfirm) && password.equals(passwordConfirm);
	}
	public void register () {
		User user = new User();
		user.setName(view.getTxtNombre().getText());
		user.setEmail(view.getTxtConfirmEmail().getText());
		user.setAge(Integer.parseInt(view.getTxtEdad().getText()));
		String psw = String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash = SecurityService.hashString(psw);
		user.setPassword(hash);
		user.setRole("ADMIN");
		
		boolean register = false;
		if (validation()) {
			register = uService.register(user);
		}
		
		if(register) {
			JOptionPane.showMessageDialog(null, "Registrado con exito");
			clear(view.getContentPane());
		} else {
			JOptionPane.showMessageDialog(null, "Error en el registro");
		}
	}
	public void clear(Container cont) {
		view.getTxtNombre().setText("");
		view.getTxtEdad().setText("");
		view.getTxtEmail().setText("");
		view.getTxtConfirmEmail().setText("");
		view.getTxtPassword().setText("");
		view.getTxtConfirmPassword().setText("");
	}

}
