package com.example.demo.MDP;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



// @Repository
// public interface mdpRepository extends JpaRepository<mdpPurchaseCode,Long> {
    
    
//     long countByCode(String code);

//     Page<mdpPurchaseCode> findByUserContainingOrCodeContaining(String user, String code, Pageable pageable);
    


// }

@Repository
public interface mdpRepository extends JpaRepository<TestTable,Long> {
    
    
    long countByCode(String code);

    Page<TestTable> findByUser(String user, Pageable pageable);


    Page<TestTable> findAll(Pageable pageable);
    // Page<TestTable> findAllOrderByIdDesc(Pageable pageable);
    Page<TestTable> findByOrderByIdDesc(Pageable pageable);

    @Query(value="SELECT id FROM TestTable order by id desc limit 1", nativeQuery = true )
    long last_column();

}    
