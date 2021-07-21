package com.example.demo.MDP;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface mdpRepository extends JpaRepository<MDP_PurchaseCode,Long> {
    
    
    long countByCode(String code);

    Page<MDP_PurchaseCode> findByUserContainingOrCodeContaining(String user, String code, Pageable pageable);
    


}

