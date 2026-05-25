package com.gestor.model.entity;

public class Table {
	
	private int id;
	private int max;
	private String name;
	private boolean bookedTable;
	
	public Table (int id, int max, String name, boolean booked) {
		this.id = id;
		this.max = max;
		this.name = name;
		this.bookedTable = booked;
	}

	public Table () {}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public int getMax () {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public boolean isBooked () {
		return bookedTable;
	}

	public void setBooked(boolean booked) {
		this.bookedTable = booked;
	}

}
