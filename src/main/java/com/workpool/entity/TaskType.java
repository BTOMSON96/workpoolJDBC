package com.workpool.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.protobuf.TextFormat.ParseException;

public class TaskType {

	private int id;
	private String name;
	
	public TaskType() {
		
	}
	
	public TaskType(ResultSet rs) throws SQLException, ParseException {
		this.id = rs.getInt(1);
		this.name = rs.getString(2);

	}

	public int getId() { 
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TaskType [id=" + id + ", name=" + name +  "\n"+ "]";
	}
	
	
}
