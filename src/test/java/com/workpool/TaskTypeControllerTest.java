package com.workpool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.workpool.controller.TaskTypeController;

import com.workpool.entity.TaskType;


public class TaskTypeControllerTest {
	
ArrayList<String>  errorMessageList = new ArrayList<>();
	TaskTypeController controller = new TaskTypeController();
	TaskType tasktype = new TaskType();
	
	//Testing resource id
	@Test
	void testId() {
		tasktype.setId(1);
		errorMessageList = controller.validateTaskType(tasktype);
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
			
	        tasktype.setName("Buntu");	  
			errorMessageList = controller.validateTaskType(tasktype);  
			
			if(errorMessageList.size() > 0 ){  
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
		
		//Test create method
		@Test
		void testCreate() {
			
			  
			try { 
		  
			tasktype.setName("DEV: Testing");
			 errorMessageList = controller.validateTaskType(tasktype);
			  
				if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
					controller.createTaskType(tasktype);
				}
				
			}catch(Exception e) {
				
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
		//Test read all method
		@Test
		void testReadAll() {
			int expected = 5;
			try {
				
				ArrayList<TaskType>  typeList = controller.readAll() ;
				assertEquals(expected,  typeList.size());
	            assertTrue(true);     	
			}
	          catch(Exception e) {
				
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
		//Test read by ID    
		@Test
		void testReadTaskType() {

			try {
				int id = 10;
				tasktype.setId(id);
				
				 errorMessageList = controller.validateTaskType(tasktype);
				 if(errorMessageList.size() > 0) {
						assertTrue(false);
					}
					else {
						assertTrue(true);
						controller.readTaskTypeById(id);
					}
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
		}
		
		//Test update
		@Test
		void testUpdateTaskType() {
			try {
				int id = 29; 
				tasktype.setId(id);
				 errorMessageList = controller.validateTaskType(tasktype);
				 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
				assertTrue(true);
			   controller.updateTaskType(tasktype);
						
				}
				
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
		}
		//Test delete
		@Test
		void testDeleteTaskType() {
			try {
				int id = 28; 
				tasktype.setId(id);
				 errorMessageList = controller.validateTaskType(tasktype);
				 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
				assertTrue(true);
			   controller.deleteTaskType(tasktype);
						
				}
			}
			 catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
			}
		}
}
