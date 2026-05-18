package com.gestor.model.entity;

import java.sql.Date;

public class Mesa {
	
	private int id,num_max;
	private String nombre;
	private boolean mesa_Reservada;
	
	public Mesa(int id,int num_max,String nom,boolean reservada) {
		this.id=id;
		this.num_max=num_max;
		this.nombre=nom;
		this.mesa_Reservada=reservada;
	}
	public Mesa() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum_max() {
		return num_max;
	}

	public void setNum_max(int num_max) {
		this.num_max = num_max;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isMesa_Reservada() {
		return mesa_Reservada;
	}

	public void setMesa_Reservada(boolean mesa_Reservada) {
		this.mesa_Reservada = mesa_Reservada;
	}

}
