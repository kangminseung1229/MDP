package com.example.demo.MDP;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import com.example.demo.SecurityDTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityRole;

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
    private SessionCheckService sc;


    @GetMapping("/main")
    public String main(){
        return "MDP/main";
    }

    @GetMapping("/login")
    public String login() {
        return "MDP/login";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, String user) {
        if(sc.CheckId(request, user))
            return "MDP/main";
        else
            return "MDP/login";
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
    public String joinPost() {
        return "MDP/join";
    }

    @GetMapping("/process")
    public String process(HttpServletResponse response, HttpServletRequest request ) throws IOException, ServletException {

        HttpSession session=request.getSession();
        String permition=(String) session.getAttribute("permition");

        sc.permitionCheck(response, permition);

        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter(HttpServletResponse response, HttpServletRequest request ) throws IOException, ServletException {

        HttpSession session=request.getSession();
        String permition=(String) session.getAttribute("permition");

        sc.permitionCheck(response, permition);

        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin(HttpServletResponse response, HttpServletRequest request ) throws IOException, ServletException {

        HttpSession session=request.getSession();
        String permition=(String) session.getAttribute("permition");

        sc.permitionCheck(response, permition);

        return "MDP/fin";
    }


    @GetMapping("admin/manage")
    public String manage(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){

        Page<mdpPurchaseCode> page = mdpRepo.findAll(pageable);
        // Page<mdpPurchaseCode> page = mdpRepo.findByOrderByIdDesc(pageable);
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
