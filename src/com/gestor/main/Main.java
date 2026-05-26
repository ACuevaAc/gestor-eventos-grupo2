package com.gestor.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.gestor.controller.LoginController;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;

public class main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		LoginView log = new LoginView();
		UserService u = new UserService();
		LoginController l = new LoginController(log, u);
		log.setVisible(true);

	}

}
