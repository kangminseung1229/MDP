package com.example.demo.RestController;

import java.util.List;

import com.example.demo.DTO.SecurityAdmins;
import com.example.demo.Repository.saRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestAuth {

    @Autowired
    private saRepository saRepo;

    @GetMapping("/list")
    public List<SecurityAdmins> auth() {

        return saRepo.findAll();
        // return saRepo.findByUsername("kschoi");
    }

    @Transactional
    @PostMapping(value = "/ChangeAuth")
    public int ChangeAuth(@RequestParam String auth, @RequestParam("userid") Long id) {
        // TODO: process POST request
        String[] strText = auth.split("/");

        int result = 1;

        try {

            saRepo.deleteAdminsRole(id);

            for (int i = 0; i < strText.length; i++)
                saRepo.updateAuth(id, Long.parseLong(strText[i]));

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            result = 0;
        }

        return result;
    }

}
