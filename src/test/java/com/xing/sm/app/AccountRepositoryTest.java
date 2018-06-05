package com.xing.sm.app;


import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import com.xing.sm.model.AccountEB;
import com.xing.sm.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {;
 
    @Autowired
    private AccountRepository accountRepository;
    

    @Test
    public void whenFindById_thenReturnAccount() {
        // given
    	AccountEB account = new AccountEB("Mohamed Abdel Kahar edf", 140d);
    	account.setId(null);    	     
    	Long accountId = accountRepository.save(account).getId(); 
    	Optional<AccountEB> found = accountRepository.findById(accountId);
        // then
        assertThat(found.isPresent());
        assertThat(found.get().getPlayer()).isEqualTo(account.getPlayer());
    }
    
    @Test
    public void whenAddAccountWithSamePlayer_thenReturnError() {
        // given
    	AccountEB account = new AccountEB("Robert Green", 140d);
    	account.setId(null);    	     
        // when
    	AccountEB newAccount = new AccountEB("Robert Green", 140d);
    	newAccount.setId(null);   
    	try{
    		accountRepository.save(newAccount);
    	}
    	catch(Exception ex){
    	      assertThat(ex).isInstanceOf(PersistenceException.class);
    	}    	
    }
    
    @Test
    public void whenUpdateBalance_thenCheckIfUpdated() {
        // given
    	AccountEB account = new AccountEB("Omar Karim", 140d);
    	account.setId(null);    	     
    	account = accountRepository.save(account);  
    	//when
    	Optional<AccountEB> found = accountRepository.findById(account.getId());
    	AccountEB foundAccount = found.get();
    	foundAccount.setBalance(80d);
    	accountRepository.save(foundAccount);
    	Optional<AccountEB> UpdatedBalance = accountRepository.findById(account.getId());
    	assertThat(UpdatedBalance.get().getBalance()).isEqualTo(foundAccount.getBalance());
    }
    
    
    
    
}
