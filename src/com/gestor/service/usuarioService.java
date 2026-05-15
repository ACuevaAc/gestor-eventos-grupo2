package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.config.ConexionDB;
import com.gestor.model.entity.Usuario;

public class usuarioService {
	private Connection con;
	
	public usuarioService() {
		try {
			con=ConexionDB.obtener();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean existeEmail(String email) {
		String sql="SELECT * FROM usuario WHERE email=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public boolean registrar(Usuario usuario) {
		
		if(existeEmail(usuario.getEmailUsuario())) {
			return false;
		}
		String sql="INSERT INTO usuario(nombre,email,edad,password,rol) VALUES (?,?,?,?,?)";
		
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getEmailUsuario());
			ps.setInt(3, usuario.getEdad());
			ps.setString(4, usuario.getPswUsuario());
			ps.setString(5, usuario.getRolUsuario());
			
			ps.executeUpdate();
			return true;
		} catch( Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}
	public Usuario login(String email,String psw) {
		String sql="SELECT * FROM usuario WHERE email=? AND password=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			String contra=SecurityService.hashString(psw);
			ps.setString(2, contra);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				Usuario u=new Usuario();
				u.setIdUsuario(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre"));
				
				return u;
			}
		} catch(Exception ex) {
			
		}
		return null;
	}

}
