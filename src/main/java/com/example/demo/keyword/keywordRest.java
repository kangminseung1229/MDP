package com.example.demo.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keywordRest")
public class keywordRest {
    
    @Autowired
    ApiService apiservice;

    @GetMapping("send")
    public String send(@RequestParam(required = false, defaultValue = "") String keywordText){

        System.out.println("send¸send¸send¸send¸send¸send¸send¸send¸send¸send¸");
        return apiservice.apiServiceTest("", "", "", "", "", keywordText);

    }

    @GetMapping("test")
    public String test(){
        return "testmessage";
    }
}
