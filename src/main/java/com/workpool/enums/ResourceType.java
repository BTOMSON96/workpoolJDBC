package com.workpool.enums;

public enum ResourceType {
    //List of constants
	Internal(1),
	External(2);
	
	private int id;
	//private String name;
	
	private ResourceType(int id) { //constructor to initialize id
		
		this.id = id;
		
	}

	
	public static ResourceType typeFromId (int id) //static method--->>can be invoked without creating object of this class
{
		for(ResourceType rt: values()) {  //loop through the constants values
			if(rt.id == id) {  //if the id that is passed is = to the id of constant the return the constant
				return rt;
			}
		}
		return null;  //return null if the id doesn't exist
}
	 public int toInt() { //toInt() method that returns the id.. so that i can convert the enums toInt the save to DB
	        return id;
	    }

}

