package com.code.MTA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyService {

    private final JdbcTemplate firstJdbcTemplate;

    @Autowired
    public MyService(@Qualifier("firstJdbcTemplate") JdbcTemplate firstJdbcTemplate){
        this.firstJdbcTemplate = firstJdbcTemplate;
    }


    public List<Map<String, Object>> getAllRecords() {
        String sql = "SELECT * FROM usermaster";
        return firstJdbcTemplate.queryForList(sql);
    }

    public  String  createSchema(String schemaName){
        try {
            String createSchemaSql = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
            firstJdbcTemplate.execute(createSchemaSql);

            String createTableSql = "CREATE TABLE IF NOT EXISTS " + schemaName + ".usermast (" +
                    "id INT PRIMARY KEY, " +
                    "column1 VARCHAR(255), " +
                    "column2 INT" +
                    ")";
            firstJdbcTemplate.execute(createTableSql);

            String createTableSql1 = "CREATE TABLE IF NOT EXISTS " + schemaName + ".tranmast (" +
                    "id INT PRIMARY KEY, " +
                    "column1 VARCHAR(255), " +
                    "column2 INT" +
                    ")";
            firstJdbcTemplate.execute(createTableSql1);

            String createTableSql2 = "CREATE TABLE IF NOT EXISTS " + schemaName + ".login (" +
                    "id INT PRIMARY KEY, " +
                    "column1 VARCHAR(255), " +
                    "column2 INT" +
                    ")";
            firstJdbcTemplate.execute(createTableSql2);

            // Construct the JDBC URL for the new schema
            String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres?currentSchema=" + schemaName;

            // Log schema creation in the common schema's my_table
            String logSchemaCreationSql = "INSERT INTO public.schemas_log (schema_name, created_by, jdbc_url, username, password, driver) VALUES (?, ?, ?, ?, ?, ?)";
            firstJdbcTemplate.update(logSchemaCreationSql, schemaName, "system", jdbcUrl, "postgres", "1234", "org.postgresql.Driver");

            return "Schema '" + schemaName + "' and tables created successfully!";
        } catch (Exception e) {
            return "Error creating schema or table: " + e.getMessage();
        }
    }
}


