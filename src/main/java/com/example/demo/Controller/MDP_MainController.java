package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.example.demo.MDP.MDP_PurchaseCode;
import com.example.demo.MDP.mdpRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String manage(Model model,@RequestParam int count){


        MDP_PurchaseCode mp = new MDP_PurchaseCode();

        // int randomCHECK = 0;

        Random random = new Random();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<Long> id = new ArrayList<Long>();
        ArrayList<Date> dateTime = new ArrayList<Date>();

        for(int i=0; i<count; i++){
            mp.setId(Long.valueOf(i+1l)); //id 설정
            mp.setCode("THINKUS_"+String.format("%02d",random.nextInt(10))); 
            
            if(mdpRepo.countByCode(mp.getCode())>0){
                i--;
                continue;
            }
            
            mdpRepo.save(mp);
            mdpRepo.flush();
            code.add(mp.getCode());
            id.add(mp.getId());
            dateTime.add(mp.getDateTime());
        }
        

        model.addAttribute("code", code);
        model.addAttribute("id", id);
        

        return "MDP/manage";
    }
}
