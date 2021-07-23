package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    // default page , root page 를 설정함.
    @GetMapping("/")
    public String index() {
        return "MDP/login";
    }

}
