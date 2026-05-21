package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.ConexionDB;
import com.gestor.model.entity.Mesa;

public class mesaService {
	public Connection conn;
	
	public mesaService() {
		try {
			this.conn=ConexionDB.obtener();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int obtenerIdsMesas() {
		String sql="SELECT COUNT(*) FROM mesa";
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public void eliminarReserva(int id) {
		String sql="DELETE FROM reserva WHERE id=?";
	}
	public List<Mesa> obtenerMesasCreadas() {
		List<Mesa> lista=new ArrayList<>();
		String sql="SELECT * FROM mesa";
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next()) {
				lista.add(new Mesa(
						rs.getInt("id"),
						rs.getInt("numero_max"),
						rs.getString("nombre"),
						rs.getBoolean("reservado")
						));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return lista;
	}
	public void deleteTable() {
		String sql="DELETE FROM mesa";
		
		try {
			Statement st=conn.createStatement();
			int filasBorradas= st.executeUpdate(sql);
			System.out.println("LOG MesaService: mesas borradas -> "+filasBorradas);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void crearMesa(Mesa m) {
		String sql = "INSERT INTO mesa (numero_max, nombre, reservado) VALUES ( ?, ?, ?)";

		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, m.getNum_max());
			ps.setString(2, m.getNombre());
			ps.setBoolean(3, m.isMesa_Reservada());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
