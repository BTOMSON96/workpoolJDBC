package com.workpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.*;


import org.junit.jupiter.api.Test;

import com.workpool.controller.EntryController;
import com.workpool.controller.ResourceController;
import com.workpool.controller.TaskController;
import com.workpool.controller.TaskTypeController;

import com.workpool.entity.Task;
import com.workpool.enums.Priority;
import com.workpool.enums.Status;

public class TaskControllerTest {
TaskController controller = new TaskController();
Task task = new Task();
ArrayList<String>  errorMessageList = new ArrayList<>();


	//Testing Title
	@Test
	void testName() {
		
		task.setTitle("     ");	  
		errorMessageList = controller.validateTask(task);  
		
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	//Testing entry id (Entry)
	@Test
	void testEntryId() {
	EntryController entryController = new EntryController();
	 int entryId = 2;
	 try {
		    task.setEntry_id(entryController.readEntryById(entryId));
			errorMessageList = controller.validateTask(task);
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
	
	//Testing task type (type)
		@Test
		void testTaskType() {
		TaskTypeController typeController = new TaskTypeController();
		 int type = 2;
		 try {
			    task.setTask_type(typeController.readTaskTypeById(type));
				errorMessageList = controller.validateTask(task);
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
		
		//Testing owner id (Resource)
		@Test
		void testOwner() {
		ResourceController resourceController = new ResourceController();
		 int owner = 31;
		 try {
			    task.setOwner_id(resourceController.readResourceById(owner));
				errorMessageList = controller.validateTask(task);
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
		
		//Testing task type (type)
		@Test
		void testResource() {
		ResourceController resourceController = new ResourceController();
		 int assignedTo = 31;
		 try {
			    task.setResource_id(resourceController.readResourceById(assignedTo));
				errorMessageList = controller.validateTask(task);
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
		
		
		
		//Testing Status
		@Test
		void testStatus() {
			Status status = Status.statusId(3);
			task.setStatus(status);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		
		//Testing Priority
		@Test
		void testPrority() {
			Priority priority = Priority.priorityId(2);
			task.setPiority(priority);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		

		//Test create method
		@Test
		void testCreate() {
			EntryController entryController = new EntryController();
			TaskTypeController typeController = new TaskTypeController();
			ResourceController resourceController = new ResourceController();
			
			//setting due date
			String duedate = "2022-03-26";
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 
			 //Today's date for created on attribute 
			Calendar calender = Calendar.getInstance();
			calender.getTime();
			try { 
			
			
		
				Date date = dateformat.parse(duedate);
				
				//convert due date to calendar
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date.getTime());
			
				task.setTitle("Self Study");
				task.setDescription("The WorkPool wiki ");
				task.setEntry_id(entryController.readEntryById(1));
				task.setTask_type(typeController.readTaskTypeById(2));
				task.setCreated_on(calender);
				task.setDue_date(cal);
				task.setOwner_id(resourceController.readResourceById(31));
				task.setResource_id(resourceController.readResourceById(31));
				task.setStatus(Status.statusId(1));
				task.setPiority(Priority.priorityId(2));
		 
			
			 errorMessageList = controller.validateTask(task);
			  
				if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
					controller.createTask(task);
				}
				
			}catch(Exception e) {
				
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
		
		//Test read all method
		@Test
		void testReadAll() {
		int expected =3 ;
			try {
						
				ArrayList<Task>  taskList = controller.readAll();
				assertEquals(expected,  taskList.size());
			     assertTrue(true);     	
			}
			 catch(Exception e) {
						
				e.printStackTrace();
				assertTrue(false);
			}
	}
				
	 //Test read by ID    
	  @Test
	 void testReadTaskById() {

		try {
			int id = 2;
			task.setId(id);
						
			 errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
			else {
				  assertTrue(true);
				controller.readTaskById(id);
								
			}
					
	    }catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
        }
	}
				
    //Test update
	 @Test
	void testUpdateTask() {
         try {
			 int id = 1; 
			 task.setId(id);
			 errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
			else {
				assertTrue(true);
			   controller.updateTask(task);
				}
			}catch(Exception e) {
				    e.printStackTrace();
					assertTrue(false);
				}
		}

	//Test delete
	@Test
	void testDeleteTask() {
		try {
		int id = 3; 
		task.setId(id);
		errorMessageList = controller.validateTask(task);
		 if(errorMessageList.size() > 0) {
		       assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteTask(task);
		}
	} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
			}
	}
				
}
			
		
