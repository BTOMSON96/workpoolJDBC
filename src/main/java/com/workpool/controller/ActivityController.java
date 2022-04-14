package com.workpool.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.workpool.database.Connect;
import com.workpool.entity.Activity;


public class ActivityController {
//static Connection con = Connect.getConnection();
	
	Statement st = null;
	ResultSet rs = null; 
	PreparedStatement ps = null;
	
	public ArrayList<String> validateActivity(Activity activity){
		ArrayList<String> errorMessageList = new ArrayList<>();
		/*
		//validating activity id
		int id = activity.getId();
		String query = "SELECT ID FROM ACTIVITY WHERE ID ="+id;
		try {
			 st = Connect.getConnection().createStatement();
			  rs = st.executeQuery(query);
			  if(!rs.next()) {
				  
						errorMessageList.add("Activity ID does not exist");
						System.out.println("Activity ID does not exist");
			}
		}
		
			catch(SQLException e) {
				System.out.println(e);
			}
		
		*/
		//Validating title
				String title = activity.getTitle();
				
				if (title == null || title.trim().isEmpty())   {
					
				errorMessageList.add("Title can't be null");
				 System.out.println("Title can't be null");
				}
				
		//validating entry ID (it must exist on entry table)
		int id = activity.getEntry_id().getId();
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
			
	//validating Task id
	int taskId = activity.getTask_id().getId();
	String sql = "SELECT ID FROM TASK WHERE ID ="+taskId;
	   try {
			st = Connect.getConnection().createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next()) {
					  
			errorMessageList.add("Task ID does not exist");
			System.out.println("Task ID does not exist");
				}
			}catch(SQLException e) {
					System.out.println(e);
			}		
		
	   //validating created on date
	   
		 //validating owner id (it must exist on Resource table)
		int owner = activity.getOwner().getId();
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

	   
		
	   
		return errorMessageList;
	}
	
	
	
	
	
	
	
	
	
	//CRUD
	
	public void createActivity(Activity activity) {
		try {
			ps = Connect.getConnection().prepareStatement("INSERT INTO ACTIVITY ( title, description, entry_id, task_id, created_on, resource_id) values (?,?,?,?,?,?)");
			
			ps.setString(1, activity.getTitle());
			ps.setString(2, activity.getDescription());
			
			ps.setInt(3, activity.getEntry_id().getId());
			ps.setInt(4, activity.getTask_id().getId());
			ps.setDate(5, new Date(activity.getCreated_on().getTimeInMillis()));
			ps.setInt(6, activity.getOwner().getId());
			
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "activity created",  "MESSAGE" , i);
			}
			Connect.getConnection().close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
public ArrayList<Activity> readAll() throws  Exception{
	EntryController entryController = new EntryController();
	ResourceController resourceController = new ResourceController();
	TaskController taskController = new TaskController();
	
		ArrayList<Activity>  activityList = new ArrayList<>();
	
			
		   st = Connect.getConnection().createStatement();
		   String Query = "SELECT * FROM ACTIVITY";
		  rs = st.executeQuery(Query);
		while(rs.next()) {
			//Getting DOB from database then converting it to calendar
			Date created = rs.getDate("created_on");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(created.getTime());
			
			Activity activity = new Activity();
			
			activity.setId(rs.getInt("id"));
			activity.setTitle(rs.getString("title"));
			activity.setDescription(rs.getString("description"));
			activity.setEntry_id(entryController.readEntryById(rs.getInt("entry_id")));
			activity.setTask_id(taskController.readTaskById(rs.getInt("task_id")));
			activity.setCreated_on(calender);
			activity.setOwner(resourceController.readResourceById(rs.getInt("resource_id")));
			
			activityList.add(activity);
			
		
		}
		System.out.println(activityList.toString());
return activityList;
	}
	
	

	public Activity readActivityById(int id) {
		EntryController entryController = new EntryController();
		ResourceController resourceController = new ResourceController();
		TaskController taskController = new TaskController();
		
		Activity activity = new Activity();
		
		String Query = "SELECT * FROM activity WHERE ID ="+id;
		try {
			
		    st = Connect.getConnection().createStatement();
		    rs = st.executeQuery(Query);
		   while(rs.next()) {
			
			 //Getting DOB from database then converting it to calendar
			Date created = rs.getDate("created_on");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(created.getTime());
			
		   activity.setId(rs.getInt("id"));
		   activity.setTitle(rs.getString("title"));
		   activity.setDescription(rs.getString("description"));
		   activity.setEntry_id(entryController.readEntryById(rs.getInt("entry_id")) );
		   activity.setTask_id(taskController.readTaskById(rs.getInt("task_id")) );
		   activity.setCreated_on(calender);
		   activity.setOwner(resourceController.readResourceById(rs.getInt("resource_id")) );
		  
		   
		}
		st.close();
		//Connect.getConnection().close();
		}
		
		
		catch (Exception e) {
             System.out.println(e);
		}
		//System.out.println(activity.toString());
		return activity;
	}

	//update 
	
	public void updateActivity(Activity activity) {
		
		
		 String Query = "UPDATE activity set title = 'Database Management: Basic skills' WHERE ID = "+activity.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Activity updated ", "MESSAGE",  i);
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
	public void deleteActivity(Activity activity) {
		 
	
		 String Query = " DELETE FROM resource WHERE ID = "+activity.getId();
			try {
				
			     ps = Connect.getConnection().prepareStatement(Query);
			    int i = ps.executeUpdate();
			    if(i > 0) {
			    	 JOptionPane.showMessageDialog(null, "Activity deleted ", "MESSAGE",  i);
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
