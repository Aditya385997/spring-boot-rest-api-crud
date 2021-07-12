package com.example.tomcatappserver.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tomcatappserver.models.CRUDModel;


@Component
public class CRUDService {
	
	private final DatabaseService databaseService;
	
	@Autowired
	public CRUDService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public JSONObject createCRUDTable() throws Exception {
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "CREATE TABLE user " +
		                        "(ID INT PRIMARY KEY     NOT NULL," +
		                        " NAME           TEXT    NOT NULL, " + 
		                        " AGE            INT     NOT NULL)"; 
			         
			jsonObj = databaseService.runQuery(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public JSONObject insert(CRUDModel crudObj) throws Exception {
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "INSERT INTO user(ID, NAME, AGE) VALUES(\""+crudObj.getId()+
					"\",\""+crudObj.getName()+"\",\""+crudObj.getAge()+"\")";
			jsonObj = databaseService.runQuery(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}

	public JSONObject delete(long crudObjId) throws Exception {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "DELETE FROM user WHERE ID ="+crudObjId;
			jsonObj = databaseService.runQuery(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}

	public JSONObject get(long id) throws Exception  {
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "SELECT * FROM user WHERE ID="+id;
			jsonObj = databaseService.get(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public JSONObject get() throws Exception  {
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "SELECT * FROM user";
			jsonObj = databaseService.get(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}

	public JSONObject update(long id, CRUDModel crudObj) throws Exception {
		try {
			JSONObject jsonObj = new JSONObject();
			String sql = "UPDATE user SET name=\""+crudObj.getName()+"\",age=\""+crudObj.getAge()+"\" WHERE ID=\""+id+"\"";
			jsonObj = databaseService.runQuery(sql);
			return jsonObj;
		} catch(Exception e) {
			throw e;
		}
	}
	
	
}
