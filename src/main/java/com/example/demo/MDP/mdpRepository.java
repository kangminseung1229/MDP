package com.example.demo.MDP;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface mdpRepository extends JpaRepository<mdpPurchaseCode,Long> {
    

    Page<mdpPurchaseCode> findByUser(String user, Pageable pageable);
    Page<mdpPurchaseCode> findAll(Pageable pageable);
    Page<mdpPurchaseCode> findByOrderByIdDesc(Pageable pageable);

    Long countByCodeAndUserIsNull(String code);
    Long countByUser(String user);
    Long countByCode(String code);
    Long countByUserOrCodeAndUserIsNotNull(String code, String user);

    @Query(value="SELECT id FROM mdpPurchaseCode order by id desc limit 1", nativeQuery = true )
    long lastColumn();

    @Transactional
    @Modifying
    @Query(value="UPDATE mdpPurchaseCode SET USER=:user WHERE CODE=:code", nativeQuery = true )
    void updateUser(String user, String code);
}    
