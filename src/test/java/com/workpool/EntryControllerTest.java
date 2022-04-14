package com.workpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import com.workpool.controller.EntryController;
import com.workpool.controller.ResourceController;
import com.workpool.entity.Entry;




public class EntryControllerTest {
EntryController controller = new EntryController();
Entry entry = new Entry();
	
ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Testing entry id
	@Test
	void testId() {
		entry.setId(30);
		errorMessageList = controller.validateEntry(entry);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	//Testing Name
	@Test
	void testName() {
		
		entry.setName("dghdhghg");	  
		errorMessageList = controller.validateEntry(entry);  
		
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	
	//Testing author id (Resource)
	@Test
	void testAuthor() {
	ResourceController resourceController = new ResourceController();
	 int resourceId = 31;
	 try {
		 entry.setAuthor(resourceController.readResourceById(resourceId));
			errorMessageList = controller.validateEntry(entry);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
	 }catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
		
	}
	
	//Test create method
		@Test
		void testCreate() { 
			ResourceController resourceController = new ResourceController();
			int resourceId = 31;
			
			try { 
		
			entry.setName("Services");
			entry.setAuthor(resourceController.readResourceById(resourceId));
			entry.setNotes("This is a  ");
		    
			errorMessageList = controller.validateEntry(entry);
			  
				if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
					controller.createEntry(entry);
				}
				
			}catch(Exception e) {
				
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
		//Test read all method
		@Test
		void testReadAll() {
			int expected =2 ;
			try {
				
				ArrayList<Entry>  entryList = controller.readAll() ;
				assertEquals(expected,  entryList.size());
	            assertTrue(true);     	
			}
	          catch(Exception e) {
				
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
		
		//Test read by ID    
		@Test
		void testReadEntryById() {

			try {
				int id = 3;
				entry.setId(id);
				
				 errorMessageList = controller.validateEntry(entry);
				 if(errorMessageList.size() > 0) {
						assertTrue(false);
					}
					else {
						assertTrue(true);
						controller.readEntryById(id);
						
					}
			
				
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
		}
		
		
		//Test update
		@Test
		void testUpdateEntry() {
			try {
				int id = 2; 
				entry.setId(id);
				 errorMessageList = controller.validateEntry(entry);
				 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
				assertTrue(true);
			   controller.updateEntry(entry);
						
				}
				
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
		}

		//Test delete
		@Test
		void testDeleteEntry() {
			try {
				int id = 3; 
				entry.setId(id);
				 errorMessageList = controller.validateEntry(entry);
				 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
				assertTrue(true);
			   controller.deleteEntry(entry);
						
				}
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
			}
		}
}
