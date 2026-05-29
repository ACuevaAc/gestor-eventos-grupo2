package com.gestor.controller;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;
import com.gestor.view.SignupView;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.user.UserMainView;

/**
 * @class LoginController
 * @description Controller architectural component managing the authentication lifecycle gateway,
 * handling credential processing, role-based session routing, and UI view transitions.
 */
public class LoginController {

    /**
     * @private
     * @type {LoginView}
     */
    private LoginView view;

    /**
     * @private
     * @type {UserService}
     */
    private UserService uService;

    /**
     * @constructor
     * @description Initializes the authorization gate entry context and maps action listeners to interface components.
     * @param {LoginView} v - The login credentials input collection view interface.
     * @param {UserService} us - The service layer managing identity validation business logic.
     */
    public LoginController(LoginView v, UserService us) {

        this.view = v;
        this.uService = us;

        view.getBtnLogIn().addActionListener(e -> login());
        view.getBtnSignUp().addActionListener(e -> register());
    }

    /**
     * @method register
     * @description Disposes of the active authentication window frame to transition the navigation routing 
     * context into the profile registration workflow wizard.
     */
    public void register() {

        view.dispose();

        SignupView v = new SignupView();
        v.setVisible(true);

        new RegisterController(v, this, uService);
    }

    /**
     * @method login
     * @description Extracts real-time credential payload input arrays, invokes backend validation pipelines, 
     * and evaluates identity authorization tokens to branch interface routing toward user or administrative dashboards.
     */
    public void login() {

        String email = view.getTxtEmail().getText();
        String psw = String.valueOf(view.getPasswordField().getPassword());

        User user = uService.login(email, psw);

        if (user != null) {

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {

                System.out.println("LOG- ADMIN");

                view.dispose();

                AdminMainView v = new AdminMainView();
                v.setVisible(true);

                new AdminController(v);

            } else if ("USER".equalsIgnoreCase(user.getRole())) {

                view.dispose();

                UserMainView v = new UserMainView(user);
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