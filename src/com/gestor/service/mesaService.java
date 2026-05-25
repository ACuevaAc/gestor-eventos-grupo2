package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.ConexionDB;
import com.gestor.model.entity.Table;

public class mesaService {

    public Connection conn;

    public mesaService() {

        try {

            this.conn = ConexionDB.obtener();

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }

    public int obtenerIdsMesas() {

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

    public List<Table> obtenerMesasCreadas() {

        List<Table> lista = new ArrayList<>();

        String sql = "SELECT * FROM mesa";

        try {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                lista.add(
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

        return lista;
    }

    public void reservarMesa(int idMesa) {

        String sql =
                "UPDATE mesa SET reservado = true WHERE id=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idMesa);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void liberarMesa(int idMesa) {

        String sql =
                "UPDATE mesa SET reservado = false WHERE id=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idMesa);

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

            System.out.println(
                    "LOG MesaService: mesas borradas -> "
                            + filasBorradas
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void crearMesa(Table m) {

        String sql =
                "INSERT INTO mesa (numero_max, nombre, reservado) VALUES (?, ?, ?)";

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
}