package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.Book;

public class BookService {

    private Connection conn;

    public BookService() {

        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: Could not establish connection in BookService.");
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM reserva";

        try (
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                LocalDateTime dateDB = rs.getObject("fecha_reserva", LocalDateTime.class);
                list.add(
                    new Book(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_mesa"),
                        dateDB
                    )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void makeReservation(int userId, int tableId, LocalDateTime date) {
        if (isUserAlreadyBooked(userId, tableId)) {
            throw new IllegalArgumentException("Operation rejected. You are already registered for this specific table or event allocation.");
        }

        int currentAttendance = countReservationsByTable(tableId);
        int maxCapacity = getCapacityLimit(tableId);

        if (currentAttendance >= maxCapacity) {
            throw new IllegalArgumentException("Registration failed. The selected assignment has reached its absolute maximum attendance capacity limit (" + maxCapacity + ").");
        }
    	
    	String sql ="INSERT INTO reserva (id_usuario, id_mesa, fecha_reserva) VALUES (?, ?, ?)";
    	
        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, userId);
            st.setInt(2, tableId);
            st.setObject(3, date);

            st.executeUpdate();

            System.out.println("LOG Reserva_Service -> Reserva realizada");

        } catch (SQLException e) {
            System.err.println(
                "Error al insertar reserva: " + e.getMessage()
            );
        }
    }

    public int countReservationsByTable(int tableId) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE id_mesa = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCapacityLimit(int tableId) {
        String sql = "SELECT capacidad FROM mesa WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("capacidad");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 4; 
    }

    public boolean isUserAlreadyBooked(int userId, int tableId) {
        String sql = "SELECT 1 FROM reserva WHERE id_usuario = ? AND id_mesa = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, tableId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}