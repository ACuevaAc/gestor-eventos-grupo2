package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.config.ConexionDB;
import com.gestor.model.entity.User;

public class usuarioService {

    private Connection con;

    public usuarioService() {

        try {

            con = ConexionDB.obtener();

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }

    public boolean existeEmail(String email) {

        String sql = "SELECT * FROM usuario WHERE email=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    public boolean registrar(User usuario) {

        if (existeEmail(usuario.getEmail())) {

            return false;
        }

        String sql =
                "INSERT INTO usuario(nombre,email,edad,password,rol) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getAge());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getRole());

            ps.executeUpdate();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    public User login(String email, String psw) {

        String sql =
                "SELECT * FROM usuario WHERE email=? AND password=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            String contra = SecurityService.hashString(psw);

            ps.setString(2, contra);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User u = new User();

                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setAge(rs.getInt("edad"));
                u.setRole(rs.getString("rol"));

                return u;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return null;
    }

    public int getId(String mail) {

        String sql = "SELECT id FROM usuario WHERE email=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, mail);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt("id");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return -1;
    }
}