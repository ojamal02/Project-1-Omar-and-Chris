package com.revature.models;

public class Principal {

    private String user_id;
    private String role_id;

    public Principal() {
        super();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Principal [id=" + user_id + ", role=" + role_id + "]";
    }

}
