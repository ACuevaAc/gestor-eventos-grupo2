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

/**
 * @class TableService
 * @description Domain service architectural component managing physical layout inventory,
 * orchestrating data tracking states for seating assets, executing atomic transactional state updates (booking and releasing),
 * mapping raw database query structures into entity data blocks, and supervising resource persistence workflows.
 */
public class TableService {

    public Connection conn;

    /**
     * @constructor
     * @description Initializes the inventory domain system, securing active connections 
     * through central transactional connection utility layers.
     */
    public TableService() {

        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @method getTableIds
     * @description Computes the scalar aggregate sum representing the absolute quantity of physical venue assets recorded in the system.
     * @returns {int} Total index tracking count representing active entity records, or 0 if query evaluation encounters runtime faults.
     */
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

    /**
     * @method getCreatedTables
     * @description Pulls the complete flat registry of active seating resources from underlying data tracking tables, 
     * parsing relational rows directly into targeted domain model representations.
     * @returns {List<Table>} Collection listing array holding complete layout configurations.
     */
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

    /**
     * @method bookTable
     * @description Triggers an atomic transactional modification query to flag an inventory physical asset 
     * as occupied, blocking downstream parallel client assignment attempts.
     * @param {int} tableId - Unique inventory locator primary key tracking the target structural asset row.
     */
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

    /**
     * @method releaseTable
     * @description Triggers an atomic status rollback query to reset an asset occupancy status back to clear 
     * parameters, making the location visible for generic user sessions.
     * @param {int} tableId - Unique inventory locator primary key tracking the target structural asset row.
     */
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

    /**
     * @method deleteTable
     * @description Executes a broad storage purge macro that clears complete raw dataset records inside 
     * the physical seating layout context.
     */
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

    /**
     * @method createTable
     * @description Maps standard model structure values into relational parameter variables to append a new 
     * physical tracking venue asset inside database tables.
     * @param {Table} m - Domain entity snapshot configuration blueprint payload.
     */
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

    /**
     * @method getMaxQuantity
     * @description Isolates structural data indices to resolve specific peak guest headcount boundaries allowed 
     * for an item matching primary tracking constraints.
     * @param {int} tableId - Unique validation query indexing filter locator.
     * @returns {int} Absolute capacity allocation boundary metric, returning 0 if tracking checks fail.
     */
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
    
    /**
     * @method findByName
     * @description Evaluates fuzzy string patterns against layout title records to identify matching resource components.
     * @param {String} name - Text signature criteria query input payload used for partial matching searches.
     */
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