package com.revature.DAO;


import java.sql.*;
import java.util.*;

import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;


public class EmployeeDAO implements DAO<Employee>{
    


	@Override
	public List<Employee> getAll() {

		ArrayList<Employee> employees = new ArrayList<>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Employees";

			PreparedStatement prepState = conn.prepareStatement(sql);

			ResultSet rs = prepState.executeQuery();

			while(rs.next()) {
				Employee emps = new Employee();
				emps.setUser_id(rs.getInt(1));
				emps.setUsername(rs.getString(2));
				emps.setPassword(rs.getString(3));
				emps.setFirstName(rs.getString(4));
				emps.setLastName(rs.getString(5));
				emps.setEmail(rs.getString(5));
				emps.setRole_id(rs.getInt(6));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getById(int id) {
		Employee Employee = new Employee();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Employees WHERE Employee_id = ?";

			PreparedStatement prepState = conn.prepareStatement(sql);

			prepState.setInt(1, id);

			ResultSet rs = prepState.executeQuery();

			while(rs.next()) {
				Employee.setUser_id(rs.getInt(1));
				Employee.setUsername(rs.getString(2));
				Employee.setPassword(rs.getString(3));
				Employee.setFirstName(rs.getString(4));
				Employee.setLastName(rs.getString(5));
				Employee.setEmail(rs.getString(5));
				Employee.setRole_id(rs.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(Employee.getUser_id() == 0) return null;

		return Employee;
	}

	@Override
	public Employee add(Employee obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee updatedObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
   
        
}