package com.example.demo.MDP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class checkID {

    @Autowired
    mdpRepository Repo;

    @Autowired
    saRepository saRepo;

    public boolean checked(HttpServletRequest request, String user){
        boolean result = false;
        System.out.println(result);
        if(Repo.countByUser(user)>0){
            HttpSession session = request.getSession();
            session.setAttribute("permission", "LOGOUT");
            result=true;
        }
        else if(Repo.countByCode(user)>0){
            if(Repo.findByCode(user).get().getUser()!=null){
                HttpSession session = request.getSession();
                session.setAttribute("permission", "LOGOUT");
                result=true;
            }
        }
        
        return result;
        
    }
    
    public void checkPermissions(HttpServletResponse response,String permission) throws IOException,ServletException{

        if(permission != "LOGOUT"){
            response.sendRedirect("/MDP/login");
        }
    }

    public boolean checkJoin(HttpServletRequest request, String user, String code){
        boolean result = false;

        if(Repo.countByCode(code)>0 && (Repo.countByCodeAndUserIsNull(code) >0) && (Repo.countByUser(user)==0)){
            HttpSession session = request.getSession();
            session.setAttribute("permission", "permission");
            result=true;
        }
        
        return result;
        
    }

    public boolean sacheckJoin(String user){
        boolean result = false;

        if((saRepo.countByUsername(user)==0)){
            result=true;
        }
        
        return result;
        
    }

}
