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
        //user가 입력됐을 경우 user의 개수, code가 입력됐을 경우 user가 null이 아닌 해당 code의 개수
        Long joinCount=Repo.countByUserOrCodeAndUserIsNotNull(user, user);

        //로그인 성공
        if(joinCount > 0){   
            session.setAttribute("permission", "LOGOUT");

            //사용자가 입력한 값이 코드일 경우
            if(Repo.countByCode(user) > 0)    
                session.setAttribute("user", Repo.findUser(user));  //Session에 user를 저장
            //사용자가 입력한 값이 아이디일 경우
            else if(Repo.countByUser(user) > 0)   
                session.setAttribute("user", user);
            result = "true";            
        }
        else {
            //맞는 구매코드를 입력했는데 회원가입이 안되어있는 경우 회원가입창으로 이동
            if(Repo.countByCode(user) > 0)   
                result = "join";
            //맞지 않은 구매코드 및 아이디를 입력한 경우
            else
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
    public String checkJoin(HttpServletRequest request, String user, String code){
        String result = "false";
        //입력된 코드가 유효한 코드인지 확인
        if(Repo.countByCode(code) > 0){
            //해당 코드가 사용중인지 확인
            if(Repo.countByCodeAndUserIsNull(code) > 0){
                //아이디가 중복인지 확인
                if(Repo.countByUser(user) == 0){
                    result="true"; //중복이 아닌 경우 회원가입 성공 -> true 반환
                }
                else
                    result="overlap"; //아이디가 중복된 경우 회원가입 실패 -> overlap 반환
            }   
            else
                result="used"; //입력된 코드가 이미 사용중인 경우 회원가입 실패 -> used 반환
        }
        return result;
    }
    //관리자 회원가입
    public boolean sacheckJoin(String user){
        boolean result = false;

        //관리자 아이디가 이미 존재하는 지 확인
        if((saRepo.countByUsername(user)==0)){
            result=true;
        }
        
        return result;
    }
}
