package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;
import com.gestor.view.SignupView;

public class RegisterController {
	
	private UserService uService;
	private SignupView view;
	private LoginController cont;

	public RegisterController(SignupView v, LoginController loginController, UserService uService) {
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
		new LoginController(v,uService);
	}
	public boolean validation () {
		String name = view.getTxtName().getText();
		int age = Integer.parseInt(view.getTxtAge().getText());

		String email = view.getTxtEmail().getText();
		String emailConfirm = view.getTxtConfirmEmail().getText();

		String password = String.valueOf(view.getTxtPassword().getPassword());
		String passwordConfirm = String.valueOf(view.getTxtConfirmPassword().getPassword());

		if (name.isEmpty() || age < 1 || email.isEmpty() || password.isEmpty()) {
			return false;
		}
		return email.equals(emailConfirm) && password.equals(passwordConfirm);
	}
	public void registrar() {
		User user=new User();

		user.setName(view.getTxtName().getText());
		user.setEmail(view.getTxtConfirmEmail().getText());
		user.setAge(Integer.parseInt(view.getTxtAge().getText()));
		String password = String.valueOf(view.getTxtConfirmPassword().getPassword());
		String hash = SecurityService.hashString(password);
		user.setPassword(hash);
		user.setRole("USER");
		
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
	public void clear (Container cont) {
		view.getTxtName().setText("");
		view.getTxtAge().setText("");
		view.getTxtEmail().setText("");
		view.getTxtConfirmEmail().setText("");
		view.getTxtPassword().setText("");
		view.getTxtConfirmPassword().setText("");
	}

}
