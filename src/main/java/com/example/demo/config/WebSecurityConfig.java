package com.example.demo.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/assets/**", "/img/**").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/rest/*").permitAll()
				.antMatchers("/test/**").permitAll()
				.antMatchers("/.well-known/pki-validation/*").permitAll()
                .antMatchers("/MDP/*").permitAll()
                .antMatchers("/MDPadmin/adminLogin").permitAll()
                .antMatchers("/MDPadmin/adminJoin").permitAll()
                .antMatchers("/MDPadmin/manage").authenticated()
                .anyRequest().authenticated()
				.and()
            .formLogin()
                //login form 전송시 오는 곳 -> postmapping login 에 해당한다.
				.loginPage("/")
                .successHandler(new SuccessHandler())
                .failureUrl("/MDPadmin/login")
				.permitAll()
				.and()
			.logout()
                .logoutUrl("/adminLogout")
                .logoutSuccessUrl("/MDPadmin/login")
				.permitAll()
                .and()
            .csrf().disable();
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
    throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder())
        .usersByUsernameQuery("select username,password,enabled "
            + "from SecurityAdmins "
            + "where username = ? ")
        .authoritiesByUsernameQuery("select SA.username, SR.name "
            + " from AdminsRole AR  "
            + " inner join SecurityAdmins SA"
            + " ON AR.admins_id = SA.id "
            + " inner join SecurityRole SR  "
            + " ON AR.role_id = SR.id "
            + " where SA.username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}