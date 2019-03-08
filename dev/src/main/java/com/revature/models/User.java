package com.revature.models;

import java.util.Objects;

public class User {

    private int user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int role_id;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role_id=" + role_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User User = (User) o;
        return user_id == User.user_id &&
                role_id == User.role_id &&
                username.equals(User.username) &&
                password.equals(User.password) &&
                firstName.equals(User.firstName) &&
                lastName.equals(User.lastName) &&
                email.equals(User.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, password, firstName, lastName, email, role_id);
    }

	public void setRole_id(Role role) {
		// TODO Auto-generated method stub
		
	}
}
