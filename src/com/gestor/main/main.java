package com.gestor.main;

import com.gestor.controller.loginController;
import com.gestor.service.usuarioService;
import com.gestor.view.LoginView;

public class main {

	public static void main(String[] args) {
		LoginView log=new LoginView();
		usuarioService u=new usuarioService();
		loginController l=new loginController(log,u);
		log.setVisible(true);

	}

}
