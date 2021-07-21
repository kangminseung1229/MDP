package com.example.demo.MDP;

import com.example.demo.MDP.MDP_PurchaseCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface mdpRepository extends JpaRepository<MDP_PurchaseCode, Long>{


    @Query(value = "DELETE FROM MDP_PurchaseCode WHERE id= :delete_id", nativeQuery = true)
    void deleteIndex(Long delete_id);
    //만약 해당 난수가 이미 데이터베이스에 존재한다면 i--하고 반복문 다시 받아야 함
    //
}
