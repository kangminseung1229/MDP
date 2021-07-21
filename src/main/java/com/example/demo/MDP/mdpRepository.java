package com.example.demo.MDP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface mdpRepository extends JpaRepository<MDP_PurchaseCode,Long> {

    // @Query(value = "Select COUNT(*) from MDP_PurchaseCode Where code =:code", nativeQuery = true)
    // int randomCHECK(String code);
    long countByCode(String code);

}