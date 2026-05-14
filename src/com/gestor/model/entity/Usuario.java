package com.gestor.model.entity;

public class Usuario {
	private int idUsuario;
	private String nombreUsuario,emailUsuario,pswUsuario,rolUsuario;
	private int edad;
	
	public Usuario(int idUser,String nom,String email,int edadUser,String psw,String rol) {
		this.idUsuario=idUser;
		this.nombreUsuario=nom;
		this.emailUsuario=email;
		this.edad=edadUser;
		this.pswUsuario=psw;
		this.rolUsuario=rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getPswUsuario() {
		return pswUsuario;
	}

	public void setPswUsuario(String pswUsuario) {
		this.pswUsuario = pswUsuario;
	}

	public String getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	

}
