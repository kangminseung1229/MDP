package com.example.demo.MDP;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.MDP.MDP_Security_DTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityRole;

import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("MDP")
public class MDP_MainController {

    @Autowired
    private mdpRepository mdpRepo;

    @Autowired
    private PasswordEncoder pwEncoder;

    @Autowired
    private saRepository saRepo;

    @Autowired
    private checkID checkID;


    @GetMapping("/main")
    public String main(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        checkID.checkLoginOut(permission,model);
        return "MDP/main";
    }

    @GetMapping("/login")
    public String login() {
        return "MDP/login";
    }

    @PostMapping("/login")
    public String logined(HttpServletRequest request, String user,Model model) {
        System.out.println(user);
        if(checkID.checked(request, user)){
            return main(request, model);
        }
        else
            return "redirect:login";
    }
        

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "MDP/main";
    }

    @GetMapping("/join")
    public String join() {
        return "MDP/join";
    }
    @PostMapping("/join")
    public String joinPost(HttpServletRequest request, String user, String code, Model model) {

        if(checkID.checkJoin(request, user, code)){
            mdpRepo.updateUser(user, code);
            return "MDP/login";
        }  
        else
            return "MDP/join";
    }

    @GetMapping("/process")
    public String process(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException  {

        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");

        checkID.checkLoginOut(permission,model);
        checkID.checkPermissions(response,permission);

        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");

        checkID.checkLoginOut(permission,model);
        checkID.checkPermissions(response,permission);

        return "MDP/letter";
    }

    @PostMapping("/letter")
    public String letterPost(HttpServletRequest request, HttpServletResponse response, Model model, String feeling) throws IOException, ServletException {
        
        System.out.println(feeling);
        

        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");

        checkID.checkLoginOut(permission,model);
        checkID.checkPermissions(response,permission);

        return "MDP/fin";
    }


    @GetMapping("admin/manage")
    public String manage(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){

        Page<mdpPurchaseCode> page = mdpRepo.findAll(pageable);
        int startPage = Math.max(1, page.getPageable().getPageNumber() - 1);
        int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 3);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("list", page);
        
        return "MDP/manage";
    }

    @GetMapping("admin/search")
    public String search(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){
        Page<mdpPurchaseCode> page = mdpRepo.findByUser(searchText,pageable);
        int startPage = Math.max(1, page.getPageable().getPageNumber() - 9);
      int endPage = Math.min(page.getTotalPages(), page.getPageable().getPageNumber() + 9);

      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
        model.addAttribute("list", page);
        
        return "MDP/manage";
    }


    @GetMapping("admin/add")
    public String add(Model model, @RequestParam int count){
        mdpPurchaseCode mp = new mdpPurchaseCode();

        Random random = new Random();
        long last=mdpRepo.lastColumn();

        for(int i=0; i<count; i++){

            mp.setId(Long.valueOf(i+last+1l)); //id 설정
            mp.setCode("THINKUS_"+String.format("%05d",random.nextInt(10000))); 
            
            //중복 제거
            if(mdpRepo.countByCode(mp.getCode())>0){
                i--;
                continue;
            }
            
            mdpRepo.save(mp);
            mdpRepo.flush();
        }

        return "redirect:manage";
        
    }


    // 관리자페이지컨트롤러

    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "MDP/admin_login";
    }

    @GetMapping("/adminJoin")
    public String admin_join(){
        return "MDP/admin_join";
    }

    @PostMapping("/adminJoin")
    public String admin_join(SecurityAdmins sa) {
        
        String encodedpw = pwEncoder.encode(sa.getPassword());
        sa.setPassword(encodedpw);
        sa.setEnabled(true);

        SecurityRole sr = new SecurityRole();
        sr.setId(1l);
        sa.getRoles().add(sr);
        saRepo.save(sa);
        return "redirect:admin_login";
        
    
    }

}
