package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.MDP.MDP_MainController;
import com.example.demo.MDP.checkID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

<<<<<<< HEAD
    // @Autowired
    // private checkID checkID;

    // @Autowired
    // private MDP_MainController mainController;

=======
>>>>>>> b259bd4e5ec5bed1126209e4e3b1a9e835816fed
    // default page , root page 를 설정함.
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
<<<<<<< HEAD
        return "/MDP/main";
    }
    // @GetMapping("/")
    // public String index(){
    //     return "MDP/main";
    // }
=======

        return "/MDP/main";      
    }

>>>>>>> b259bd4e5ec5bed1126209e4e3b1a9e835816fed

}
