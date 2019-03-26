package com.ProjetCreationAPIREST;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import com.controller.TestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackageClasses=TestController.class)

public class Application
{ 
	private static final String BDD_URL;
	private static final String BDD_USER;
	private static final String BDD_PASSWORD;

	public static void main( String[] args )
    {
        try {
        	SpringApplication.run(Application.class,args);
        }
        catch (Exception e) {
        	System.out.println("Application erreur "+e);
        }
        
        try {
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		Connection conn = DriverManager.getConnection(BDD_URL,BDD_USER,BDD_PASSWORD);
    		Statement stmt = conn.createStatement();
    		String sql_query = "SELECT * FROM `ville_france`";
    		ResultSet result = stmt.executeQuery(sql_query);
    		System.out.println("SQL QUERY :"+ sql_query);
    		
    		while(result.next()) { 
    			System.out.println("*****************");
    			System.out.println(result.getString("Code_commune_INSEE"));
    			System.out.println(result.getString("Nom_commune"));
    			System.out.println(result.getString("Code_postal"));
    			System.out.println(result.getString("Libelle_acheminement"));
    			System.out.println("*****************");
    	
    		}
    		
    		result.close();
    		stmt.close();
    		conn.close();
    		
    		}
    	
    		
    		
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
}
