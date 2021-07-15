package com.example.demo.keyword;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * keywordController
 */

 @Controller
 @RequestMapping("/keyword")
public class keywordController {

    

    @GetMapping("keywordPage")
    public String keyword(){
        return "keyword/keywordPage";
    }

    
}