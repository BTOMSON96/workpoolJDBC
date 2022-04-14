package com.workpool.controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import javax.swing.JOptionPane;

import com.google.protobuf.TextFormat.ParseException;
import com.workpool.database.Connect;
import com.workpool.entity.Resource;
import com.workpool.enums.ResourceType;




public class ResourceController {

	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null; /*
	public static boolean isDateValid(Resource resource) {
		  String dob =  resource.getDob().toString();
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      df.setLenient(false);
      try {
		df.parse(dob);
		 return true;
	} catch (java.text.ParseException e) {
		 return false;
	}
	}
	*/
	public ArrayList<String> validateResource(Resource resource) {
		
		
		ArrayList<String>  errorMessageList = new ArrayList<>();
		/* 
		//validating resource id
		int id = resource.getId();
		String query = "SELECT ID FROM RESOURCE WHERE ID ="+id;
		try {
			 st = Connect.getConnection().createStatement();
			  rs = st.executeQuery(query);
			  if(!rs.next()) {
				  
						errorMessageList.add("Resource ID does not exist");
						System.out.println("Resource ID does not exist");
			}
		}
		
			catch(SQLException e) {
				System.out.println(e);
			}
		
		*/
		//Validate ResourceType enums
		ResourceType type = resource.getResourcetype();
				
	   if(type != ResourceType.typeFromId(1) && type != ResourceType.typeFromId(2)) {
	    errorMessageList.add("Resource type must be 1 or 2");
		System.out.println("Resource type must be 1 or 2");
				
	   }
		
		//Validating Name
		String name = resource.getName();
		
		if (name == null || name.trim().isEmpty())   {
			
		errorMessageList.add("no name specified");
		 System.out.println("no name specified");
		}
		
		//Validating surname
		String surname = resource.getSurname();
				
		if (surname == null || surname.trim().isEmpty())   {
					
		 errorMessageList.add("no surname specified");
		 System.out.println("no surname specified");
				}
		/*
		//Validating dob
		boolean isDateValid;
		  String dob =  resource.getDob().toString();
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      df.setLenient(false);
	      try {
			df.parse(dob);
			 isDateValid = true;
		} catch (java.text.ParseException e) {
			 isDateValid = false;
		}
	      if(isDateValid == false) {
	    	errorMessageList.add("Date of birth is invalid");
	  		System.out.println("Date of birth is invalid");
	      }
		System.out.println( "fghj "+dob);
		*/
	    //String dob =  resource.getDob().toString();
		//String dobPattern = "(\\d{4}) - (0?[1-9] | 1[012]) - (0?[1-9] | [12][0-9] | 3[01])";   //first brackets represents year, 2nd represents month.. 3rd represents day
		//Date dob =  new Date(resource.getDob().getTimeInMillis());
		//if (dob == null || dob.matches(dobPattern) == false) {	
		//errorMessageList.add("Date of birth is invalid");
		//System.out.println("Date of birth is invalid");
		//}
		
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                // .withResolverStyle(ResolverStyle.STRICT);
		 
     // LocalDate.parse(dob.toString(), formatter);
     
		
		//Validating address
		String address = resource.getAddress();
				
		if (address == null || address.trim().isEmpty())   {
					
		errorMessageList.add("address can't be null");
		System.out.println("address can't be null");
		}
		
		//Validating Email
		String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String email = resource.getEmail();
						
	   if (email == null || email.matches(emailPattern) == false)   {
							
		 errorMessageList.add("Invalid email address");
		 System.out.println("Invalid email address");
		}
				
		//Validating username
		String username = resource.getUsername();
				
	   if (username == null || username.trim().isEmpty())   {
					
		errorMessageList.add("no username specified");
		}
	   
	 		
	//Validating phone
		String numberPattern = "^[0-9]{10}$";
		String phone = resource.getPhone();
	if (phone == null || phone.matches(numberPattern) == false) {
		 
		errorMessageList.add("Invalid phone number");
		System.out.println("Invalid phone number");
	 
       }
		
		//Validating Password
		String password = resource.getPassword();
				
		if (password == null || password.trim().isEmpty())   {
					
		  errorMessageList.add("no password specified");
		 System.out.println("no password specified");
				
		}
			
		return errorMessageList;
	}
	
	
	//CRUD 
	public void createResource(Resource resource) {
		
		try {
			ps = Connect.getConnection().prepareStatement("INSERT INTO Resource (type, name, surname, dob,  address, email, username, phone, password) values (?,?,?,?,?,?,?,?,?)");
		
			 
	       //String date = resource.getDob().toString();
			//SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			
			//java.util.Date dateStr = dateformat.parse(date);
			//java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			
			
			ps.setInt(1, resource.getResourcetype().toInt());
			
			ps.setString(2, resource.getName());
			ps.setString(3, resource.getSurname());
			//set Date to accept calendar object
			ps.setDate(4, new Date(resource.getDob().getTimeInMillis()));
			ps.setString(5, resource.getAddress());
			ps.setString(6, resource.getEmail());
			ps.setString(7, resource.getUsername());
			ps.setString(8, resource.getPhone());
			ps.setString(9, resource.getPassword());
			
			
			int i = ps.executeUpdate();
			
			
			if(i > 0 ) {
				JOptionPane.showMessageDialog(null, "resource created",  "MESSAGE" , i);
				
			}
			Connect.getConnection().close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
public ArrayList<Resource> readAll() throws  Exception{
		
		ArrayList<Resource>  resourceList = new ArrayList<>();
	
			
		   st = Connect.getConnection().createStatement();
		   String Query = "SELECT * FROM RESOURCE";
		  rs = st.executeQuery(Query);
		while(rs.next()) {
			
			//Getting DOB from database then converting it to calendar
			Date dob = rs.getDate("dob");
			Calendar calender = Calendar.getInstance();
			calender.setTimeInMillis(dob.getTime());
			
			
			Resource resource = new Resource();
			
			resource.setId(rs.getInt("id"));
			resource.setResourcetype(ResourceType.typeFromId(rs.getInt("type")));
			resource.setName(rs.getString("name"));
			resource.setSurname(rs.getString("surname"));
			resource.setDob(calender);
			resource.setAddress(rs.getString("address"));
			resource.setEmail(rs.getString("email"));
			resource.setUsername(rs.getString("username"));
			resource.setPhone(rs.getString("phone"));
			resource.setPassword(rs.getString("password"));
			
			
			resourceList.add(resource);
			
			
		}
		System.out.println(resourceList.toString());
return resourceList;
	}
	
	

	public Resource readResourceById(int id) throws ParseException {
		Resource resource = new Resource();
		PreparedStatement ps;
		ArrayList<Resource> resourceList = new ArrayList<>();
		
		String Query = "SELECT * FROM RESOURCE WHERE ID ="+id;
		try {
				ps = Connect.getConnection().prepareStatement(Query);
		   
				rs = ps.executeQuery();
				while (rs.next()) {
					
					//Getting DOB from database then converting it to calendar
					Date dob = rs.getDate("dob");
					Calendar calender = Calendar.getInstance();
					calender.setTimeInMillis(dob.getTime());
					
					
					resource.setId(rs.getInt("id"));
					resource.setResourcetype(ResourceType.typeFromId(rs.getInt("type")));
					resource.setName(rs.getString("name"));
					resource.setSurname(rs.getString("surname"));
					resource.setDob(calender);
					resource.setAddress(rs.getString("address"));
					resource.setEmail(rs.getString("email"));
					resource.setUsername(rs.getString("username"));
					resource.setPhone(rs.getString("phone"));
					resource.setPassword(rs.getString("password"));
					
					resourceList.add(resource);
					

			}
				//Connect.getConnection().close();
		}
		catch (SQLException e) {
             System.out.println(e);
		}
		//System.out.println(resource.toString());
		return resource;
	}

	//update 
	
	public void updateResource(Resource resource) {
		
		
		 String Query = "UPDATE resource SET name = 'Buntu' WHERE ID = "+resource.getId();
		try {
			
		     ps = Connect.getConnection().prepareStatement(Query);
		    int i = ps.executeUpdate();
		    if(i > 0) {
		    	 JOptionPane.showMessageDialog(null, "Resource updated ", "MESSAGE",  i);
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
	public void deleteResource(Resource resource) {
		 
	
		 String Query = " DELETE FROM resource WHERE ID = "+resource.getId();
			try {
				
			     ps = Connect.getConnection().prepareStatement(Query);
			    int i = ps.executeUpdate();
			    if(i > 0) {
			    	 JOptionPane.showMessageDialog(null, "Resource deleted ", "MESSAGE",  i);
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
