package com.gestor.model.entity;

import java.time.LocalDateTime;

public class Reserva {
	private int idReserva,idUsuario,idMesa,fecha;
	private LocalDateTime fechaReserva;
	
	public Reserva(int idReserva,int idUsuario,int idMesa) {
		this.idReserva=idReserva;
		this.idUsuario=idUsuario;
		this.idMesa=idMesa;
		fechaReserva=LocalDateTime.now();
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	
}
