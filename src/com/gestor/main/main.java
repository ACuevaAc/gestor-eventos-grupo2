package com.gestor.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.gestor.controller.loginController;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;

public class main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		LoginView log = new LoginView();
		UserService u = new UserService();
		loginController l = new loginController(log, u);
		log.setVisible(true);

	}

}
