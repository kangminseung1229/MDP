package com.example.demo.Repository;

import com.example.demo.DTO.admins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface adminRepository extends JpaRepository<admins, Long>{


    // @Query("select count(*) from admins where cd = :cd and pw = :pw")
    // int findByCdAndPwNative(@Param("cd") String cd, @Param("pw") String pw);

    
    admins findByCdAndPw(String cd, String pw);
    
}
