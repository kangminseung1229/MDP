package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public class purchaseCode {
    
    public String RandomCode(Model model,@RequestParam int count){
       

        Random random = new Random();
        ArrayList<String> code = new ArrayList<String>();
        
        for(int i =0; i<count; i++){
            // String num = Integer.toString();
            code.add("THINKUS_"+String.format("%05d",random.nextInt(100000)));
            // MDP_PurchaseCode user = new MDP_PurchaseCode();
        }
        System.out.println(code);
        model.addAttribute("code", code);
        
        return "MDP/manage";
    }
    
}
