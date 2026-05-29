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

/**
 * @class SummaryService
 * @description Domain service architectural component managing aggregated historical reports,
 * handling the extraction of de-normalized reservation projection snapshots, converting legacy SQL timestamps 
 * into modern Java chronological variables, and populating reporting structures.
 */
public class SummaryService {
	private Connection conn;
	
    /**
     * @constructor
     * @description Initializes the tracking reporting engine by fetching active relational database connections 
     * through shared infrastructure connection management wrappers.
     */
	public SummaryService() {
		try {
			conn=DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * @method getAllSummaryBooks
     * @description Executes a multi-table relational join matching operational reservation logs, physical asset details, 
     * and specific owner accounts to compile a comprehensive historical overview tracking array.
     * @returns {List<SummaryBook>} Complete data projection listing array for reporting interfaces, or null if database processing operations fail.
     */
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
