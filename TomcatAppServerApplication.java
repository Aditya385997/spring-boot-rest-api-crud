package com.example.tomcatappserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.tomcatappserver.services.CRUDService;

/*
 * CURL:
 * [23:26, 6/27/2021] ABHIJEET CHAVAN: curl -X PUT -H "Content-Type: application/json" -d '{"key1":"value"}' "YOUR_URI"
[23:32, 6/27/2021] ABHIJEET CHAVAN: curl -X GET "http://localhost:8080/crud/get"
[23:32, 6/27/2021] ABHIJEET CHAVAN: curl -X DELETE "http://localhost:8080/crud/delete?id=1"
[23:35, 6/27/2021] ABHIJEET CHAVAN: curl -X POST null-H "Content-Type: application/json" -d  '{"id":1,"name":"abhi","age":27}'  "http://localhost:8080/crud/insert"
 */

@SpringBootApplication
public class TomcatAppServerApplication {
	// 1. Learn java to create a dummy server 
	private final CRUDService crudService;
	
	@Autowired
	public TomcatAppServerApplication(CRUDService crudService) {
		this.crudService = crudService;
		// todo: valid Exception is "table already exists else dont 
		// start server
		try {
			this.crudService.createCRUDTable();
		} catch(Exception e) {
			
		}
	}
	public static void main(String[] args) {		
		SpringApplication.run(TomcatAppServerApplication.class, args);
	}

}
