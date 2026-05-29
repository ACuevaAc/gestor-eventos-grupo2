package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.UserService;
import com.gestor.view.LoginView;
import com.gestor.view.SignupView;

/**
 * @class RegisterController
 * @description Controller architectural component managing the profile enrollment workflow pipeline,
 * handling credential verification matching, entity synchronization, password encryption hooks,
 * and form field clearing routines.
 */
public class RegisterController {
	
	private UserService uService;
	private SignupView view;
	private LoginController cont;

	/**
     * @constructor
     * @description Initializes the profile registration sub-system wizard context and maps actions to layout triggers.
     * @param {SignupView} v - The profile creation input field collection view container.
     * @param {LoginController} loginController - Standard gateway authentication reference context.
     * @param {UserService} uService - Core business service handling persistence identity rules.
     */
	public RegisterController(SignupView v, LoginController loginController, UserService uService) {
		this.view=v;
		this.cont=loginController;
		this.uService=uService;
		
		view.getBtnCreate().addActionListener(e-> registrar());
		view.getBtnBack().addActionListener(e-> back());
	}

	/**
     * @method back
     * @description Destroys the active signup registration panel to revert standard navigation visibility 
     * streams back to the primary authentication layout context.
     */
	public void back() {
		view.dispose();
		LoginView v=new LoginView();
		v.setVisible(true);
		new LoginController(v,uService);
	}

	/**
     * @method validation
     * @description Evaluates interface input syntax rules, constraints criteria, and identity payload parity matching.
     * @returns {boolean} True if structural syntactic constraints and criteria matches evaluate correctly, false otherwise.
     * @throws {NumberFormatException} Implicitly thrown if parsing metrics for age input strings violate primitive integer layouts.
     */
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

	/**
     * @method registrar
     * @description Extracts real-time structural fields from input text boxes, binds payloads to model structures, 
     * applies data hashing security encryption layers, and pushes entity states to registration pipelines.
     * @throws {IllegalArgumentException} Implicitly thrown via service layers if business rules (uniqueness, age) fail verification checks.
     */
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

	/**
     * @method clear
     * @description Flushes text content states across internal layout text input elements, restoring field environments.
     * @param {Container} cont - Root layout view panel tracking graphics hierarchy structures.
     */
	public void clear (Container cont) {
		view.getTxtName().setText("");
		view.getTxtAge().setText("");
		view.getTxtEmail().setText("");
		view.getTxtConfirmEmail().setText("");
		view.getTxtPassword().setText("");
		view.getTxtConfirmPassword().setText("");
	}

}
