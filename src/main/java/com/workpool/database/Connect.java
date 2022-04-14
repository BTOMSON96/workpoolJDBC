package com.workpool.database;
import java.sql.*;
public class Connect {
//static variable so that it can be initialized once at start of execution
static Connection conn = null;  

//public static method -> invoke it without  creating object of this class
	public static Connection getConnection() { 
		if(conn != null) return conn; //return connection if its not null
		
		String db = "task_management";
		String username = "root";
		String password = "123456";
		
		return getConnection(db, username, password);	
	}
	//private static method of class Connection to pass database name, username and password
	private static Connection getConnection(String dbName, String username, String password) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" +dbName + "?user =" +username+ "&password=" +password);
		}
		catch (Exception e) {
			System.out.println(e);
	}
		return conn;
	} 
}
