package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.MDP.MDP_PurchaseCode;
import com.example.demo.MDP.mdpRepository;

import org.springframework.beans.factory.annotation.Autowired;

// import com.example.demo.Service.AdminService;
// import com.example.demo.DTO.MDP_PurchaseCode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("MDP")
public class MDP_MainController {


    @Autowired
    private mdpRepository mdpRepo;

    @GetMapping("/main")
    public String main(){
        return "MDP/main";
    }
    @GetMapping("/login")
    public String login(){
        return "MDP/login";
    }
    @GetMapping("/join")
    public String join(){
        return "MDP/join";
    }
    @GetMapping("/process")
    public String process(){
        return "MDP/process";
    }
    @GetMapping("/letter")
    public String letter(){
        return "MDP/letter";
    }
    @GetMapping("/fin")
    public String fin(){
        return "MDP/fin";
    }
    @GetMapping("/manage")
    public String manage(){
        return "MDP/manage";
    }


    //java random
    @GetMapping("/random")
    public String random(Model model, @RequestParam int count){

        MDP_PurchaseCode mp = new MDP_PurchaseCode();

        // int randomCHECK = 0;

        Random random = new Random();
        ArrayList<String> code = new ArrayList<String>();
        
        // mp.setId(1l);
        // System.out.println(mp.getId());
        // mp.setCode("code1234");
        // mdpRepo.save(mp);
        

        // mp.setCode("THINKUS_53723");
        // randomCHECK = codeRepo.randomCHECK(mp.getCode());

        // model.addAttribute("randomCheck", randomCHECK);

        

        for(int i =0; i<count; i++){
            mp.setId(Long.valueOf(i+1l));
            mp.setCode("THINKUS_"+String.format("%05d",random.nextInt(100000)));
            //select 문 필요 개수가 존재하는 여부에따라서

            mdpRepo.save(mp);
            mdpRepo.flush();
            code.add("THINKUS_"+String.format("%05d",random.nextInt(100000)));
        }
        

        model.addAttribute("code", code);
        return "MDP/random";
    }
}

