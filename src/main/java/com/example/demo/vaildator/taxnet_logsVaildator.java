package com.example.demo.vaildator;

import com.example.demo.DTO.taxnet_logs;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

/**
 * taxnet_logsVaildator
 */

 @Component
public class taxnet_logsVaildator implements Validator{



	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		
		return taxnet_logs.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub 
		taxnet_logs logs = (taxnet_logs) obj;
		
		if (StringUtils.isEmpty(logs.getUserid())) {
			errors.rejectValue("userid", "key", "아이디를 입력하세요.");
			
		}
		
	}
    
}