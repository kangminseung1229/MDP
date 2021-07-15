
package com.example.demo.DTO;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * taxnet_logsDto
 */

 @Data
 @Entity
public class taxnet_logs {


    	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@Size(max = 30, min = 2, message = "글자수 제한 2~30 ")
	private String userid;
	private String ip;
	private String c_url;
	private String menu;

	@UpdateTimestamp
	private Date logtime;
	private String search;
}