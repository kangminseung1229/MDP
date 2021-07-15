package com.example.demo.newconsult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class consultgroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private Long consult_id;

    private Long counselor_id;
    
    // @ManyToOne
    // @JoinColumn(name = "id", insertable=false, updatable=false )
    // private newcounselors newcounselor;
}
