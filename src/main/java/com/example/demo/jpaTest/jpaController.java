package com.example.demo.jpaTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/*

jpa test

*/
@Controller
@RequestMapping("/test")
public class jpaController {

    @Autowired
    private jpaRepo repo;

    @GetMapping("/jpatest")
    public String jpatest(Model model){



        model.addAttribute("RS1", repo.findBytestFlag("flag1"));
        model.addAttribute("RS2", repo.findBytestflag2("flag2"));
        


        



        return "test/jpatest";
    }


}
