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
		String name = view.getTxtNombre().getText().trim();
		String ageText = view.getTxtEdad().getText().trim();
		String email = view.getTxtEmail().getText().trim();
		String emailConfirm = view.getTxtConfirmEmail().getText().trim();

		String password = String.valueOf(view.getTxtPassword().getPassword());
		String passwordConfirm = String.valueOf(view.getTxtConfirmPassword().getPassword());
		if(name.isEmpty() || ageText.isEmpty() || email.isEmpty() || password.isEmpty()) {
			return false;
		}
		int age;
		try {
			age = Integer.parseInt(ageText);
		} catch (NumberFormatException e) {
			return false; 
		}
		if (age < 18) {
			return false;
		}				
		return email.equals(emailConfirm) && password.equals(passwordConfirm);
	}
	public void register () {
		if (!validation()) {
			JOptionPane.showMessageDialog(null, "Recuerda no dejar campos vacíos, usar números válidos en la edad y ser mayor de 18 años.");
			return;
		}

		User user = new User();
		user.setName(view.getTxtNombre().getText().trim());
		user.setEmail(view.getTxtConfirmEmail().getText().trim());
		user.setAge(Integer.parseInt(view.getTxtEdad().getText().trim())); 
		
		String psw = String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash = SecurityService.hashString(psw);
		user.setPassword(hash);
		user.setRole("ADMIN");
		
		boolean register = uService.register(user);
		
		if(register) {
			JOptionPane.showMessageDialog(null, "Registrado con éxito");
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
