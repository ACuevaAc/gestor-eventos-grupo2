package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.config.ConexionDB;
import com.gestor.model.entity.Book;

public class BookService {

    private Connection conn;

    public BookService() {

        try {
            this.conn = ConexionDB.obtener();
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
}