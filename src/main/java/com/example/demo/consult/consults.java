package com.example.demo.consult;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * consultsDTO
 */
@Data
@Entity
public class consults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tm_id;
    private String title;
    private String contents;
    private Long parent;
    private int flagid;


    @OneToOne
    @JoinColumn(name = "counselor_id")
    private counselors counselor;

    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;

}

