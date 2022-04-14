package com.workpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.workpool.controller.ActivityController;
import com.workpool.controller.EntryController;
import com.workpool.controller.ResourceController;
import com.workpool.controller.TaskController;

import com.workpool.entity.Activity;



public class ActivityControllerTest {
ActivityController controller = new ActivityController();
Activity activity = new Activity();


ArrayList<String>  errorMessageList = new ArrayList<>();

//Testing activity id
@Test
void testId() {
	activity.setId(30);
	errorMessageList = controller.validateActivity(activity);
	if(errorMessageList.size() > 0) {
		assertTrue(true);
	}
	else{
		assertTrue(false);
	}
}


//Testing Title
@Test
void testName() {
	
	activity.setTitle("     ");	  
	errorMessageList = controller.validateActivity(activity);  
	
	if(errorMessageList.size() > 0 ){  
		assertTrue(true);
	}
	else {
		assertTrue(false);
	}
}

//Testing entry id
@Test
void testEntryId() {
	EntryController entryController = new EntryController();
	int entry = 7;
	activity.setEntry_id(entryController.readEntryById(entry));
	errorMessageList = controller.validateActivity(activity);
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
	activity.setTask_id(taskController.readTaskById(task));
	errorMessageList = controller.validateActivity(activity);
	if(errorMessageList.size() > 0) {
		assertTrue(true);
	}
	else{
		assertTrue(false);
	}
}

//Testing owner (Resource)
@Test
void testOwner() {
	ResourceController resourceController = new ResourceController();
	 int resourceId = 31;
	 try {
		 activity.setOwner(resourceController.readResourceById(resourceId));
		  errorMessageList = controller.validateActivity(activity);
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
	EntryController entryController = new EntryController();
	TaskController taskController = new TaskController();
	ResourceController resourceController = new ResourceController();
	  
	
	Calendar calender = Calendar.getInstance();
	calender.getTime();
  
	try { 
		
		
		
		
		activity.setTitle("Working on JDBC");
		activity.setDescription("WorkPool Training");
		activity.setEntry_id(entryController.readEntryById(2));
		activity.setTask_id(taskController.readTaskById(1));
		activity.setCreated_on(calender);
		activity.setOwner(resourceController.readResourceById(33));
		
		System.out.println("today :" +calender.getTime());
 
	
	 errorMessageList = controller.validateActivity(activity);
	  
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.createActivity(activity);
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
				
		ArrayList<Activity>  activityList = controller.readAll();
		assertEquals(expected,  activityList.size());
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
		activity.setId(id);
		
		 errorMessageList = controller.validateActivity(activity);
		 if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
				assertTrue(true);
				controller.readActivityById(id);
				
			}
	}
	 catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
}


//Test update
@Test
void testUpdateActivity() {
	try {
		int id = 2; 
		activity.setId(id);
		 errorMessageList = controller.validateActivity(activity);
		 if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
		assertTrue(true);
	   controller.updateActivity(activity);
				
		}
		
	}
	 catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
}

//Test delete
@Test
void testDeleteActivity() {
	try {
		int id = 30; 
		activity.setId(id);
		 errorMessageList = controller.validateActivity(activity);
		 if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
		assertTrue(true);
	   controller.deleteActivity(activity);
				
		}
	}
	 catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
	}
}

}
