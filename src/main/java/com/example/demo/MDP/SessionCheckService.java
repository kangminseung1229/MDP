package com.example.demo.MDP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 사용자 세션을 통해 구매자만 입장
@Service
public class SessionCheckService {

    @Autowired
    mdpRepository mdpRepo;

    // 아이디 체크
    public boolean IdCheck(HttpServletRequest request, String user) {

        boolean result = false;
        Long mdpCount = mdpRepo.CountByUser(user);

        
        if (mdpCount > 0) {
            HttpSession session= request.getSession();
            session.setAttribute("permition", "permition");
            result = true;
        }

        return result;

    }

    //권한체크
    public void permitionCheck(HttpServletResponse response, String permition) throws IOException, ServletException{

        if (permition != "permition") {
            response.sendRedirect("/MDP/login");
        }
    }

}
