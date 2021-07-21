package com.example.demo.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.taxnet_logs;


@Repository
public interface tlRepository extends JpaRepository<taxnet_logs, Long>{

	List<taxnet_logs> findByUserid(String userid);
	List<taxnet_logs> findByUseridOrMenu(String userid, String menu);
	Page<taxnet_logs> findByUseridContainingOrIpContainingOrderByIdDesc(String userid, String ip, Pageable pageable);

	public void deleteById(Long id);

}
