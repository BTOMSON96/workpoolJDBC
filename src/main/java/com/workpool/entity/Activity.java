package com.workpool.entity;


import java.util.Calendar;


public class Activity {
	
	
	private int id;
	private String title;
	private String description;
	private Entry entry_id;
	private Task task_id;
	private Calendar created_on;
	private Resource owner;
	
	public Activity() {
		
	}
	public Activity(int id, String title, String description, Entry entry_id, Task task_id, Calendar created_on, Resource owner) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.entry_id = entry_id;
		this.task_id = task_id;
		this.created_on = created_on;
		this.owner = owner;
	}

	/*
	public Activity(ResultSet rs) throws SQLException, ParseException {
		
		this.id = rs.getInt(1);
		this.description = rs.getString(2);
		this.entry_id = rs.getInt(3);
		this.task_id = rs.getInt(4);
		this.created_on = rs.getDate(5);
		this.resource = rs.getInt(6);
		
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
	public Task getTask_id() {
		return task_id;
	}
	public void setTask_id(Task task_id) {
		this.task_id = task_id;
	}
	
	public Calendar getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Calendar created_on) {
		this.created_on = created_on;
	}
	public Resource getOwner() {
		return owner;
	}
	public void setOwner(Resource owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", description=" + description + ", entry_id=" + entry_id.getName() + ", task_id=" + task_id.getTitle()
				+ ", created_on=" + created_on + ", resource=" + owner.getName() + "\n"+ "]";
	}



}
