package com.example.demo.MDP;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface mdpRepository extends JpaRepository<mdpPurchaseCode, Long> {

    Page<mdpPurchaseCode> findByUser(String user, Pageable pageable);

    Page<mdpPurchaseCode> findAll(Pageable pageable);

    Page<mdpPurchaseCode> findByOrderByIdDesc(Pageable pageable);

    Long countByCodeAndUserIsNull(String code);

    Long countByUser(String user);

    Long countByCode(String code);

    Long countByUserOrCodeAndUserIsNotNull(String code, String user);

    @Query(value = "SELECT id FROM mdpPurchaseCode order by id desc limit 1", nativeQuery = true)
    long lastColumn();

    @Query(value = "SELECT user FROM mdpPurchaseCode WHERE CODE=:user", nativeQuery = true)
    String findUser(String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mdpPurchaseCode SET USER=:user WHERE CODE=:code", nativeQuery = true)
    void updateUser(String user, String code);

    @Query(value = "SELECT * FROM mdpPurchaseCode WHERE CODE LIKE %:user% OR USER LIKE %:user%", nativeQuery = true)
    Page<mdpPurchaseCode> manageSearch(String user, Pageable pageable);

}
