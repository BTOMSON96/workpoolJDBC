package com.workpool;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.workpool.controller.ResourceController;
import com.workpool.entity.Resource;
import com.workpool.enums.ResourceType;

class ResourceControllerTest {
	ResourceController controller = new ResourceController();
	Resource resource = new Resource();
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Testing resource id
	@Test
	void testId() {
		resource.setId(30);
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	//Testing resource type 
	 
	@Test
	void testType() {
		ResourceType type = ResourceType.typeFromId(7);
		resource.setResourcetype(type);
		errorMessageList = controller.validateResource(resource);
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
		
		resource.setName("   ");	  
		errorMessageList = controller.validateResource(resource);  
		
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	//Testing surname
	@Test
	void testSurname() {
			
	resource.setSurname("   ");	  
	errorMessageList = controller.validateResource(resource);  
			
	if(errorMessageList.size() > 0 ){  
	assertTrue(true);
	 }
	else {
	assertTrue(false);
		}
	}
		
	//Testing DOB
	@Test
	void testDob() {
			
   //resource.setDob();	  
	errorMessageList = controller.validateResource(resource);  
			
	if(errorMessageList.size() > 0 ){  
	assertTrue(true);
			}
	else {
	assertTrue(false);
			}
		}
		
	//Testing address
	@Test
	void testAddress() {
			
	resource.setAddress("   ");	  
	errorMessageList = controller.validateResource(resource);  
			
	if(errorMessageList.size() > 0 ){  
		assertTrue(true);
			}
	else {
	assertTrue(false);
			}
		}
	
	//Testing email
	@Test
	void testEmail() {
	
	resource.setEmail("tomsonb1");
	errorMessageList = controller.validateResource(resource);
	if(errorMessageList.size() > 0) {
		assertTrue(true);
	}
	else {
		assertTrue(false);
	}
	}
	
	//Testing Username
	@Test
	void testUsername() {
	
	resource.setUsername("   ");
	errorMessageList = controller.validateResource(resource);
	if(errorMessageList.size() > 0) {
		assertTrue(true);
	}
	else {
		assertTrue(false);
	}
	}
	
	//Testing phone number
	@Test
	void testPhone() {
	
	resource.setPhone("06625266609");
	errorMessageList = controller.validateResource(resource);
	if(errorMessageList.size() > 0) {
		assertTrue(true); 
	}
	else {
		assertTrue(false);
	    }
	}
	
	//Testing password
	@Test
	void testPassword() {
	
	resource.setPassword("    ");
	errorMessageList = controller.validateResource(resource);
	if(errorMessageList.size() > 0) {
		assertTrue(true);
	}
	else {
		assertTrue(false);
	}
	}
	
	
	
	
	
	//Test create method
	@Test
	void testCreate() {
		String dateofbirth = "2002-07-26";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 
	  
		try { 
		Date date = dateformat.parse(dateofbirth);
		
		//convert date to calendar
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(date.getTime());
		;
		
		assertEquals(2002, calender.get(Calendar.YEAR));
		assertEquals(6, calender.get(Calendar.MONTH));
		assertEquals(26, calender.get(Calendar.DAY_OF_MONTH));
		
	
	  ResourceType type = ResourceType.typeFromId(2);
	 
		resource.setResourcetype(type);
		resource.setName("paul");
		resource.setSurname("walker");
	    resource.setDob(calender);
		resource.setAddress("CPT");
		resource.setEmail("walker@gmail.com");
		resource.setUsername("pual.walker");
		resource.setPhone("0735227045");
		resource.setPassword("456");
		
		System.out.println("date " +date);
		 errorMessageList = controller.validateResource(resource);
		  
			if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
				assertTrue(true);
				controller.createResource(resource);
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
			
			ArrayList<Resource>  resourceList = controller.readAll() ;
			assertEquals(expected,  resourceList.size());
            assertTrue(true);     	
		}
          catch(Exception e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	//Test read by ID    
	@Test
	void testReadResource() {

		try {
			int id = 33;
			resource.setId(id);
			
			 errorMessageList = controller.validateResource(resource);
			 if(errorMessageList.size() > 0) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
					controller.readResourceById(id);
					
				}
		
			
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	}
	
	
	//Test update
	@Test
	void testUpdateResource() {
		try {
			int id = 1; 
			resource.setId(id);
			 errorMessageList = controller.validateResource(resource);
			 if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
			assertTrue(true);
		   controller.updateResource(resource);
					
			}
			
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	}

	//Test delete
	@Test
	void testDeleteResource() {
		try {
			int id = 32; 
			resource.setId(id);
			 errorMessageList = controller.validateResource(resource);
			 if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
			assertTrue(true);
		   controller.deleteResource(resource);
					
			}
		}
		 catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
		}
	}
}
