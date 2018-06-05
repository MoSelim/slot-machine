package com.sm.repository;


import org.springframework.data.repository.CrudRepository;

import com.sm.model.GameEB;

public interface GameRepository extends CrudRepository<GameEB, Long>{
	
}
