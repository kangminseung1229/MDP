package com.example.demo.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.DTO.admins;
import com.example.demo.Repository.adminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminLogin")
public class AdminLoginAjax {


    @Autowired
    private adminRepository adminRepo;
    
    @PostMapping("/AdminLoginAjax")
    public int AdminLoginAjax(@RequestParam String cd, @RequestParam String pw, HttpServletRequest request) {


        int permission = 0;

        HttpSession session = request.getSession();

        admins ad = adminRepo.findByCdAndPw(cd, pw);

        if (ad != null) {
            session.setAttribute("adminName", ad.getNm());
            permission = 1;
        }
        
        

        System.out.println(session.getAttribute("adminName"));


        return permission;
    }
    
    
}
