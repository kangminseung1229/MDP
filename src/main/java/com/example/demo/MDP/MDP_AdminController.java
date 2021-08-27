package com.example.demo.MDP;

import org.springframework.ui.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.MDP.MDP_Security_DTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("MDPadmin")
public class MDP_AdminController {

    @Autowired
    private mdpRepository mdpRepo;

    @Autowired
    private PasswordEncoder pwEncoder;

    @Autowired
    private saRepository saRepo;

    @Autowired
    private checkID checkID;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }

    @GetMapping("/join")
    public String admin_join() {
        return "admin/join";
    }

    @PostMapping("/join")
    public String adminJoin(SecurityAdmins sa, String code, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 승인코드 검사
        if ((code.equals("mdp2021")) == false) {
            out.println("<script>alert('승인코드가 옳지 않습니다');history.go(-1);</script>"); //승인코드가 틀릴 경우 alert창 발생
            out.flush();
            return "redirect:join";
        }

        // 비밀번호 암호화
        String encodedpw = pwEncoder.encode(sa.getPassword());
        sa.setPassword(encodedpw);
        sa.setEnabled(true);

        SecurityRole sr = new SecurityRole();
        
        //아이디 중복검사
        if (checkID.sacheckJoin(sa.getUsername())) {
            sr.setId(1l);
            sa.getRoles().add(sr);
            saRepo.save(sa);
            return "redirect:login";
        } else {
            out.println("<script>alert('이미 존재하는 아이디입니다');history.go(-1);</script>");
            out.flush();
            return "redirect:join";
        }
    }

    @GetMapping("/manage")
    public String manage(Model model, @RequestParam(required = false, defaultValue = "") String searchText,
            @PageableDefault(size = 15) Pageable pageable) {
        // 페이징
        Page<mdpPurchaseCode> page;

        if(searchText.equals("")){
            page = mdpRepo.findAll(pageable);
        }
        else{
            page = mdpRepo.manageSearch(searchText, pageable);
        }

        int startPage = Math.max(1, Math.min(page.getPageable().getPageNumber()-1, page.getTotalPages()-4));
		int endPage = Math.min(page.getTotalPages(), startPage + 4);
        if(page.getTotalPages()==0){
            endPage=1;
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("total", page.getTotalPages()); // 총 페이지 수
        model.addAttribute("pages", page.getPageable().getPageNumber()); // 현재 페이지
        model.addAttribute("list", page);

        return "admin/manage";
    }

    @GetMapping("/add")
    public String add(Model model, @RequestParam int count) {
        mdpPurchaseCode mp = new mdpPurchaseCode();

        Random random = new Random();
        long last = mdpRepo.lastColumn();

        for (int i = 0; i < count; i++) {
            String pc = "THINKUS_";
            mp.setId(Long.valueOf(i + last + 1l)); // id 설정
            for (int j = 0; j < 5; j++) {
                int rd = random.nextInt(2);
                char ch = (char) ((Math.random() * 26) + 65);
                if (rd == 1) {
                    pc = pc.concat(Integer.toString(random.nextInt(10)));
                } else {
                    pc = pc.concat(Character.toString(ch));
                }
            }
            mp.setCode(pc);

            // 중복 제거
            if (mdpRepo.countByCode(mp.getCode()) > 0) {
                i--;
                continue;
            }

            mdpRepo.save(mp);
            mdpRepo.flush();
        }

        return "redirect:manage";

    }

    @GetMapping("/adminLogout")
    public String adminLogout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "admin/login";
    }
}
