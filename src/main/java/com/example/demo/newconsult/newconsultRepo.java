package com.example.demo.newconsult;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface newconsultRepo extends JpaRepository<newconsults, Long>{



    // 카운트 쿼리가 이상하게 찍혀 직접 지정해준다.
    @Query(value = " select * from newconsults where flag=0 AND title  like %:title% " ,
        countQuery = "select count(*) from newconsults where  flag=0 AND  title like %:title% ",
    nativeQuery = true)
    Page<newconsults> getAll(String title, Pageable pageable);

    @Query(value = "SELECT nc.id AS nc_id , nc.tm_id, nc.title, nc.contents, nc.flag, nc.updated_at, ncs.nm FROM consultgroup cg "+
                    "LEFT OUTER JOIN newconsults nc " +
                    "ON cg.id = nc.group_id " + 
                    "LEFT OUTER JOIN newcounselors ncs " + 
                    "ON cg.counselor_id = ncs.id " + 
                    "WHERE nc.group_id = :groupId ",
            nativeQuery = true
    )
    List<Map<String,Object>> getView (Long groupId);


    //상단 회원 정보
    newconsults findByFlagAndGroupid(int flag, Long groupId);

    //상담글 각각 수정
    @Modifying
    @Transactional
    @Query(value = "UPDATE newconsults SET title = :title, contents = :contents WHERE id = :id ; ", nativeQuery = true)
    int setConsult(String title, String contents,  Long id);
    
    
}
