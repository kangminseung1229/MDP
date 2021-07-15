package com.example.demo.jpaTest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class jpatest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String testFlag;
    String testflag2;
    
}
