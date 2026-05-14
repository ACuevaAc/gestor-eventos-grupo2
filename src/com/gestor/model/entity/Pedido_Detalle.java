package com.gestor.model.entity;

public class Pedido_Detalle {
	
	private int idDetalle,idPedido,idProducto,cantidad;
	
	
	public Pedido_Detalle(int idDet,int idPed,int idPro,int cant) {
		this.idDetalle=idDet;
		this.idPedido=idPed;
		this.idProducto=idPro;
		this.cantidad=cant;
	}


	public int getIdDetalle() {
		return idDetalle;
	}


	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}


	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	

}
