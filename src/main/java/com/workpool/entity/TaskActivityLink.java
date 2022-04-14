package com.workpool.entity;


import java.util.Calendar;



public class TaskActivityLink {
	private int id;         
	private Task task_id;  
	private Activity activity_id;  
	private Calendar date_linked;
	
	
	public TaskActivityLink() {
		
	}

	public TaskActivityLink(int id, Task task_id, Activity activity_id, Calendar date_linked) {
		super();
		this.id = id;
		this.task_id = task_id;
		this.activity_id = activity_id;
		this.date_linked = date_linked;
	}

	/*
	public TaskActivityLink(ResultSet rs) throws SQLException, ParseException  {
		this.id = rs.getInt(1);
		this.task_id = rs.getInt(2); 
		this.activity_id = rs.getInt(3);
		this.date_linked = rs.getDate(4);
	}
	*/


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Task getTask_id() {
		return task_id;
	}


	public void setTask_id(Task task_id) {
		this.task_id = task_id;
	}


	public Activity getActivity_id() {
		return activity_id;
	}


	public void setActivity_id(Activity activity_id) {
		this.activity_id = activity_id;
	}

	public Calendar getDate_linked() {
		return date_linked;
	}

	public void setDate_linked(Calendar date_linked) {
		this.date_linked = date_linked;
	}

	@Override
	public String toString() {
		return "TaskActivityLink [id=" + id + ", task_id=" + task_id.getTitle() + ", activity_id=" + activity_id.getTitle() + ", date_linked="
				+ date_linked.getTime() +  "\n"+ " ]";
	}
	


}
