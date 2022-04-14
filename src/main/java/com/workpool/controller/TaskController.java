package com.workpool.controller;


import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.JOptionPane;

import com.workpool.database.Connect;
import com.workpool.entity.Task;
import com.workpool.enums.Priority;
import com.workpool.enums.Status;

public class TaskController {

	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public ArrayList<String> validateTask(Task task){
		
		ArrayList<String> errorMessageList = new ArrayList<>();
		
		//validating Task id
		int id = task.getId();
		String query = "SELECT ID FROM TASK WHERE ID ="+id;
		try {
			 st = Connect.getConnection().createStatement();
			  rs = st.executeQuery(query);
			  if(!rs.next()) {
				  
						errorMessageList.add("Task ID does not exist");
						System.out.println("Task ID does not exist");
			}
		}
		
			catch(SQLException e) {
				System.out.println(e);
			}
		
		/*
		

		//Validating title
		String title = task.getTitle();
		
		if (title == null || title.trim().isEmpty())   {
			
		errorMessageList.add("Title can't be null");
		 System.out.println("Title can't be null");
		}
		
		
		//validating entry ID (it must exist on entry table)
		int id = task.getEntry_id().getId();
		String query = "SELECT ID FROM ENTRY WHERE ID ="+id;
			try {
				st = Connect.getConnection().createStatement();
				 rs = st.executeQuery(query);
				if(!rs.next()) {
						  
				errorMessageList.add("Entry ID does not exist on entry table");
				System.out.println("Entry ID does not exist on entry table");
		}
  }catch(SQLException e) {
		System.out.println(e);
}
				
	 //validating task type (it must exist on type table)
	int type = task.getTask_type().getId();
	String sql = "SELECT ID FROM TYPE WHERE ID ="+type;
		try {
			st = Connect.getConnection().createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()) {
						  
				errorMessageList.add("Task type  does not exist on type table");
				System.out.println("Task type does not exist on type table");
		}
	}catch(SQLException e) {
		System.out.println(e);
	}
 
		//Validatting created on date
		
		
		
		//validate due date
		
		
		
		 //validating owner id (it must exist on Resource table)
		int owner = task.getOwner_id().getId();
		String sqlstate = "SELECT ID FROM RESOURCE WHERE ID ="+owner;
			try {
				st = Connect.getConnection().createStatement();
				rs = st.executeQuery(sqlstate);
				if(!rs.next()) {
							  
					errorMessageList.add("Owner ID does not exist on Resource table");
					System.out.println("Owner ID does not exist on Resource table");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
			
			 //validating Resource ID (assigned to) -->> (it must exist on Resource table)
			int assignedTo = task.getResource_id().getId();
			String statement = "SELECT ID FROM RESOURCE WHERE ID ="+assignedTo;
				try {
					st = Connect.getConnection().createStatement();
					rs = st.executeQuery(statement);
					if(!rs.next()) {
								  
						errorMessageList.add("Assign task to an existing resource");
						System.out.println("Assign task to an existing resource");
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
		
		
		//Validate Status enum
		Status status =  task.getStatus();				
		if(status != Status.statusId(1) && status != Status.statusId(2) && status != Status.statusId(3)) {
		 errorMessageList.add("Status must be 1 , 2 or 3");
	   System.out.println("Status must be 1 , 2 or 3");
	}
	
	// Validating Priority enum

	Priority priority = task.getPiority();
	if(priority != Priority.priorityId(1) && priority != Priority.priorityId(2)  && priority != Priority.priorityId(3)  && priority != Priority.priorityId(4) ) {
		 errorMessageList.add("Priority must be 1 , 2 , 3 or 4");
		 System.out.println("Priority must be 1 , 2 , 3 or 4");
	}
	*/
		return errorMessageList;
	}
	
	
	
	
	//CRUD
	
