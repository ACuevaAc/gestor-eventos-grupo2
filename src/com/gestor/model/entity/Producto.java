package com.gestor.model.entity;

public class Producto {
	private int idProducto;
	private String nomProducto;
	private double precioProducto;
	private byte [] imagen;
	
	public Producto(int idPro,String nomPro,double prePro,byte[] img) {
		this.idProducto=idPro;
		this.nomProducto=nomPro;
		this.precioProducto=prePro;
		this.imagen=img;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNomProducto() {
		return nomProducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	

}
