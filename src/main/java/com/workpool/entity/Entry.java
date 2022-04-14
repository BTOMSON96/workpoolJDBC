package com.workpool.entity;



public class Entry {


	private int id;     
	 private String name ;
	 private Resource  author ; //linked to resource 
	 private String notes;
	 
	 public Entry() {
		 
	 }
		public Entry(int id, String name, Resource author, String notes) {
			super();
			this.id = id;
			this.name = name;
			this.author = author;
			this.notes = notes;
		}

	 /*
	 public Entry(ResultSet rs) throws SQLException, ParseException  { 

			 
		 this.id = rs.getInt("id");
		 this.name = rs.getString("entry_name");
		 this.notes = rs.getString("notes");
		 
		 int resourceId = rs.getInt("author");
		ResourceController resourceController = new ResourceController();
		this.author = resourceController.readResource(resourceId); 
	 }
*/
	 
	 
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

	public Resource getAuthor() {
		return author;
	}

	public void setAuthor(Resource author) {
		this.author = author;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	 
	 
	 @Override
	public String toString() {
		return "Entry [id=" + id + ", name=" + name + ", author=" + author.getName() + ", notes=" + notes + "]";
	}
	
}


