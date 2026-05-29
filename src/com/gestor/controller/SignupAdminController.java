package com.gestor.controller;

import java.awt.Container;

import javax.swing.JOptionPane;

import com.gestor.model.entity.User;
import com.gestor.service.SecurityService;
import com.gestor.service.UserService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.signupadminView;

/**
 * @class SignupAdminController
 * @description Controller architectural component managing the administrative profile provisioning pipeline,
 * coordinating structural input synchronization, cryptographic password generation token mappings, 
 * administrative role escalation rules, and interface navigation tracking.
 */
public class SignupAdminController {
	private signupadminView view;
	private AdminController cont;
	private UserService uService;
	
	/**
     * @constructor
     * @description Initializes the administrative profile allocation execution context and hooks event listeners to layout elements.
     * @param {signupadminView} v - The administrator profile creation form panel interface container.
     * @param {AdminController} c - Main configuration dashboard coordination context reference.
     */
	public SignupAdminController(signupadminView v,AdminController c) {
		
		this.view=v;
		this.cont=c;
		this.uService=new UserService();
		
		view.getBtnCreate().addActionListener(e-> register());
		view.getBtnBack().addActionListener(e-> goBack());
	}

	/**
     * @method goBack
     * @description Terminates active authorization creation frames to restore the primary system layout dashboard views.
     */
	public void goBack() {
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}

	/**
     * @method validation
     * @description Evaluates structural administrative credentials syntax, age restriction thresholds, and confirmation parity rules.
     * @returns {boolean} True if structural syntax constraints and identity parity matches evaluate correctly, false otherwise.
     */
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

	/**
     * @method register
     * @description Orchestrates field parsing, executes interface validation loops, maps state configurations into the target profile structure, 
     * appends the secure ADMIN authentication role token, and pushes the transaction layer toward persistence pipelines.
     * @throws {IllegalArgumentException} Implicitly caught from services if underlying business validation criteria (uniqueness, syntax) reject inputs.
     */
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

	/**
     * @method clear
     * @description Resets textual component view data contexts inside administrative wizard frames.
     * @param {Container} cont - Structural component boundary parent holding active text input node branches.
     */
	public void clear(Container cont) {
		view.getTxtNombre().setText("");
		view.getTxtEdad().setText("");
		view.getTxtEmail().setText("");
		view.getTxtConfirmEmail().setText("");
		view.getTxtPassword().setText("");
		view.getTxtConfirmPassword().setText("");
	}

}