	public void createTask(Task task) {
		try {
			ps = Connect.getConnection().prepareStatement("INSERT INTO TASK (title, description, entry_id, task_type, owner, resource_id, status, priority) values (?,?,?,?,?,?,?,?)");
			
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setInt(3, task.getEntry_id().getId());
			ps.setInt(4, task.getTask_type().getId());
			ps.setDate(5, new Date(task.getCreated_on().getTimeInMillis()));
			ps.setDate(6, new Date(task.getDue_date().getTimeInMillis()));
			ps.setInt(5, task.getOwner_id().getId());
			ps.setInt(6, task.getResource_id().getId());
			
			ps.setInt(7, task.getStatus().toInt());
			ps.setInt(8, task.getPiority().toInt());
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "Task created",  "MESSAGE" , i);
			}
			Connect.getConnection().close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
public ArrayList<Task> readAll() throws  Exception{
		EntryController entryController = new EntryController();
		TaskTypeController typeController = new TaskTypeController();
		ResourceController resourceController = new ResourceController();
		
		ArrayList<Task>  taskList = new ArrayList<>();
	
			
		   st = Connect.getConnection().createStatement();
		   String Query = "SELECT * FROM TASK";
		  rs = st.executeQuery(Query);
		while(rs.next()) {
			//Getting DOB from database then converting it to calendar
			Date created = rs.getDate("created_on");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(created.getTime());
			
			
			Date due = rs.getDate("due_date");
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(due.getTime());
			
			Task task = new Task();
			
			task.setId(rs.getInt("id"));
			task.setTitle(rs.getString("title"));
			task.setDescription(rs.getString("description"));
			task.setEntry_id(entryController.readEntryById(rs.getInt("entry_id")));
			task.setTask_type(typeController.readTaskTypeById(rs.getInt("task_type")));
			task.setCreated_on(calender);
			task.setDue_date(cal);
			task.setOwner_id(resourceController.readResourceById(rs.getInt("owner")));
			task.setResource_id(resourceController.readResourceById(rs.getInt("resource_id")));
			task.setStatus(Status.statusId(rs.getInt("status")));
			task.setPiority(Priority.priorityId(rs.getInt("priority")));
			
			taskList.add(task);
			
			
		}
		System.out.println(taskList.toString());
return taskList;
	}
	
	

	public Task readTaskById(int id) {
		EntryController entryController = new EntryController();
		TaskTypeController typeController = new TaskTypeController();
		ResourceController resourceController = new ResourceController();
		
		Task task = new Task();
		
		ArrayList<Task> taskList = new ArrayList<>();
		
		String Query = "SELECT * FROM task WHERE ID ="+id;
		try {
			
			 ps = Connect.getConnection().prepareStatement(Query);
			rs = ps.executeQuery();
			
		while(rs.next()) {
			//Getting DOB from database then converting it to calendar
			Date created = rs.getDate("created_on");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(created.getTime());
			
			
			Date due = rs.getDate("due_date");
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(due.getTime());
			
			task.setId(rs.getInt("id"));
			task.setTitle(rs.getString("title"));
			task.setDescription(rs.getString("description"));
			task.setEntry_id(entryController.readEntryById(rs.getInt("entry_id")));
			task.setTask_type(typeController.readTaskTypeById(rs.getInt("task_type")));
			task.setCreated_on(calender);
			task.setDue_date(cal);
			task.setOwner_id(resourceController.readResourceById(rs.getInt("owner")));
			task.setResource_id(resourceController.readResourceById(rs.getInt("resource_id")));
			task.setStatus(Status.statusId(rs.getInt("status")));
			task.setPiority(Priority.priorityId(rs.getInt("priority")));
		  
			taskList.add(task);
		}
		st.close();
		//Connect.getConnection().close();
		}
		
		
		catch (Exception e) {
             System.out.println(e);
		}
		//System.out.println(task.toString());
		return task;
	}

	//update 
	
	public void updateTask(Task task) {
		
		
		 String Query = "UPDATE TASK SET task_type = 1 where ID = "+task.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Task updated ", "MESSAGE",  i);
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
	public void deleteTask(Task task) {
		 
	
		 String Query = " DELETE FROM TASK WHERE ID = "+task.getId();
			try {
				
			     ps = Connect.getConnection().prepareStatement(Query);
			    int i = ps.executeUpdate();
			    if(i > 0) {
			    	 JOptionPane.showMessageDialog(null, "Task deleted ", "MESSAGE",  i);
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
