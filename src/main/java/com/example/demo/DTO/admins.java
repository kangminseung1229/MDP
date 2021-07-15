


package com.example.demo.DTO;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * admins
 * 관리자 로그인 table
 */

@Data
@Entity
public class admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String cd;
    private String pw;
    private String nm;
    private Date regdate;
    
}