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
import com.workpool.entity.TaskActivityLink;

public class TaskActivityLinkController {
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	
	public ArrayList<String> validateLink(TaskActivityLink link){
		ArrayList<String>  errorMessageList = new ArrayList<>();
		
		
		/*	
		//validating Link id
		int id = link.getId();
		String query = "SELECT ID FROM LINK WHERE ID ="+id;
				try {
					st = Connect.getConnection().createStatement();
					 rs = st.executeQuery(query);
					 if(!rs.next()) {
						  
						errorMessageList.add("Link ID does not exist");
						System.out.println("Link ID does not exist");
					}
				}catch(SQLException e) {
				System.out.println(e);
			}
			*/
		//validating Task id
		int taskId = link.getTask_id().getId();
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
			
		//validating activity id
		 int activityId = link.getActivity_id().getId();
		String statement = "SELECT ID FROM ACTIVITY WHERE ID ="+activityId;
				try {
					 st = Connect.getConnection().createStatement();
					 rs = st.executeQuery(statement);
					  if(!rs.next()) {
						 errorMessageList.add("Activity ID does not exist");
						System.out.println("Activity ID does not exist");
					}
				}
				
					catch(SQLException e) {
						System.out.println(e);
					}
	
		
		return errorMessageList;
	}
	
	
	
	
	//CRUD
	public void createLink(TaskActivityLink link) {
		try {
			ps = Connect.getConnection().prepareStatement("INSERT INTO LINK (task_id, activity_id, date_linked) values (?,?,?)");
			
			
		
			ps.setInt(1, link.getTask_id().getId());
			ps.setInt(2, link.getActivity_id().getId());
			
			ps.setDate(3, new Date (link.getDate_linked().getTimeInMillis()));
			
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "Task activity link created",  "MESSAGE" , i);
			}
			
			Connect.getConnection().close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
public ArrayList<TaskActivityLink> readAll() throws  Exception{
		TaskController taskController = new TaskController();
		ActivityController activityController = new ActivityController();
		
		ArrayList<TaskActivityLink>  linkList = new ArrayList<>();
	
			
		   st = Connect.getConnection().createStatement();
		   String Query = "SELECT * FROM LINK";
		  rs = st.executeQuery(Query);
		while(rs.next()) {
			
			//Getting DOB from database then converting it to calendar
			Date date = rs.getDate("date_linked");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(date.getTime());
			TaskActivityLink link = new TaskActivityLink();
			
			        link.setId(rs.getInt("id"));
			        link.setTask_id(taskController.readTaskById(rs.getInt("task_id")));
			        link.setActivity_id(activityController.readActivityById(rs.getInt("activity_id")));
			        link.setDate_linked(calender);
	
			linkList.add(link);
			
			
		}
		System.out.println(linkList.toString());
return linkList;
	}
	
	

	public TaskActivityLink readLinkById(int id) {
		TaskController taskController = new TaskController();
		ActivityController activityController = new ActivityController();
		
		TaskActivityLink link = new TaskActivityLink();
		
		String Query = "SELECT * FROM LINK WHERE ID ="+id;
		try {
			
		    st = Connect.getConnection().createStatement();
		    rs = st.executeQuery(Query);
		while(rs.next()) {
			//Getting DOB from database then converting it to calendar
			Date date = rs.getDate("date_linked");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(date.getTime());
		
			

	        link.setId(rs.getInt("id"));
	        link.setTask_id(taskController.readTaskById(rs.getInt("task_id")));
	        link.setActivity_id(activityController.readActivityById(rs.getInt("activity_id")));
		    link.setDate_linked(calender);
		   
		  
		   
		}
		st.close();
		Connect.getConnection().close();
		}
		
		
		catch (SQLException e) {
             System.out.println(e);
		}
		System.out.println(link.toString());
		return link;
	}

	//update 
	
	public void updateLink(TaskActivityLink link) {
		
		
		 String Query = "UPDATE LINK = 'pokjj' WHERE ID = "+link.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Task activity link updated ", "MESSAGE",  i);
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
	public void deleteLink(TaskActivityLink link) {
		 
	
		 String Query = " DELETE FROM LINK WHERE ID = "+link.getId();
			try {
				
			     ps = Connect.getConnection().prepareStatement(Query);
			    int i = ps.executeUpdate();
			    if(i > 0) {
			    	 JOptionPane.showMessageDialog(null, "Task activity link ", "MESSAGE",  i);
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
