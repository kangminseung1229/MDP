package com.example.demo.MDP;

// import com.example.demo.SecurityDTO.SecurityAdmins;
import com.example.demo.MDP.MDP_Security_DTO.SecurityAdmins;

import org.springframework.data.jpa.repository.JpaRepository;

public interface saRepository extends JpaRepository<SecurityAdmins, Long>{

    
    
}
