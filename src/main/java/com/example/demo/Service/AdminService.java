package com.example.demo.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.DTO.SecurityAdmins;
import com.example.demo.DTO.SecurityRole;
import com.example.demo.Repository.saRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AdminService
 */

 @Service
public class AdminService {


    @Autowired
    private saRepository saRepo;    


    @Autowired
    private PasswordEncoder pwEncoder;

    //관리자 계정 생성
    public SecurityAdmins save(SecurityAdmins sa){


        String encodedpw = pwEncoder.encode(sa.getPassword());

        sa.setPassword(encodedpw);
        sa.setEnabled(true);

        
        SecurityRole sr = new SecurityRole();
        sr.setId(1l);


        sa.getRoles().add(sr);
        return saRepo.save(sa);
    }

    //비밀번호 변경
    public SecurityAdmins update(SecurityAdmins sa){
        String encodedpw = pwEncoder.encode(sa.getPassword());
        sa.setPassword(encodedpw);
        
        return saRepo.save(sa);

    }
    

    public void sessionIns(HttpServletRequest request) {


        HttpSession session = request.getSession();

        session.setAttribute("value1", "msk");
        
    }


}