package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.Table;

public class TableService {

    public Connection conn;

    public TableService() {

        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTableIds() {
        String sql = "SELECT COUNT(*) FROM mesa";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Table> getCreatedTables() {
        List<Table> list = new ArrayList<>();

        String sql = "SELECT * FROM mesa";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(
                    new Table(
                        rs.getInt("id"),
                        rs.getInt("numero_max"),
                        rs.getString("nombre"),
                        rs.getBoolean("reservado")
                    )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void bookTable (int tableId) {
        String sql = "UPDATE mesa SET reservado = true WHERE id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, tableId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseTable(int tableId) {
        String sql = "UPDATE mesa SET reservado = false WHERE id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, tableId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        String sql = "DELETE FROM mesa";

        try {
            Statement st = conn.createStatement();

            int filasBorradas = st.executeUpdate(sql);
            System.out.println("LOG MesaService: mesas borradas -> " + filasBorradas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(Table m) {
        String sql = "INSERT INTO mesa (numero_max, nombre, reservado) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, m.getMax());
            ps.setString(2, m.getName());
            ps.setBoolean(3, m.isBooked());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getMaxQuantity(int tableId) {
  
   	    String sql = "SELECT numero_max FROM mesa WHERE id = ?";
    	    try {
    	        PreparedStatement ps = conn.prepareStatement(sql);
    	        ps.setInt(1, tableId);
    	        ResultSet rs = ps.executeQuery();

    	        if (rs.next()) {
    	            return rs.getInt("numero_max");
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return 0;
    
	}
    
    public void findByName(String name) {
    	String sql = "SELECT * FROM mesa WHERE nombre Like '?%'";
    	try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}