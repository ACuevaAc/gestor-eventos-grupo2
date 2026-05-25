package com.gestor.controller;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;
import com.gestor.view.SignupView;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.user.UserMainView;

public class loginController {

    private LoginView view;
    private UserService uService;

    public loginController(LoginView v, UserService us) {

        this.view = v;
        this.uService = us;

        view.getBtnLogIn().addActionListener(e -> login());
        view.getBtnSignUp().addActionListener(e -> register());
    }

    public void register() {

        view.dispose();

        SignupView v = new SignupView();
        v.setVisible(true);

        new registerController(v, this, uService);
    }

    public void login() {

        String email = view.getTxtEmail().getText();
        String psw = String.valueOf(view.getPasswordField().getPassword());

        User usuario = uService.login(email, psw);

        if (usuario != null) {

            if ("ADMIN".equalsIgnoreCase(usuario.getRole())) {

                System.out.println("LOG- ADMIN");

                view.dispose();

                AdminMainView v = new AdminMainView();
                v.setVisible(true);

                new AdminController(v);

            } else if ("USER".equalsIgnoreCase(usuario.getRole())) {

                view.dispose();

                UserMainView v = new UserMainView(usuario);
                v.setVisible(true);

                new UserController(v);

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Error: El usuario no tiene un rol válido asignado."
                );
            }

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Credenciales incorrectas"
            );
        }
    }
}