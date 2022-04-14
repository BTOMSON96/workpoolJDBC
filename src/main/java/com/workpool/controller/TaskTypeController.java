package com.workpool.controller;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.protobuf.TextFormat.ParseException;
import com.workpool.database.Connect;
import com.workpool.entity.TaskType;




public class TaskTypeController {


	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	

public ArrayList<String> validateTaskType(TaskType type) {
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//validating tasktype id
	int id = type.getId();
	String query = "SELECT ID FROM type WHERE ID ="+id;
	try {
		 st = Connect.getConnection().createStatement();
		  rs = st.executeQuery(query);
		  if(!rs.next()) {
			  
					errorMessageList.add("Type ID does not exist");
					System.out.println("Type ID does not exist");
		}
	}
	
		catch(SQLException e) {
			System.out.println(e);
		}
	
	/*
	//Validating Name
	String name = type.getName();
	
	if (name == null || name.trim().isEmpty())   {
		
	errorMessageList.add("no name specified");
	 System.out.println("no name specified");
	}
	*/
	return errorMessageList;
	
}



//CRUD
public void createTaskType(TaskType type) {
	

	
	try {
		ps = Connect.getConnection().prepareStatement("INSERT INTO TYPE (name) values (?)");
		
		ps.setString(1, type.getName());
		
		int i = ps.executeUpdate();
		
		if(i > 0) {
			JOptionPane.showMessageDialog(null, "Task type created",  "MESSAGE" , i);
		}
		Connect.getConnection().close();
	}
	catch(SQLException e) {
		System.out.println(e);
	}
	
}
//get all records from TaskType table
public ArrayList<TaskType> readAll() throws  Exception{
	
	ArrayList<TaskType>  typeList = new ArrayList<>();

		
	   st = Connect.getConnection().createStatement();
	   String Query = "SELECT * FROM TYPE";
	  rs = st.executeQuery(Query);
	while(rs.next()) {
		
		TaskType type = new TaskType(rs);
		typeList.add(type);
		
		
	}
	System.out.println(typeList.toString());
return typeList;
}


//Retrieve data from the task type table using id
public TaskType readTaskTypeById(int id)throws ParseException {
	TaskType tasktype = new TaskType();
	ArrayList<TaskType> typeList = new ArrayList<>();
	
	
	String Query = "SELECT * FROM TYPE WHERE ID ="+id;
	try {

		ps = Connect.getConnection().prepareStatement(Query);
   
		rs = ps.executeQuery();
	while(rs.next()) {
		
		tasktype = new TaskType(rs);
		typeList.add(tasktype);
	   
	}
	//st.close();
	//Connect.getConnection().close();
	}
	
	
	catch (SQLException e) {
         System.out.println(e);
	}
	//System.out.println(tasktype.toString());
	return tasktype;
}


//update 

public void updateTaskType(TaskType type) {
	
	
	 String Query = "UPDATE TYPE SET NAME = 'training' WHERE ID = "+type.getId();
	try {
		
	     ps = Connect.getConnection().prepareStatement(Query);
	    int i = ps.executeUpdate();
	    if(i > 0) {
	    	 JOptionPane.showMessageDialog(null, "Task type updated ", "MESSAGE",  i);
	    }if(i == 0) {
	    	System.out.println("No rows affected");
	    }
	    Connect.getConnection().close();
	}
	
	catch (SQLException e) {
		System.out.println(e);
	}



}

//delete 
public void deleteTaskType(TaskType type) {
	 

	 String Query = " DELETE FROM TYPE WHERE ID = "+type.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Task type deleted ", "MESSAGE",  i);
		    }if(i == 0) {
		    	System.out.println("No rows affected");
		    }
		    Connect.getConnection().close();
		}
		
		catch (SQLException e) {
			System.out.println(e);
		} 
	
}
}
