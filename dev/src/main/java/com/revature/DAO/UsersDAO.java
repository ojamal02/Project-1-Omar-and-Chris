package com.revature.DAO;


import java.sql.*;
import java.util.*;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;
import org.apache.log4j.Logger;


public class UsersDAO implements DAO<User>{
    
	private static Logger log = Logger.getLogger(UsersDAO.class);

	@Override
	public List<User> getAll() {
		User User = new User();
		ArrayList<User> users = new ArrayList<>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_Users";

			PreparedStatement prepState = conn.prepareStatement(sql);

			ResultSet rs = prepState.executeQuery();

			while(rs.next()) {

				User.setUser_id(rs.getInt("ers_users_id"));
				User.setUsername(rs.getString("ers_username"));
				User.setPassword(rs.getString("ers_password"));
				User.setFirstName(rs.getString("user_first_name"));
				User.setLastName(rs.getString("user_last_name"));
				User.setEmail(rs.getString("user_email"));
				User.setRole_id(rs.getInt("user_role_id"));
				users.add(User);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return users;
	}

	@Override
	public User getById(int id) {
		User User = new User();
		ArrayList<User> users = new ArrayList<>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Users WHERE User_id = ?";

			PreparedStatement prepState = conn.prepareStatement(sql);

			prepState.setInt(1, id);

			ResultSet rs = prepState.executeQuery();

			while(rs.next()) {
				User.setUser_id(rs.getInt("ers_users_id"));
				User.setUsername(rs.getString("ers_username"));
				User.setPassword(rs.getString("ers_password"));
				User.setFirstName(rs.getString("user_first_name"));
				User.setLastName(rs.getString("user_last_name"));
				User.setEmail(rs.getString("user_email"));
				User.setRole_id(rs.getInt("user_role_id"));
				users.add(User);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(User.getUser_id() == 0) return null;

		return User;
	}

	public User getByCredentials(String username, String password) {
		User user = new User();
		//User users = null;

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM chrisomar.ers_users JOIN chrisomar.ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_username = ? AND ers_password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				user.setUser_id(rs.getInt("ers_users_id"));
				user.setUsername(rs.getString("ers_username"));
				user.setPassword(rs.getString("ers_password"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_email"));
				user.setRole_id(rs.getInt("user_role_id"));
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		
		System.out.println(user);
		return user;
	}

	@Override
	public User add(User newUser) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO chrisomar.ers_users VALUES (0, ?, ?, ?, ?, ?, 1)", new String[] {"ers_users_id"});
			
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstName());
			pstmt.setString(4, newUser.getLastName());
			pstmt.setString(5, newUser.getEmail());
			

			if(pstmt.executeUpdate() != 0) {

				// Retrieve the generated primary key for the newly added user
				ResultSet rs = pstmt.getGeneratedKeys();


				while(rs.next()) {
					newUser.setUser_id(rs.getInt(1));
					newUser.setRole_id(new Role(3));
				}

				conn.commit();

			}

		} catch (SQLIntegrityConstraintViolationException sicve) {
			log.error(sicve.getMessage());
			log.warn("Username already taken.");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return newUser;
	}

	@Override
	public User update(User updatedUser) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "UPDATE ers_users SET ers_username = ?, ers_password = ?, user_first_name = ?, user_last_name = ?, user_email = ?, ers_user_role_id = ? WHERE ers_users_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedUser.getUsername());
			pstmt.setString(2, updatedUser.getPassword());
			pstmt.setString(3, updatedUser.getFirstName());
			pstmt.setString(4, updatedUser.getLastName());
			pstmt.setString(5, updatedUser.getEmail());
			pstmt.setInt(5, updatedUser.getRole_id());

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated != 0) {
				conn.commit();
				return updatedUser;
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return null;
	}

	@Override
	public boolean delete(int user_id) {

		User User = new User();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ers_users WHERE ers_users_id = ?");
			pstmt.setInt(1, user_id);

			if (pstmt.executeUpdate() == 0) return false;

		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return true;
	}
   
        
}