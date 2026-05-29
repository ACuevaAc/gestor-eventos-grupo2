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
        this.view = v;
        this.cont = loginController;
        this.uService = uService;
        view.getBtnCreate().addActionListener(e -> registrar());
        view.getBtnBack().addActionListener(e -> back());
    }

    public void back() {
        view.dispose();
        LoginView v = new LoginView();
        v.setVisible(true);
        new LoginController(v, uService);
    }

    public boolean validation() {
        String name = view.getTxtName().getText().trim();
        String email = view.getTxtEmail().getText().trim();
        String emailConfirm = view.getTxtConfirmEmail().getText().trim();
        String password = String.valueOf(view.getTxtPassword().getPassword());
        String passwordConfirm = String.valueOf(view.getTxtConfirmPassword().getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int age;
        try {
            age = Integer.parseInt(view.getTxtAge().getText().trim());
            if (age < 1) {
                JOptionPane.showMessageDialog(null, "La edad debe ser mayor a 0.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.equals(emailConfirm)) {
            JOptionPane.showMessageDialog(null, "Los correos electrónicos no coinciden.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void registrar() {
        if (!validation()) {
            return; 
        }

        User user = new User();
        user.setName(view.getTxtName().getText().trim());
        user.setEmail(view.getTxtConfirmEmail().getText().trim());
        user.setAge(Integer.parseInt(view.getTxtAge().getText().trim()));
        
        String password = String.valueOf(view.getTxtConfirmPassword().getPassword());
        String hash = SecurityService.hashString(password);
        user.setPassword(hash);
        user.setRole("USER");

        boolean register = uService.register(user);

        if (register) {
            JOptionPane.showMessageDialog(view, "Registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            clear(view.getContentPane());
        } else {
            JOptionPane.showMessageDialog(view, "Error en el registro. El usuario ya existe o hubo un fallo en el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clear(Container cont) {
        view.getTxtName().setText("");
        view.getTxtAge().setText("");
        view.getTxtEmail().setText("");
        view.getTxtConfirmEmail().setText("");
        view.getTxtPassword().setText("");
        view.getTxtConfirmPassword().setText("");
    }
}
