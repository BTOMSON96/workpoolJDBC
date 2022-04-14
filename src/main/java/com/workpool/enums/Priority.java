package com.workpool.enums;

public enum Priority {

  Critical(1),
  Important(2),
  Normal(3),
  LowImportance(4);
  
  private int id;
  
  private Priority(int id) {
	  this.id = id;
  }
  
	public static Priority priorityId (int id)
{
		for(Priority rt: values()) {
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
