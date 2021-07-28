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

    @Autowired
    private checkID checkID;

    @Autowired
    private MDP_MainController mainController;

    // default page , root page 를 설정함.
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model) {
        // HttpSession session = request.getSession();
        // String permission = (String) session.getAttribute("permission");
        // checkID.checkLoginOut(permission,model);
        // return "/MDP/main";
        return mainController.main(request, model);
    }
    // public String index(){
    //     return "MDP/login";
    // }

}
