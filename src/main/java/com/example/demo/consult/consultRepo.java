package com.example.demo.consult;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface consultRepo extends JpaRepository<consults,Long>{

    @Query(value= "SELECT cs.*, (SELECT flagid FROM consults where parent = cs.id order by flagid desc LIMIT 1 ) AS gu, csl.nm FROM consults cs " +
    " left outer join counselors csl on cs.counselor_id = csl.id "+
    " WHERE parent =0 and (title like %:title% or contents like %:contents% )", nativeQuery = true)
    Page<Map<String,Object>> getListByTitle( String title,  String contents, Pageable pageable);


    //select * from consults where parent = 1 or id  = 1 order by flagid
    List<consults> findByParentOrIdOrderByFlagid(Long parent, Long id);
    
    


}
