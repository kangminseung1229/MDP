package com.example.demo.newconsult;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * newconsults
 */
@Data
@Entity
public class newconsults {

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tm_id;
    private String title;
    private String contents;

    private int flag;



    // @ManyToOne
    // @JoinColumn(name = "id", insertable=false, updatable=false )
    // private consultgroup consultgroup;
    // JPA 언더바 인식 불가
    @Column(name = "group_id")
    private Long groupid;

    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;

    
}