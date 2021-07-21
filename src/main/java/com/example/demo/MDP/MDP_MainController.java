package com.example.demo.MDP;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("MDP")
public class MDP_MainController {

    @Autowired
    private mdpRepository mdpRepo;

    @GetMapping("/main")
    public String main() {
        return "MDP/main";
    }

    @GetMapping("/login")
    public String login() {
        return "MDP/login";
    }

    @GetMapping("/join")
    public String join() {
        return "MDP/join";
    }

    @GetMapping("/process")
    public String process() {
        return "MDP/process";
    }

    @GetMapping("/letter")
    public String letter() {
        return "MDP/letter";
    }

    @GetMapping("/fin")
    public String fin() {
        return "MDP/fin";
    }

    @GetMapping("/manage")
    public String manage(Model model,@RequestParam int count){


        MDP_PurchaseCode mp = new MDP_PurchaseCode();

        // int randomCHECK = 0;

        Random random = new Random();
        ArrayList<Long> id = new ArrayList<Long>();
        ArrayList<String> code = new ArrayList<String>();


        for(int i=0; i<count; i++){
            mp.setId(Long.valueOf(i+1l)); //id 설정
            mp.setCode("THINKUS_"+String.format("%02d",random.nextInt(15))); 
            
            if(mdpRepo.countByCode(mp.getCode())>0){
                i--;
                continue;
            }
            
            mdpRepo.save(mp);
            mdpRepo.flush();

            id.add(mp.getId());
            code.add(mp.getCode());
        }
        
        model.addAttribute("id", id);
        model.addAttribute("code", code);
        
        return "MDP/manage";
    }


    //board 만들기
    @GetMapping("/boardList")
    public String  boardList(Model model, @RequestParam(required = false, defaultValue = "") String searchText, @PageableDefault(size = 3) Pageable pageable) {

        Page<MDP_PurchaseCode> list = mdpRepo.findByUserContainingOrCodeContaining(searchText, searchText, pageable);
        
        int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
		int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
        model.addAttribute("list", list);
        return "MDP/boardList";
    }
 

}
