package com.gestor.model.entity;

import java.time.LocalDateTime;

public class Reserva {
    private int idReserva;
    private int idUsuario;
    private int idMesa;
    private LocalDateTime fechaReserva; 
    
    
    // Este constructor sobra
    public Reserva(int idReserva, int idUsuario, int idMesa) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.fechaReserva = LocalDateTime.now(); 
    }


    public Reserva(int idReserva, int idUsuario, int idMesa, LocalDateTime fechaReserva) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.fechaReserva = fechaReserva;
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

    public LocalDateTime getFechaReserva() {
    	return fechaReserva; 
    	}
    public void setFechaReserva(LocalDateTime fechaReserva) {
    	this.fechaReserva = fechaReserva; 
    	}
}
