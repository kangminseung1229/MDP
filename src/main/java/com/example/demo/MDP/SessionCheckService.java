package com.example.demo.MDP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionCheckService{
    
    @Autowired
    mdpRepository mdpRepo;

    public boolean CheckId(HttpServletRequest request, String user){

        boolean result=false;
        Long mdpCount=mdpRepo.countByUser(user);

        if(mdpCount>0){
            HttpSession session=request.getSession();
            session.setAttribute("permition", "permition");
            result=true;
        }

        return result;
    }

    public void permitionCheck(HttpServletResponse response, String permition) throws IOException, ServletException{
        if(permition!="permition"){
            response.sendRedirect("/MDP/login");
        }

    }
}
