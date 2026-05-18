package com.gestor.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.gestor.controller.loginController;
import com.gestor.service.usuarioService;
import com.gestor.view.loginView;

public class main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		loginView log = new loginView();
		usuarioService u = new usuarioService();
		loginController l = new loginController(log, u);
		log.setVisible(true);

	}

}
