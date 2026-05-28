package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.SummaryBook;

public class SummaryService {
	private Connection conn;
	
	public SummaryService() {
		try {
			conn=DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public List<SummaryBook> getAllSummaryBooks() {
        List<SummaryBook>list = new ArrayList<>();
        String sql = "SELECT r.id AS id_reserva, m.nombre AS nombre_mesa, u.nombre AS nombre_usuario, r.fecha_reserva " +
                     "FROM reserva r, mesa m, usuario u " +
                     "WHERE r.id_mesa = m.id AND r.id_usuario = u.id";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                java.sql.Timestamp timestamp = rs.getTimestamp("fecha_reserva");
                LocalDateTime reservationDate = (timestamp != null) ? timestamp.toLocalDateTime() : null;

                list.add(new SummaryBook(
                    rs.getInt("id_reserva"),
                    rs.getString("nombre_mesa"),
                    rs.getString("nombre_usuario"),
                    reservationDate
                ));

                
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
