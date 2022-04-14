package com.workpool.enums;

public enum Status {
   
	Received(1),
	Completed(2),
	Close(3);
	 private int id;
	 
	private Status(int id) {
		this.id = id;
	}
	
	public static Status statusId(int id) {
		for(Status rt: values()) {
			if(rt.id == id) {
				return rt;
			}
		}
		return null;
	}
	 public int toInt() {
	      return id;
	  }
}
