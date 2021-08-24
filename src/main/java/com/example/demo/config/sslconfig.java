package com.example.demo.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * sslconfig
 */
@Controller
@RequestMapping("/.well-known/pki-validation")
public class sslconfig {

    @GetMapping("/8CAFE936F6EA59FA793350D399F2A7DA.txt")
    public String permition(){
        return "8CAFE936F6EA59FA793350D399F2A7DA.txt";
    }

}