package com.example.demo.MDP;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;



@Data
@Entity
public class mdpPurchaseCode {

    @Id
    private Long id;
    
    private String code;
    private String user;

    @UpdateTimestamp
    private Date dateTime;
    
}