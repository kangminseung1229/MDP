package com.example.demo.Controller;

import com.example.demo.DTO.SecurityAdmins;
import com.example.demo.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LonginController {

    @Autowired
    private AdminService adminService;


    // spring security가 감지해서 컨트롤함
    // @GetMapping("/login")
    // public String login(HttpServletRequest request) {

    // HttpSession session = request.getSession();

    // if (session.getAttribute("greeting") == null) {
    // return "/login";

    // } else {
    // return "/board/list";
    // }

    // }

    @PostMapping("/register")
    public String register(SecurityAdmins sa) {
        adminService.save(sa);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // successhandler 가 대체하므로 defaulturl 은 사용하지 않는다.
    // @GetMapping("/sessionIns")
    // public String sessionIns(HttpServletRequest request) {
    // adminService.sessionIns(request);
    // return "redirect:/board/list";
    // }

    @GetMapping("/test/sessiontest")
    public String sessiontest() {
        return "test/sessiontest";
    }

}
