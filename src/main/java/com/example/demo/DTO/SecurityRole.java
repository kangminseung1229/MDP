
package com.example.demo.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * SecurityAdmins
 */

 @Data
 @Entity
public class SecurityRole {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    // @ManyToMany(mappedBy = "roles")
    // private List<SecurityAdmins> securityAdmins;


    
}