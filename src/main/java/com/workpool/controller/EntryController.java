package com.workpool.controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.workpool.database.Connect;
import com.workpool.entity.Entry;



public class EntryController {

	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public ArrayList<String> validateEntry(Entry entry)
	{
		ArrayList<String>  errorMessageList = new ArrayList<>();
		
		
		//validating entry id
		int id = entry.getId();
		String query = "SELECT ID FROM ENTRY WHERE ID ="+id;
		try {
			 st = Connect.getConnection().createStatement();
			  rs = st.executeQuery(query);
			  if(!rs.next()) {
				  
				errorMessageList.add("Entry ID does not exist");
				System.out.println("Entry ID does not exist");
			}
		}
		
		catch(SQLException e) {
				System.out.println(e);
	}
		/*
		//Validating Name
		String name = entry.getName();
		
		if (name == null || name.trim().isEmpty())   {
			
		errorMessageList.add("no entry name specified");
		 System.out.println("no entry name specified");
		}
		
		
		//Validating author id (Resource)
		int author = entry.getAuthor().getId();
				
		String sql = "SELECT ID FROM RESOURCE WHERE ID ="+author;
		try {
			 st = Connect.getConnection().createStatement();
			  rs = st.executeQuery(sql);
			  if(!rs.next()) {
				  
				errorMessageList.add("Cannot add or update a child row");
				System.out.println("Cannot add or update a child row");
			}
		}
		
		catch(SQLException e) {
				System.out.println(e);
	}
		
		//Validating Name
		String notes = entry.getNotes();
				
		if (name == null || name.trim().isEmpty())   {
					
				errorMessageList.add("no name specified");
				 System.out.println("no name specified");
		}
			*/
		return errorMessageList;
		
	}
	
	
	
	
	//CRUD
	public void createEntry(Entry entry) {
		try {
			ps = Connect.getConnection().prepareStatement("INSERT INTO ENTRY (entry_name, author, notes) values (?,?,?)");
			
			
		
			ps.setString(1, entry.getName());
			ps.setInt(2, entry.getAuthor().getId());
			ps.setString(3, entry.getNotes());
			
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "entry created",  "MESSAGE" , i);
			}
			Connect.getConnection().close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
public ArrayList<Entry> readAll() throws  Exception{
	ResourceController resourceController = new ResourceController();
		ArrayList<Entry>  entryList = new ArrayList<>();
	
			
		   st = Connect.getConnection().createStatement();
		   String Query = "SELECT * FROM ENTRY";
		  rs = st.executeQuery(Query);
		while(rs.next()) {
			
			Entry entry = new Entry();
	          
			entry.setId(rs.getInt("id"));
			entry.setName(rs.getString("entry_name"));
			entry.setAuthor(resourceController.readResourceById(rs.getInt("author")));
		    entry.setNotes(rs.getString("notes"));
		    
			entryList.add(entry);
			
			
		}
		System.out.println(entryList.toString());
return entryList;
	}
	
	

	public Entry readEntryById(int id) {
		ResourceController resourceController = new ResourceController();
		Entry entry = new Entry();
		ArrayList<Entry> entryList = new ArrayList<>();
		String Query = "SELECT * FROM entry WHERE ID ="+id;
		try {
			
		    ps = Connect.getConnection().prepareStatement(Query);
		   // ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
			
				entry.setId(rs.getInt("id"));
				entry.setName(rs.getString("entry_name"));
				entry.setAuthor(resourceController.readResourceById(rs.getInt("author")));
			    entry.setNotes(rs.getString("notes"));
				entryList.add(entry);
			}	 
		  
		
		st.close();
		Connect.getConnection().close();
		}
		
		
		catch (Exception e) {
             System.out.println(e);
		}
		//System.out.println(entry.toString());
		return entry;
	}

	//update 
	
	public void updateEntry(Entry entry) {
		
		
		 String Query = "UPDATE Entry  SET entry_name = 'Jacana' WHERE ID = "+entry.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Entry updated ", "MESSAGE",  i);
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
	public void deleteEntry(Entry entry) {
		 
	
		 String Query = " DELETE FROM entry WHERE ID = "+entry.getId();
			try {
				
			     ps = Connect.getConnection().prepareStatement(Query);
			    int i = ps.executeUpdate();
			    if(i > 0) {
			    	 JOptionPane.showMessageDialog(null, "Entry deleted ", "MESSAGE",  i);
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
