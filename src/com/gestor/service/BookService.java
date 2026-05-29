package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.Book;

/**
 * @class BookService
 * @description Domain service architectural component orchestrating reservation management business logic,
 * handling relational persistence connections, input metric verification constraints, inventory cap allocations, 
 * data mapping layers, and cascade deletion sequences.
 */
public class BookService {

	private Connection conn;

	/**
     * @constructor
     * @description Initializes the reservation domain engine by establishing active connections 
     * through shared infrastructure connection management components.
     */
	public BookService() {

		try {
			this.conn = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Error: Could not establish connection in BookService.");
			e.printStackTrace();
		}
	}

	/**
     * @method getBooks
     * @description Queries and pulls all global structural records from the tracking reservation schema, 
     * mapping data tuple inputs directly into domain entity objects.
     * @returns {List<Book>} Complete collection listing of verified system booking tracking entities.
     */
	public List<Book> getBooks() {
		List<Book> list = new ArrayList<>();

		String sql = "SELECT * FROM reserva";

		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				LocalDateTime dateDB = rs.getObject("fecha_reserva", LocalDateTime.class);
				list.add(new Book(rs.getInt("id"), rs.getInt("id_usuario"), rs.getInt("id_mesa"), dateDB));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
     * @method makeReservation
     * @description Processes execution logic to provision venue asset assignments. Checks for relational allocation collisions, 
     * evaluates capacity limit conditions against concurrent records, and commits insertion payloads to persistence contexts.
     * @param {int} userId - Unique identity primary key matching the customer account owner.
     * @param {int} tableId - Unique inventory tracker key matching the destination venue table asset.
     * @param {LocalDateTime} date - Targeted explicit runtime timestamp mapping for the scheduling pipeline.
     * @throws {IllegalArgumentException} Thrown if tracking parameters violate assignment isolation boundaries or if target seating cap constraints collide with active bounds.
     */
	public void makeReservation(int userId, int tableId, LocalDateTime date) {
		if (isUserAlreadyBooked(userId, tableId)) {
			throw new IllegalArgumentException(
					"Operation rejected. You are already registered for this specific table or event allocation.");
		}

		int currentAttendance = countReservationsByTable(tableId);
		int maxCapacity = getCapacityLimit(tableId);

		if (currentAttendance >= maxCapacity) {
			throw new IllegalArgumentException(
					"Registration failed. The selected assignment has reached its absolute maximum attendance capacity limit ("
							+ maxCapacity + ").");
		}

		String sql = "INSERT INTO reserva (id_usuario, id_mesa, fecha_reserva) VALUES (?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setInt(1, userId);
			st.setInt(2, tableId);
			st.setObject(3, date);

			st.executeUpdate();

			System.out.println("LOG Reserva_Service -> Reserva realizada");

		} catch (SQLException e) {
			System.err.println("Error al insertar reserva: " + e.getMessage());
		}
	}

	/**
     * @method countReservationsByTable
     * @description Queries database structures using optimized filtering keys to calculate the exact count 
     * of concurrent occupant registrations sharing a specific asset index.
     * @param {int} tableId - Relational identifier mapping targeting specific physical units.
     * @returns {int} Non-negative scalar value representing aggregate registered occupant references.
     */
	public int countReservationsByTable(int tableId) {
		String sql = "SELECT COUNT(*) FROM reserva WHERE id_mesa = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, tableId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
     * @method isUserAlreadyBooked
     * @description Evaluates identity context fields to identify pre-existing duplicate transaction collisions 
     * for a concrete user tracking reference and a physical asset node.
     * @param {int} userId - Unique identity primary key locator parameter.
     * @param {int} tableId - Inventory asset component primary key parameter.
     * @returns {boolean} True if matching index coordinates yield row records, false otherwise.
     */
	public int getCapacityLimit(int tableId) {
		String sql = "SELECT numero_max FROM mesa WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, tableId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("numero_max");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 4;
	}

	/**
     * @method isUserAlreadyBooked
     * @description Evaluates identity context fields to identify pre-existing duplicate transaction collisions 
     * for a concrete user tracking reference and a physical asset node.
     * @param {int} userId - Unique identity primary key locator parameter.
     * @param {int} tableId - Inventory asset component primary key parameter.
     * @returns {boolean} True if matching index coordinates yield row records, false otherwise.
     */
	public boolean isUserAlreadyBooked(int userId, int tableId) {
		String sql = "SELECT 1 FROM reserva WHERE id_usuario = ? AND id_mesa = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, tableId);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
     * @method deleteFromBooking
     * @description Executes isolated single-parameter cascading deletion commands across targeted relational 
     * reservation entities bound to a physical resource position.
     * @param {int} tableid - Unique identifier primary key specifying resource records scheduled for removal.
     */
	public void deleteFromBooking(int tableid) {
		String sql = "DELETE FROM RESERVA WHERE ID_MESA = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tableid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}