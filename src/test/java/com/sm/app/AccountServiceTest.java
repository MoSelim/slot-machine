package com.sm.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sm.exception.AccountNotFoundException;
import com.sm.exception.NotEnoughCreditException;
import com.sm.model.AccountEB;
import com.sm.model.GameEB;
import com.sm.repository.AccountRepository;
import com.sm.service.AccountService;
import com.sm.service.GameService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountService accountService;
	@MockBean(name="gameService")
	private GameService gameService;
	private AccountEB InitialAccount;	
	
	@Before
	public void setUp(){
		InitialAccount = new AccountEB("Mohamed" + new Random().nextInt(1000), 140d);
		InitialAccount.setId(null); 
		InitialAccount = accountRepository.save(InitialAccount);
    	GameEB game = new GameEB();
    	game.setCredit(15d);
    	game.setWin(true);
		game.setAccount(InitialAccount);
		game.setResult("APPLE APPLE APPLE");  
    	Mockito.when(gameService.pullLever(InitialAccount.getId()))
        .thenReturn(game);
    	
	}
	
	@Test
    public void whenFindById_thenReturnAccount() {
        // given
    	AccountEB account = accountService.findByd(InitialAccount.getId());
        // then
        assertThat(account.getPlayer()).isEqualTo(InitialAccount.getPlayer());        
    }
	
	@Test
    public void whenFindByInvalidId_thenReturnException() {
		try{
			AccountEB account = accountService.findByd(InitialAccount.getId() + 1);
		}
    	catch(Exception ex){
    		assertThat(ex).isInstanceOf(AccountNotFoundException.class);
    	}        
    }
	
	@Test
    public void whenDeposit_thenReturnNewBalance() {
		Double newBalance = accountService.updateBalance(InitialAccount.getId(), 40d);
    	assertThat(newBalance.equals(InitialAccount.getBalance() + 40d));    
    }
	
	@Test
    public void whenPlayerWins_thenIncreaseBalanceWithCredit() {
		//given
		Double oldBalance = InitialAccount.getBalance();
		GameEB game = accountService.play(InitialAccount.getId());		
		//then
		AccountEB account = accountService.findByd(InitialAccount.getId());
    	assertThat(account.getBalance()).isEqualTo(oldBalance + game.getCredit());    
    }
	
	@Test
    public void whenPlayerDoesntHaveCredit_thenReturnException() {
		//given
		InitialAccount.setBalance(0d);
		//then
		try{
			GameEB game = accountService.play(InitialAccount.getId());	
		}
    	catch(Exception ex){
    		assertThat(ex).isInstanceOf(NotEnoughCreditException.class);
    	}					   
    }
	
	@Test
    public void whenDepositNegativeNumber_thenReturnException() {
		try{
			Double newBalance = accountService.updateBalance(InitialAccount.getId(), -40d);
		}
    	catch(Exception ex){
    		assertThat(ex).isInstanceOf(IllegalArgumentException.class);
    	}        
    }
	
	

}
