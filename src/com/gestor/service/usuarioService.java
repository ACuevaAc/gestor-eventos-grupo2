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
			
		}
	}
	public boolean registrar(Usuario usuario) {
		String sql="INSERT INTO usuarios(nombre,email,password) VALUES (?,?,?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getEmailUsuario());
			ps.setString(3, usuario.getPswUsuario());
			ps.setString(4, usuario.getRolUsuario());
			
			ps.executeUpdate();
			return true;
		} catch( Exception ex) {
			
		}
		
		return false;
		
	}
	public Usuario login(String email,String psw) {
		String sql="SELECT * FROM usuarios WHERE email=? AND password=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, psw);
			
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
