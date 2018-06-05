package com.xing.sm.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xing.sm.enums.FruitEnum;
import com.xing.sm.model.ReelEB;



public interface ReelRepository extends CrudRepository<ReelEB, FruitEnum>{
	@Query("select r from ReelEB r")
	List<ReelEB> findAllReels();
}
