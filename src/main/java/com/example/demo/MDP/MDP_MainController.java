package com.example.demo.MDP;

import java.io.IOException;
import java.io.PrintWriter;
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

        //세션 검사
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
    public String logined(HttpServletRequest request, String user,Model model, HttpServletResponse response) throws IOException {

        //사용자가 아무것도 입력하지 않았을 때
        if(user==""){
            return "redirect:login";
        }
        //로그인 성공
        if(checkID.checked(request, user)){
            return main(request, model);
        }
        else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디/구매코드가 존재하지 않습니다.');history.go(-1);</script>");
            out.flush();

            return "redirect:login";
        }
    }
        
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){

        //로그아웃시 세션 삭제
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

        //회원가입 성공
        if(checkID.checkJoin(request, user, code)){
            mdpRepo.updateUser(user, code);
            return "MDP/login";
        }  
        else
            return "MDP/join";
    }

    @GetMapping("/process")
    public String process(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException  {

        //세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);

        String[] common = {"진심의","의지되는","감사한","걱정스러운","기억에 남는",
        "친절한","안심이 되는","그리운","배려하는","위로의","재미있는",
        "빛나는","건강한","밝은","기대되는","행복한","든든한","아름다운",
        "따스한","보고싶은","편안한","신나는"};

        String[] thanks = {"찬란한","정감 있는","나를 위한","감동적인","베푸는",
        "인자한","설레는","벅찬","보답하는","사려깊은"};

        String[] sorry = {"늦었지만","이제라도","사과하는","슬픈","마음 아픈",
        "죄송한","눈물나는","속상한","안타까운","안쓰러운"};

        String[] celebration = {"긍정적인","뿌듯한","믿음직한","따뜻한","열정적인",
        "활발한","생기 넘치는","매력적인","부지런한"};

        String[] love = {"응원하는","여유로운","하나뿐인","사랑스러운","오랫동안",
        "풍부한","간직하고 싶은","고운","부드러운","감싸주는"};

        String[] say = {"화사한","아련한","씁쓸한","단단한","외로운",
        "궁금한","다시 예전처럼","포근한","잔잔한","끈끈한"};
        
        model.addAttribute("common", common);
        model.addAttribute("thanks", thanks);
        model.addAttribute("sorry", sorry);
        model.addAttribute("celebration", celebration);
        model.addAttribute("love", love);
        model.addAttribute("say", say);
        
        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        
        //세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);
        return "MDP/letter";
    }

    @PostMapping("/letter")
    public String letterPost(HttpServletRequest request, Model model){

        //step1
        model.addAttribute("step1Me", request.getParameter("step1Me"));
        model.addAttribute("step1Text", request.getParameter("step1Text"));

        //step2
        model.addAttribute("feeling", request.getParameter("feeling"));

        //step3
        model.addAttribute("step3_textbox1", request.getParameter("step3_textbox1"));
        model.addAttribute("step3_textbox2", request.getParameter("step3_textbox2"));
        model.addAttribute("step3_textbox3", request.getParameter("step3_textbox3"));
        model.addAttribute("step3_textbox4", request.getParameter("step3_textbox4"));
        model.addAttribute("step3_textbox5", request.getParameter("step3_textbox5"));
        model.addAttribute("step3_textbox6", request.getParameter("step3_textbox6"));
        model.addAttribute("step3_textbox7", request.getParameter("step3_textbox7"));
        model.addAttribute("step3_textbox8", request.getParameter("step3_textbox8"));
        model.addAttribute("step3_textbox9", request.getParameter("step3_textbox9"));

        //step4
        model.addAttribute("step4Text1", request.getParameter("step4Text1"));
        model.addAttribute("step4Text2", request.getParameter("step4Text2"));
        model.addAttribute("step4Text3", request.getParameter("step4Text3"));
        model.addAttribute("step4Text4", request.getParameter("step4Text4"));

        //step5
        model.addAttribute("step5Text1", request.getParameter("step5Text1"));
        model.addAttribute("step5Text2", request.getParameter("step5Text2"));
        model.addAttribute("step5Text3", request.getParameter("step5Text3"));
        model.addAttribute("step5Text4", request.getParameter("step5Text4"));

        //step6
        model.addAttribute("step6_emotion_result", request.getParameter("step6_emotion_result"));

        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        
        //세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut",permission);
        checkID.checkPermissions(response,permission);

        return "MDP/fin";
    }

    @PostMapping("/fin")
    public String finPost(Model model, HttpServletRequest request){

        model.addAttribute("letterTo", request.getParameter("letterTo"));
        model.addAttribute("letterText", request.getParameter("letterText"));
        model.addAttribute("letterFrom", request.getParameter("letterFrom"));

        return "MDP/fin";
    }

    @GetMapping("admin/manage")
    public String manage(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 15) Pageable pageable){

        //페이징
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
        
        //아이디 검색
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
