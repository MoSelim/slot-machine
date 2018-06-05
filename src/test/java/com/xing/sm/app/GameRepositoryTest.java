package com.xing.sm.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xing.sm.model.AccountEB;
import com.xing.sm.model.GameEB;
import com.xing.sm.repository.AccountRepository;
import com.xing.sm.repository.GameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

	@Autowired
	private GameRepository gameRepository;
	//may be in this case there is no need to mock the test object as we use inmemory database
	@Autowired
	private AccountRepository accountRepository;
	private Long savedId;
	
	@Before
	public void setUp(){
		AccountEB account = new AccountEB("Mohamed Adel", 140d);
    	account.setId(null); 
    	account = accountRepository.save(account);
    	savedId = account.getId();
	}
	
	
	@Test
    public void whenFindById_thenReturnGame() {
        // given
		AccountEB player = accountRepository.findById(savedId).get();
    	GameEB game = new GameEB();
    	game.setCredit(15d);
    	game.setWin(true);
		game.setAccount(player);
		game.setResult("APPLE APPLE APPLE");     	     
    	Long gameId = gameRepository.save(game).getId(); 
    	Optional<GameEB> found = gameRepository.findById(gameId);
        // then
        assertThat(found.isPresent());
        assertThat(found.get().getCredit().equals(game.getCredit()));
        assertNotNull(found.get().getCreated());
    }
}
