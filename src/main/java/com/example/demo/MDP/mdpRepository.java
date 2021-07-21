package com.example.demo.MDP;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface mdpRepository extends JpaRepository<MDP_PurchaseCode,Long> {
    
    @Query(value = "Select * from MDP_PurchaseCode Where code =: code", nativeQuery = true)
    int randomCHECK(String code);


    Page<MDP_PurchaseCode> findByUserContainingOrCodeContaining(String user, String code, Pageable pageable);
    
}
