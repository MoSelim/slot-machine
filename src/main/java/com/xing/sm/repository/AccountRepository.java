package com.xing.sm.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xing.sm.model.AccountEB;

@Repository
public interface AccountRepository extends CrudRepository<AccountEB, Long>{

}
