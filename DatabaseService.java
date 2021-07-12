package com.example.tomcatappserver.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.sql.*;
@Component
public class DatabaseService {
	
		public JSONObject runQuery(String sql) throws Exception {
			JSONObject jsonObj = new JSONObject();
			Connection c = null;
		    Statement stmt = null;
		      
		      try {
		         Class.forName("org.sqlite.JDBC");
		         c = DriverManager.getConnection("jdbc:sqlite:test.db");
		         System.out.println("Opened database successfully");
		         stmt = c.createStatement();
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.close();
		         jsonObj.put("status", true);
		     	return jsonObj;
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		         throw e; 
		      }
		}
		
		public JSONObject get(String sql) throws Exception {
			JSONObject jsonObj = new JSONObject();
			Connection c = null;
		    Statement stmt = null;
		      try {
		         Class.forName("org.sqlite.JDBC");
		         c = DriverManager.getConnection("jdbc:sqlite:test.db");
		         System.out.println("Opened database successfully");
		         stmt = c.createStatement();
		         // always set fetch size. approx minimum buffer will be >=100;
		         // setFetchSize throws error if rows<=0;
		         stmt.setFetchSize(1); 
		         
		         ResultSet rs    = stmt.executeQuery(sql);
		         JSONArray array = new JSONArray();
		         while(rs.next()) {
		        	 JSONObject temp = new JSONObject();
		        	 temp.put("id", rs.getLong("ID"));
		        	 temp.put("name", rs.getString("NAME"));
		        	 temp.put("age", rs.getInt("AGE"));
		        	 array.put(temp);
		         }
		         jsonObj.put("items", array);
		         stmt.close();
		         c.close();
		         System.out.println("items");
		         System.out.println(jsonObj);
		     	return jsonObj;
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		         throw e; 
		      }
		}
		
		
		
		
}
