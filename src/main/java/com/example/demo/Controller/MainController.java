package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController{

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        // 세션을 받고 
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        String user = (String) session.getAttribute("user");
        model.addAttribute("loginOut", permission);
        model.addAttribute("user", user);

        return "MDP/main";      
    }

}
