package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.User;

public class UserService {

    private Connection con;

    public UserService() {

        try {
            con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<User> getListUsers() {
    	List<User> lista=new ArrayList<>();
    	String sql="SELECT email,password,rol FROM usuario";
    	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next()) {
				lista.add(new User(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("rol")
						));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    public void deleteFromEmail(String email) {
    	String sql="DELETE FROM usuario WHERE email=?";
    	try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean existsEmail(String email) {
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

    public boolean register(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User details and email cannot be empty.");
        }

        if (existsEmail(user.getEmail())) {
            throw new IllegalArgumentException("The email address is already registered in the system.");
        }

        if (user.getAge() < 18) {
            throw new IllegalArgumentException("Access denied. Users must be at least 18 years old.");
        }


        String sql = "INSERT INTO usuario(nombre,email,edad,password,rol) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public User login(String email, String password) {

        String sql = "SELECT * FROM usuario WHERE email=? AND password=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            String hash = SecurityService.hashString(password);
            ps.setString(2, hash);

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