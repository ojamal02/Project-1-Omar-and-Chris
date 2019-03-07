package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UsersDAO;
import com.revature.models.User;

public class UserService {

    private static Logger log = Logger.getLogger(UserService.class);

    private UsersDAO UsersDAO = new UsersDAO();

    public List<User> getAllUsers() {
        return UsersDAO.getAll();
    }

    public User getById(int userId) {
        return UsersDAO.getById(userId);
    }

//    public User getUserByUsername(String username) {
//        return UsersDAO.getByUsername(username);
//    }

    public User getUserByCredentials(String username, String password) {

        User user = null;

        // Verify that neither of the credentials are empty string
        if (!username.equals("") && !password.equals("")) {
            user = UsersDAO.getByCredentials(username, password);
            return user;
        }

        log.info("Empty username and/or password value(s) provided");
        return null;
    }

    public User addUser(User newUser) {

        // Verify that there are no empty fields
        if (newUser.getUsername().equals("") || newUser.getPassword().equals("") || newUser.getFirstName().equals("")
                || newUser.getLastName().equals("")) {
            log.info("New user object is missing required fields");
            return null;
        }

        return UsersDAO.add(newUser);
    }

    public User updateUser(User updatedUser) {

        // Verify that there are no empty fields
        if (updatedUser.getUsername().equals("") || updatedUser.getPassword().equals("")
                || updatedUser.getFirstName().equals("") || updatedUser.getLastName().equals("")) {
            log.info("Updated user object is missing required fields");
            return null;
        }

        // Attempt to persist the user to the dataset
        User persistedUser = UsersDAO.update(updatedUser);


        // If the update attempt was successful, update the currentUser of AppState, and return the updatedUser
        if (persistedUser != null) {
            return updatedUser;
        }

        // If the update attempt was unsuccessful, return null
        log.warn("User update failed");
        return null;

    }

    public boolean deleteUser(int userId) {
        return UsersDAO.delete(userId);
    }
}
