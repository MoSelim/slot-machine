package com.sm.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sm.enums.FruitEnum;
import com.sm.model.ReelEB;



public interface ReelRepository extends CrudRepository<ReelEB, FruitEnum>{
	@Query("select r from ReelEB r")
	List<ReelEB> findAllReels();
}
