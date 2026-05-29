package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @class DatabaseConnection
 * @description Infrastructure configuration layer implementing the Creational Singleton Pattern 
 * to manage and maintain a single persistent JDBC connection pipeline to the PostgreSQL VPS instance.
 */
public class DatabaseConnection {
	
	/**
     * @private
     * @static
     * @type {Connection}
     * @description The single active database connection instance shared across the lifecycle application.
     */
	private static Connection cnx = null;
	
	/**
     * @method getConnection
     * @static
     * @description Resolves and returns the unique active database connection pipeline. 
     * Lazily initializes the connection session if it is currently null.
     * @returns {Connection} The active initialized JDBC Connection object resource mapping.
     * @throws {SQLException} If a transactional database access error occurs or connection credentials fail.
     * @throws {ClassNotFoundException} If the specified PostgreSQL JDBC Driver class blueprint cannot be located in the build path.
     */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (cnx == null) {
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

	/**
     * @method close
     * @static
     * @description Safely terminates and closes the existing static connection stream pipeline, freeing cloud VPS resources.
     * @throws {SQLException} If a state database telemetry tracking access error occurs during stream closure execution.
     */
	public static void close () throws SQLException {
		if (cnx != null) cnx.close();
	}
}

