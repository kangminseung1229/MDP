package com.example.demo.jpaTest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaRepo extends JpaRepository<jpatest,Long> {
    

    jpatest findBytestflag2(String testflag2);
    jpatest findBytestFlag(String testFlag);
}
