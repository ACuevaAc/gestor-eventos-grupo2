package com.gestor.model.entity;

public class Pedido {
	private int idPedido,idMesa;
	
	public Pedido(int idPed,int idMe) {
		this.idPedido=idPed;
		this.idMesa=idMe;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	

}
