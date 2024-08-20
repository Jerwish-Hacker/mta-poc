package com.code.MTA.controller;


import com.code.MTA.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    public MyService myService;

    @GetMapping
    public List<Map<String, Object>> getall(){
        return myService.getAllRecords();
    }

    @PostMapping("/createdb")
    public String createSchema(@RequestParam String schemaName) {
            return  myService.createSchema(schemaName);
    }
}
