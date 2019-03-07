package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static ConnectionFactory cf = new ConnectionFactory();
	private ConnectionFactory() {
		super();
	}
	public static ConnectionFactory getInstance() {
		return cf;
	}
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:\\Users\\cshan\\Project-1-Omar-and-Chris\\dev\\src\\main\\resources\\application.properties"));

			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pw"));
			
		} catch (SQLException sqle) {
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			
		}
		return conn;
	}
}