package com.workpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.workpool.controller.ActivityController;

import com.workpool.controller.TaskActivityLinkController;
import com.workpool.controller.TaskController;

import com.workpool.entity.TaskActivityLink;

public class TaskActivityLinkControllerTest {
	
	TaskActivityLinkController controller = new TaskActivityLinkController();
	TaskActivityLink link = new TaskActivityLink();
	ArrayList<String>  errorMessageList = new ArrayList<>();

	
	//Testing Link id
	@Test
	void testId() {
		link.setId(30);
		errorMessageList = controller.validateLink(link);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	
	//Testing task id
	@Test
	void testTaskId() {
		TaskController taskController = new TaskController();
		int task = 7;
		link.setTask_id(taskController.readTaskById(task));
		errorMessageList = controller.validateLink(link);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	//Testing activity id
	@Test
	void testActivityId() {
		ActivityController activityController = new ActivityController();
		int activity = 7;
		link.setActivity_id(activityController.readActivityById(activity));
		errorMessageList = controller.validateLink(link);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}

	
	
	
	
	//CRUD
	//Test create method
	@Test
	void testCreate() {
		
		TaskController taskController = new TaskController();
		ActivityController activityController = new ActivityController();
		  

		Calendar calender = Calendar.getInstance();
		calender.getTime();
	  
		try { 
			
			link.setTask_id(taskController.readTaskById(1));
			link.setActivity_id(activityController.readActivityById(1));
		    link.setDate_linked(calender);
		
		
		 errorMessageList = controller.validateLink(link);
		  
			if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
				assertTrue(true);
				controller.createLink(link);
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
					
			ArrayList<TaskActivityLink>  linkList = controller.readAll();
			assertEquals(expected,  linkList.size());
		     assertTrue(true);     	
		}
		 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
		}
	}

	//Test read by ID    
	@Test
	void testReadActivity() {

		try {
			int id = 2;
			link.setId(id);
			
			 errorMessageList = controller.validateLink(link);
			 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
					controller.readLinkById(id);
					
				}
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	}

	
	
	//Test update
	@Test
	void testUpdateLink() {
		try {
			int id = 2; 
			link.setId(id);
			 errorMessageList = controller.validateLink(link);
			 if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
			assertTrue(true);
		   controller.updateLink(link);
					
			}
			
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	}

	//Test delete
	@Test
	void testDeleteLink() {
		try {
			int id = 3; 
			link.setId(id);
			 errorMessageList = controller.validateLink(link);
			 if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
			assertTrue(true);
		   controller.deleteLink(link);
					
			}
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
		}
	}

}
