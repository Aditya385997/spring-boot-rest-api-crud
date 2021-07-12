package com.example.tomcatappserver.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tomcatappserver.models.CRUDModel;
import com.example.tomcatappserver.services.CRUDService;
import com.example.tomcatappserver.services.DatabaseService;

@RestController
@RequestMapping(path="/crud")
public class CRUDController {
	private final CRUDService crudService;
	
	@Autowired
	public CRUDController(CRUDService crudService) {
		this.crudService = crudService;
	}
	
//	
//	@GetMapping(value="/get", produces="application/json")
//	public ResponseEntity<String> getObject() {
//		JSONObject jsonObj = new JSONObject();
//		try {
//		jsonObj = crudService.get();
//		return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
//		System.out.print(jsonObj);
//		} catch(Exception e) {
//			jsonObj.put("error", e);
//			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
//		}
//	}
	// get by using query parameters
	
	
	
	
	@GetMapping(value="/get", produces="application/json")
	public ResponseEntity<String> getObjectByQueryParams(
			@RequestParam(defaultValue="GuestUser") String name,
			@RequestParam(defaultValue="20") int age
			) {
		System.out.println("name "+name);
		System.out.println("age "+age);
		JSONObject jsonObj = new JSONObject();
		try {
		jsonObj = crudService.get();
		System.out.print(jsonObj);
		return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
		} catch(Exception e) {
			jsonObj.put("error", e);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// get 
	@GetMapping(value="/get/{id}", produces="application/json")
	public ResponseEntity<String> getObjectByPathVariables(@PathVariable("id") long id) {
		System.out.println("id passed "+id);
		JSONObject jsonObj = new JSONObject();
		try {
		jsonObj = crudService.get(id);
		System.out.print(jsonObj);
		return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
		} catch(Exception e) {
			jsonObj.put("error", e);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping(path = "/insert", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> insertObject(@RequestBody CRUDModel crudObj) {
		JSONObject jsonObj = new JSONObject();
		try {
			System.out.println(crudObj);
			jsonObj = crudService.insert(crudObj);
			System.out.println(jsonObj);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			jsonObj.put("error", e);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteObject(@RequestParam("id") long crudObjId) {
		JSONObject jsonObj = new JSONObject();
		try {
			System.out.println(crudObjId);
			jsonObj = crudService.delete(crudObjId);
			System.out.println(jsonObj);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			jsonObj.put("error", e);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateObject(
			@RequestParam long id,
			@RequestBody CRUDModel crudObj) {
			JSONObject jsonObj = new JSONObject();
		try {
			System.out.println(crudObj);
			jsonObj = crudService.update(id, crudObj);
			System.out.println(jsonObj);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			jsonObj.put("error", e);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/connect-auth")
	public ResponseEntity<String> getObject() {
		JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", true);
			System.out.print(jsonObj);
			return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
	
	}
	
}
