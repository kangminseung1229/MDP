package com.example.demo.MDP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class checkID {

    @Autowired
    mdpRepository Repo;

    public boolean checked(HttpServletRequest request, String user){
        boolean result = false;
        System.out.println(result);

        if(Repo.countByUser(user)>0 || (Repo.findByCode(user).get().getUser() != null)){
            HttpSession session = request.getSession();
            session.setAttribute("permission", "permission");
            result=true;
        }
        
        return result;
        
    }
    
    // public boolean checked(HttpSession session, String user){
    //     boolean result = false;
    //     System.out.println(result);

    //     if(Repo.countByUser(user)>0 || (Repo.findByCode(user).get().getUser() != null)){
    //         // HttpSession session = request.getSession();
    //         session.setAttribute("permission", "permission");
    //         result=true;
    //     }
        
    //     return result;
        
    // } 
    
    public void checkPermissions(HttpServletResponse response,String permission) throws IOException,ServletException{

        if(permission != "permission"){
            response.sendRedirect("/MDP/login");
        }
    }

    public void checkLoginOut(String permission,Model model){
        String loginOut = "LOGOUT";
        if(permission != "permission"){
            loginOut = "LOGIN";
            
        }
        model.addAttribute("loginOut",loginOut);
    }
}
