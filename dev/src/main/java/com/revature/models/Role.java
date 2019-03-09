package com.revature.models;

import java.util.Objects;

public class Role {

    private int role_id;
    private String role;
    
    public Role() {
    	super();
    }
    
    public Role(int role_id) {
        this.role_id = role_id;
        
        switch(role_id) {
		case 1:
			this.role = "MANAGER"; break;
		case 2:
		 	this.role = "EMPLOYEE"; break;
		case 3:
			this.role = "LOCKED"; break;
		default:
			this.role = "LOCKED";
		}
    }
    
    public Role(String roleName) {
		super();
		this.role = roleName;
		
		switch(roleName) {
		case "MANAGER":
			this.role_id = 1; break;
		case "EMPLOYEE":
			this.role_id = 2; break;
		case "LOCKED":
			this.role_id = 3; break;
		default:
			this.role_id = 3;
		}
	}
    

    public Role(int role_id, String role) {
		super();
		this.role_id = role_id;
		this.role = role;
	}

	public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role_id == role1.role_id &&
                role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }
}
