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

            return "Schema '" + schemaName + "' and table  created successfully!";
        } catch (Exception e) {
            return "Error creating schema or table: " + e.getMessage();
        }
    }
}


