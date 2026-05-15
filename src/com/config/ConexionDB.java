package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	private static Connection cnx=null;
	
	public static Connection obtener() throws SQLException, ClassNotFoundException {
		if(cnx==null) {
			try {
				Class.forName("org.postgresql.Driver");
				cnx=DriverManager.getConnection("jdbc:postgresql://5.78.178.225:5432/gegdb","adm","l9nMñ44.aa1.j0s3mA");
			} catch (SQLException ex) {
				throw new SQLException(ex);
			} catch (ClassNotFoundException ex) {
				throw new ClassCastException(ex.getMessage());
			}
		}
		return cnx;
	}
	public static void cerrar() throws SQLException {
		if(cnx!=null) {
			cnx.close();
		}
	}
}

