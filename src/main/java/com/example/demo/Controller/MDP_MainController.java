package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MDP")
public class MDP_MainController {
    @GetMapping("/main")
    public String main(){
        return "MDP/main";
    }
    @GetMapping("/login")
    public String login(){
        return "MDP/login";
    }
    @GetMapping("/join")
    public String join(){
        return "MDP/join";
    }
    @GetMapping("/process")
    public String process(){
        return "MDP/process";
    }
    @GetMapping("/letter")
    public String letter(){
        return "MDP/letter";
    }
}

