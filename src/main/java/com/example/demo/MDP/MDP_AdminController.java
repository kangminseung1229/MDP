package com.example.demo.MDP;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.MDP.MDP_Security_DTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("MDP")
public class MDP_AdminController {

    @Autowired
    private PasswordEncoder pwEncoder;

    @Autowired
    private saRepository saRepo;

    @Autowired
    private checkID checkID;
    
    // 관리자 페이지 컨트롤러
    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "MDP/adminLogin";
    }

    @GetMapping("/adminJoin")
    public String admin_join(){
        return "MDP/adminJoin";
    }

    @PostMapping("/adminJoin")
    public String admin_join(SecurityAdmins sa, String code) {

        //승인코드 검사
        System.out.println(code);
        if((code.equals("mdp2021"))==false){
            System.out.println(code == "mdp2021");
            return "redirect:adminJoin";
        }

        //비밀번호 암호화
        String encodedpw = pwEncoder.encode(sa.getPassword());
        sa.setPassword(encodedpw);
        sa.setEnabled(true);

        SecurityRole sr = new SecurityRole();
        if(checkID.sacheckJoin(sa.getUsername())){
            sr.setId(1l);
            sa.getRoles().add(sr);
            saRepo.save(sa);
            return "redirect:adminLogin";
        }
        else{
            return "redirect:adminJoin";
        }
    }

    @GetMapping("/adminLogout")
    public String adminLogout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        return "MDP/adminLogin";        
    }
}
