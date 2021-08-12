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

    //로그인 성공 확인 함수
    public boolean checked(HttpServletRequest request, String user){
        boolean result = false;
        HttpSession session = request.getSession();
        Long count=Repo.countByUserOrCodeAndUserIsNotNull(user, user);

        //로그인 성공
        if(count >0){
            session.setAttribute("permission", "LOGOUT");
            result = true;            
        }
        else {
            session.setAttribute("permission", "LOGIN");
        }
        return result;
    }
    
    //권한 확인 함수
    public void checkPermissions(HttpServletResponse response,String permission) throws IOException,ServletException{
        if(permission != "LOGOUT"){
            response.sendRedirect("/MDP/login");
        }
    }

    //회원가입 확인 함수
    public boolean checkJoin(HttpServletRequest request, String user, String code){
        boolean result = false;
        //코드가 있는지, 해당 코드가 사용중인지, 아이디가 중복인지 확인
        if(Repo.countByCode(code)>0 && (Repo.countByCodeAndUserIsNull(code) >0) && (Repo.countByUser(user)==0)){
            HttpSession session = request.getSession();
            session.setAttribute("permission", "permission");
            result=true;
        }
        
        return result;
    }
    //관리자 회원가입
    public boolean sacheckJoin(String user){
        boolean result = false;

        if((saRepo.countByUsername(user)==0)){
            result=true;
        }
        
        return result;
    }
}
