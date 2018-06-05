package com.xing.sm.repository;


import org.springframework.data.repository.CrudRepository;




import com.xing.sm.model.GameEB;

public interface GameRepository extends CrudRepository<GameEB, Long>{
	
}
