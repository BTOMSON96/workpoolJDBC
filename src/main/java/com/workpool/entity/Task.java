package com.workpool.entity;

import java.util.Calendar;

import com.workpool.enums.Priority;
import com.workpool.enums.Status;



public class Task {

	
	 private int id;          
	 private String title;      
     private String description;
     
	 private Entry entry_id;   
	 private TaskType task_type; 
	 private Calendar created_on;   
	 private Calendar due_date;    
	 private Resource owner_id;       
	 private Resource resource_id; 
	 private Status status;     
     private Priority piority; 
    
     public Task() {
    	 
     }
     
     
     public Task(int id, String title, String description, Entry entry_id, TaskType task_type, Calendar created_on,
			Calendar due_date, Resource owner_id, Resource resource_id, Status status, Priority piority) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.entry_id = entry_id;
		this.task_type = task_type;
		this.created_on = created_on;
		this.due_date = due_date;
		this.owner_id = owner_id;
		this.resource_id = resource_id;
		this.status = status;
		this.piority = piority;
	}


	/*
    public Task(ResultSet rs) throws SQLException, ParseException{
    	this.id = rs.getInt(1);
    	this.title = rs.getString(2);
    	this.description = rs.getString(3);
    	this.entry_id = rs.getInt(4);
    	this.task_type = rs.getInt(5);
    	this.created_on = rs.getDate(6);
    	this.due_date = rs.getDate(7);
    	this.owner_id = rs.getInt(8);
    	this.resource_id = rs.getInt(9); 
    	
    	this.status = Status.statusId(rs.getInt(10));
    	this.piority = Priority.priorityId(rs.getInt(11));
    }
   */ 
    

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Entry getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(Entry entry_id) {
		this.entry_id = entry_id;
	}
	public TaskType getTask_type() {
		return task_type;
	}
	public void setTask_type(TaskType task_type) {
		this.task_type = task_type;
	}
	
	public Calendar getCreated_on() {
		return created_on;
	}


	public void setCreated_on(Calendar created_on) {
		this.created_on = created_on;
	}


	public Calendar getDue_date() {
		return due_date;
	}


	public void setDue_date(Calendar due_date) {
		this.due_date = due_date;
	}


	public Resource getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(Resource owner_id) {
		this.owner_id = owner_id;
	}
	public Resource getResource_id() {
		return resource_id;
	}
	public void setResource_id(Resource resource_id) {
		this.resource_id = resource_id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Priority getPiority() {
		return piority;
	}
	public void setPiority(Priority piority) {
		this.piority = piority;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", entry_id=" + entry_id.getName()
				+ ", task_type=" + task_type.getName() + ", created_on=" + created_on.getTime() + ", due_date=" + due_date.getTime() + ", owner_id="
				+ owner_id.getName() + ", resource_id=" + resource_id.getName() + ", status=" + status + ", piority=" + piority +  "\n"+ "]";
	}
	
}
