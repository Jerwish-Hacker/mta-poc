package com.code.MTA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
   
    public void createTable() {
    	 System.out.println("Executing createTable method...");
    	  // Create the tenant_schemas table in the public schema to track all created schemas
         String createTableSql = "CREATE TABLE IF NOT EXISTS public.schemas_log (" +
                 "id SERIAL PRIMARY KEY, " +
                 "schema_name VARCHAR(255) NOT NULL, " +
                 "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                 "created_by VARCHAR(255), " +
                 "status VARCHAR(50) DEFAULT 'active', " +
                 "jdbc_url VARCHAR(255) NOT NULL, " +
                 "username VARCHAR(255) NOT NULL, " +
                 "password VARCHAR(255) NOT NULL, " +
                 "driver VARCHAR(255) NOT NULL" +
                 ")";

         jdbcTemplate.execute(createTableSql);
     }
}
