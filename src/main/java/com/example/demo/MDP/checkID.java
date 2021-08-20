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
    public String checked(HttpServletRequest request, String user){
        String result = "false";
        HttpSession session = request.getSession();
        Long joinCount=Repo.countByUserOrCodeAndUserIsNotNull(user, user);
        Long codeCount=Repo.countByCode(user);
        //로그인 성공
        if(joinCount >0){   
            session.setAttribute("permission", "LOGOUT");
            result = "true";            
        }
        else {
            if(codeCount > 0)   //맞는 구매코드를 입력했는데 회원가입이 안되어있는 경우
                result = "join";    
            else
                session.setAttribute("permission", "LOGIN");    //맞지 않은 구매코드 및 아이디를 입력한 경우
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
    public String checkJoin(HttpServletRequest request, String user, String code){
        String result = "false";
        System.out.println("AAA");
        //코드가 있는지 
        if(Repo.countByCode(code)>0){
            //해당 코드가 사용중인지
            if(Repo.countByCodeAndUserIsNull(code) >0){
                //아이디가 중복인지 확인
                if(Repo.countByUser(user)==0){
                    result="true";
                    System.out.println("true");
                }
                else
                    result="overlap";
            }   
            else
                result="used";
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
