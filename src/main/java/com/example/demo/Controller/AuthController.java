package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.SecurityAdmins;
import com.example.demo.DTO.SecurityRole;
import com.example.demo.Repository.saRepository;
import com.example.demo.Repository.srRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private saRepository saRepo;

    @Autowired
    private srRepository srRepo;

    @Autowired
    private PasswordEncoder pwEncoder;
    


    @GetMapping("list")
    public String list(Model model, Pageable pageable){

        
        
        // Page<SecurityAdmins> list = saRepo.findAll();
        List<SecurityAdmins> list = saRepo.findAll();
        model.addAttribute("list", list);

        return "auth/authList";
    }


    @GetMapping("/write")
	public String write(Model model, @RequestParam(required = false) Long id) {


		if (id == null) {
			model.addAttribute("SecurityAdmins", new SecurityAdmins());
		} else {
            SecurityAdmins sa = saRepo.findById(id).orElse(null);
            model.addAttribute("SecurityAdmins", sa);
            model.addAttribute("userAuth", saRepo.strAuth(id));
		}


        //전체 권한 종류
        List<SecurityRole> srAll = srRepo.findAll();
        model.addAttribute("AuthAll", srAll);


        return "auth/authWrite";
        
    }
    
    @PostMapping("/write")
    public String write(Model model, SecurityAdmins sa, RedirectAttributes redirect){
        // adminService.save(sa);
        String encodedpw = pwEncoder.encode(sa.getPassword());
        sa.setPassword(encodedpw);

        saRepo.save(sa);
        redirect.addAttribute("id",sa.getId());
        return "redirect:write";
    }

}
