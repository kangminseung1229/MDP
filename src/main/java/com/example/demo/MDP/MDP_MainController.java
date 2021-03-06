package com.example.demo.MDP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MDP")
public class MDP_MainController {

    @Autowired
    private mdpRepository mdpRepo;

    @Autowired
    private checkID checkID;

    @GetMapping("/main")
    public String main(HttpServletRequest request, Model model) {

        // 세션 검사
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        String user = (String) session.getAttribute("user");
        model.addAttribute("loginOut", permission);
        model.addAttribute("user", user);

        return "MDP/main";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        // 세션 검사
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");

        // 로그인 상태면 로그인 화면으로 이동했을 때 메인 화면으로 이동
        if (permission == "LOGOUT")
            return "MDP/main";
        else
            return "MDP/login";
    }

    @PostMapping("/login")
    public String logined(HttpServletRequest request, String code, Model model, HttpServletResponse response)
            throws IOException {

        // 사용자가 아무것도 입력하지 않았을 때
        if (code == "") {
            return "redirect:login";
        }
        // 로그인 성공
        if (checkID.checked(request, code) == "true") {
            return main(request, model);
        }
        // 맞는 구매코드를 입력했는데 회원가입이 안되어있는 경우
        else if (checkID.checked(request, code) == "join") {
            return join(request, model, "join");
        }
        // 로그인 실패
        else {
            // 로그인 실패 알림창
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디/구매코드가 존재하지 않습니다.');history.go(-1);</script>");
            out.flush();

            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        // 로그아웃시 세션 삭제
        HttpSession session = request.getSession();
        session.invalidate();
        return "MDP/login";
    }

    @GetMapping("/join")
    public String join(HttpServletRequest request, Model model, String alert) {

        // 사용자가 회원가입시 입력한 코드 값을 전달
        model.addAttribute("code", request.getParameter("code"));
        model.addAttribute("alert", alert);

        return "/MDP/join";
    }

    @PostMapping("/join")
    public String joinPost(HttpServletRequest request, HttpServletResponse response, String user, String code,
            Model model) throws IOException {

        String alert;
        // 사용자가 아무것도 입력하지 않았을 때
        if (user == "" || code == "") {
            alert = "아이디와 구매코드를 입력해주세요";
            return join(request, model, alert);
        }
        // 회원가입 성공
        String joinChecked = checkID.checkJoin(request, user, code);

        if (joinChecked == "true") {
            mdpRepo.updateUser(user, code);
            return "MDP/login";
        } else {
            return join(request, model, joinChecked);
        }
    }

    @GetMapping("/process")
    public String process(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException, ServletException {

        // 세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut", permission);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user);

        checkID.checkPermissions(response, permission);

        // step6용 단어배열
        String[] common = { "진심의", "의지되는", "감사한", "걱정스러운", "기억에 남는", "친절한", "안심이 되는", "그리운", "배려하는", "위로의", "재미있는",
                "빛나는", "건강한", "밝은", "기대되는", "행복한", "든든한", "아름다운", "따스한", "보고싶은", "편안한", "신나는" };

        String[] thanks = { "찬란한", "정감 있는", "나를 위한", "감동적인", "베푸는", "인자한", "설레는", "벅찬", "보답하는", "사려깊은" };

        String[] sorry = { "늦었지만", "이제라도", "사과하는", "슬픈", "마음 아픈", "죄송한", "눈물나는", "속상한", "안타까운", "안쓰러운" };

        String[] celebration = { "긍정적인", "뿌듯한", "믿음직한", "따뜻한", "열정적인", "활발한", "생기 넘치는", "매력적인", "부지런한" };

        String[] love = { "응원하는", "여유로운", "하나뿐인", "사랑스러운", "오랫동안", "풍부한", "간직하고 싶은", "고운", "부드러운", "감싸주는" };

        String[] say = { "화사한", "아련한", "씁쓸한", "단단한", "외로운", "궁금한", "다시 예전처럼", "포근한", "잔잔한", "끈끈한" };

        model.addAttribute("common", common);
        model.addAttribute("thanks", thanks);
        model.addAttribute("sorry", sorry);
        model.addAttribute("celebration", celebration);
        model.addAttribute("love", love);
        model.addAttribute("say", say);

        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException, ServletException {

        // 세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut", permission);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user);

        checkID.checkPermissions(response, permission);
        return "MDP/letter";
    }

    @PostMapping("/letter")
    public String letterPost(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException, ServletException {

        // 세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");

        checkID.checkPermissions(response, permission);

        model.addAttribute("loginOut", permission);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user);

        // step1
        model.addAttribute("step1Me", request.getParameter("step1Me"));
        model.addAttribute("step1Text", request.getParameter("step1Text"));

        // step2
        model.addAttribute("feeling", request.getParameter("feeling"));

        // step3
        model.addAttribute("step3_textbox1", request.getParameter("step3_textbox1"));
        model.addAttribute("step3_textbox2", request.getParameter("step3_textbox2"));
        model.addAttribute("step3_textbox3", request.getParameter("step3_textbox3"));
        model.addAttribute("step3_textbox4", request.getParameter("step3_textbox4"));
        model.addAttribute("step3_textbox5", request.getParameter("step3_textbox5"));
        model.addAttribute("step3_textbox6", request.getParameter("step3_textbox6"));
        model.addAttribute("step3_textbox7", request.getParameter("step3_textbox7"));
        model.addAttribute("step3_textbox8", request.getParameter("step3_textbox8"));
        model.addAttribute("step3_textbox9", request.getParameter("step3_textbox9"));

        // step4
        model.addAttribute("step4Text1", request.getParameter("step4Text1"));
        model.addAttribute("step4Text2", request.getParameter("step4Text2"));
        model.addAttribute("step4Text3", request.getParameter("step4Text3"));
        model.addAttribute("step4Text4", request.getParameter("step4Text4"));

        // step5
        model.addAttribute("step5Text1", request.getParameter("step5Text1"));
        model.addAttribute("step5Text2", request.getParameter("step5Text2"));
        model.addAttribute("step5Text3", request.getParameter("step5Text3"));
        model.addAttribute("step5Text4", request.getParameter("step5Text4"));

        // step6
        model.addAttribute("step6_emotion_result", request.getParameter("step6_emotion_result"));

        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException, ServletException {

        // 세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        model.addAttribute("loginOut", permission);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user);

        checkID.checkPermissions(response, permission);

        return "MDP/fin";
    }

    @PostMapping("/fin")
    public String finPost(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException, ServletException {
        // 세션 체크
        HttpSession session = request.getSession();
        String permission = (String) session.getAttribute("permission");
        checkID.checkPermissions(response, permission);

        model.addAttribute("loginOut", permission);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("letterTo", request.getParameter("letterTo"));
        model.addAttribute("letterText", request.getParameter("letterText"));
        model.addAttribute("letterFrom", request.getParameter("letterFrom"));

        return "MDP/fin";
    }

}
