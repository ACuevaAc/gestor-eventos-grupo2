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
import com.gestor.model.entity.Reserva;

public class Reserva_Service {

    private Connection conn;

    public Reserva_Service() {

        try {

            this.conn = ConexionDB.obtener();

        } catch (ClassNotFoundException | SQLException e) {

            System.err.println("Error: Could not establish connection in Reserva_Service.");
            e.printStackTrace();
        }
    }

    public List<Reserva> getReservas() {

        List<Reserva> lista = new ArrayList<>();

        String sql = "SELECT * FROM reserva";

        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                LocalDateTime fechaBD =
                        rs.getObject("fecha_reserva", LocalDateTime.class);

                lista.add(
                        new Reserva(
                                rs.getInt("id"),
                                rs.getInt("id_usuario"),
                                rs.getInt("id_mesa"),
                                fechaBD
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    public void realizarReserva(
            int idUser,
            int idTable,
            LocalDateTime date
    ) {

        String sql =
                "INSERT INTO reserva (id_usuario, id_mesa, fecha_reserva) VALUES (?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idUser);
            st.setInt(2, idTable);
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