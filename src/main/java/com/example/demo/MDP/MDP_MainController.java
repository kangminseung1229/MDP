package com.example.demo.MDP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("loginOut",permission);
        return "MDP/main";
    }

    @GetMapping("/login")
    public String login() {
        return "MDP/login";
    }

    @PostMapping("/login")
    public String logined(HttpServletRequest request, String user,Model model) {

        if(checkID.checked(request, user)){
            return main(request, model);
        }
        else
            return "redirect:login";
    }
        

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        return "MDP/login";        
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
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);
        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);
        System.out.println("///"+permission+"///");
        return "MDP/letter";
    }

    @PostMapping("/letter")
    public String letterPost(HttpServletRequest request, Model model){
        String step1Me = request.getParameter("step1Me");
        model.addAttribute("step1Me", step1Me);
        String step1Text = request.getParameter("step1Text");
        model.addAttribute("step1Text", step1Text);
        String feeling = request.getParameter("feeling");
        model.addAttribute("feeling", feeling);
        System.out.println("---"+feeling+"---");
        String step3_textbox1 = request.getParameter("step3_textbox1");
        model.addAttribute("step3_textbox1", step3_textbox1);
        String step3_textbox2 = request.getParameter("step3_textbox2");
        model.addAttribute("step3_textbox2", step3_textbox2);
        String step3_textbox3 = request.getParameter("step3_textbox3");
        model.addAttribute("step3_textbox3", step3_textbox3);
        String step3_textbox4 = request.getParameter("step3_textbox4");
        model.addAttribute("step3_textbox4", step3_textbox4);
        String step3_textbox5 = request.getParameter("step3_textbox5");
        model.addAttribute("step3_textbox5", step3_textbox5);
        String step3_textbox6 = request.getParameter("step3_textbox6");
        model.addAttribute("step3_textbox6", step3_textbox6);
        String step3_textbox7 = request.getParameter("step3_textbox7");
        model.addAttribute("step3_textbox7", step3_textbox7);
        String step3_textbox8 = request.getParameter("step3_textbox8");
        model.addAttribute("step3_textbox8", step3_textbox8);
        String step3_textbox9 = request.getParameter("step3_textbox9");
        model.addAttribute("step3_textbox9", step3_textbox9);
        String step4Text1 = request.getParameter("step4Text1");
        model.addAttribute("step4Text1", step4Text1);
        String step4Text2 = request.getParameter("step4Text2");
        model.addAttribute("step4Text2", step4Text2);
        String step4Text3 = request.getParameter("step4Text3");
        model.addAttribute("step4Text3", step4Text3);
        String step4Text4 = request.getParameter("step4Text4");
        model.addAttribute("step4Text4", step4Text4);
        String step5Text1 = request.getParameter("step5Text1");
        model.addAttribute("step5Text1", step5Text1);
        String step5Text2 = request.getParameter("step5Text2");
        model.addAttribute("step5Text2", step5Text2);
        String step5Text3 = request.getParameter("step5Text3");
        model.addAttribute("step5Text3", step5Text3);
        String step5Text4 = request.getParameter("step5Text4");
        model.addAttribute("step5Text4", step5Text4);
        String step6_emotion_result = request.getParameter("step6_emotion_result");
        model.addAttribute("step6_emotion_result", step6_emotion_result);
        

        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);

        return "MDP/fin";
    }

    @PostMapping("/fin")
    public String finPost(Model model, String letterTo, String letterText, String letterFrom) throws IOException, ServletException {
        model.addAttribute("letterTo", letterTo);
        model.addAttribute("letterText", letterText);
        model.addAttribute("letterFrom", letterFrom);

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
        return "MDP/adminLogin";
    }

    @GetMapping("/adminJoin")
    public String admin_join(){
        return "MDP/adminJoin";
    }

    @PostMapping("/adminJoin")
    public String admin_join(SecurityAdmins sa) {
        
        // String encodedpw = pwEncoder.encode(sa.getPassword());
        // sa.setPassword(encodedpw);
        // sa.setEnabled(true);

        // SecurityRole sr = new SecurityRole();
        // sr.setId(1l);
        // sa.getRoles().add(sr);
        // saRepo.save(sa);
        // return "redirect:adminLogin";

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

}
