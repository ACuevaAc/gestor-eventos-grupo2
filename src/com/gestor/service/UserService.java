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

/**
 * @class UserService
 * @description Domain service architectural component managing user profile lifecycles and authentication ecosystems,
 * handling credentials verification checkpoints, transactional identity registrations, primitive field validation rules,
 * pattern matching lookups, and secure data entity extraction.
 */
public class UserService {

    private Connection con;

    /**
     * @constructor
     * @description Initializes the user identity engine, fetching active data resource connections 
     * through shared infrastructure connection factories.
     */
    public UserService() {

        try {
            con = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * @method getListByName
     * @description Queries persistent profiles using parameterized pattern criteria matching to look up emails, 
     * returning structural account snapshots containing credentials and authorization properties.
     * @param {String} name - Text pattern value used for partial address matching criteria filter mappings.
     * @returns {List<User>} Collection listing array matching target credentials projections, or null if database processing fails.
     */
    public List<User> getListByName(String name) {
    	List<User> list=new ArrayList<>();
    	String sql="SELECT email,password,rol FROM usuario WHERE email LIKE ?";
    	try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new User(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("rol")
						));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * @method getListUsers
     * @description Generates a generic index statement tracking down complete structural credential payloads 
     * from database accounts metadata indices.
     * @returns {List<User>} Complete flat directory map containing standard authorization models, or null if the resource connection breaks.
     */
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

    /**
     * @method deleteFromEmail
     * @description Executes isolated target structural data purge macros using atomic filtering keys to erase users 
     * matching unique administrative contact indices.
     * @param {String} email - Unique messaging communication index assigned to accounts scheduled for physical layout removal.
     */
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

    /**
     * @method existsEmail
     * @description Checks structural tracking indices to identify pre-existing unique contact addresses, 
     * blocking sequence collisions during parallel creation registrations.
     * @param {String} email - Target validation string parameter locator.
     * @returns {boolean} True if matching coordinates yield data records, false otherwise.
     */
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

    /**
     * @method register
     * @description Verifies user structure states against demographic age constraints and duplicate profile limits, 
     * mapping property sets into target insertion schemas before persisting components.
     * @param {User} user - Domain entity snapshot payload holding complete sign-up data definitions.
     * @returns {boolean} True if insertion operations complete successfully, false upon context exceptions.
     * @throws {IllegalArgumentException} Thrown if contact credentials mismatch structural configurations, duplicate signatures exist, or age inputs drop under legal boundaries.
     */
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

    /**
     * @method login
     * @description Orchestrates the session validation checkpoint lifecycle, processing passing text tokens into 
     * cryptographic hash targets to verify database matches and fetch authorization profiles.
     * @param {String} email - Target structural identification mapping property criteria.
     * @param {String} password - Raw entry passcode token processed using core security hash algorithms.
     * @returns {User} Complete contextual user entity configuration wrapper if tokens align, or null upon credential collision errors.
     */
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

    /**
     * @method getId
     * @description Locates structural database matrices to pull unique primary sequence identifiers 
     * linked to specific user contact properties.
     * @param {String} mail - Absolute target identifier text query criteria input.
     * @returns {int} Non-negative primary sequential reference record key, or -1 if mappings are empty or fault.
     */
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