package com.workpool.entity;

import java.util.Calendar;


import com.workpool.enums.ResourceType;

public class Resource {
	
	  private int id;            
	  private ResourceType resourcetype;    
	  private String name;
	  private String surname;
	  private Calendar dob ;
	  private String address;
	  private String email;        
      private String username;     
	  private String phone;        
	  private String password;
	  
	 public Resource() {
		 
	 }
/*
	  public Resource(ResultSet rs) throws SQLException, ParseException{
		
		  
		  this.id = rs.getInt(1);
		  this.resourcetype = ResourceType.typeFromId( rs.getInt(2));
		  this.name = rs.getString(3);
		  this.surname = rs.getString(4);
		  
		  //Calender
		  
		  this.address = rs.getString(5);
		  this.email = rs.getString(6);;
		  this.username =rs.getString(7);;
		  this.phone = rs.getString(8);;
		  this.password = rs.getString(9);;
	  }
	  */
	  
		public Resource(int id, ResourceType resourcetype, String name, String surname, Calendar dob, String address,
				String email, String username, String phone, String password) {
			super();
			this.id = id;
			this.resourcetype = resourcetype;
			this.name = name;
			this.surname = surname;
			this.dob = dob;
			this.address = address;
			this.email = email;
			this.username = username;
			this.phone = phone;
			this.password = password;
		}
   
   
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public ResourceType getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(ResourceType resourcetype) {
		this.resourcetype = resourcetype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourcetype=" + resourcetype + ", name=" + name + ", surname=" + surname
				+ ", dob=" + dob.getTime() + ", address=" + address + ", email=" + email + ", username=" + username + ", phone="
				+ phone + ", password=" + password +  "\n"+  "]";
	}
	
	  
}
