package com.sm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.exception.AccountNotFoundException;
import com.sm.exception.NotEnoughCreditException;
import com.sm.model.AccountEB;
import com.sm.model.GameEB;
import com.sm.repository.AccountRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private GameService gameService;
	
	public AccountEB create(AccountEB account) {			
		return accountRepository.save(account);
	}

	public Double getBalance(Long accountId) {		
		AccountEB account = findByd(accountId);
		return account.getBalance();
	}

	public Double deposite(Long accountId, Double deposite) {
		if(deposite <= 0){
			throw new IllegalArgumentException("Deposit should be greater than 0");
		}
		return updateBalance(accountId, deposite);		
	}
	
	public GameEB play(Long accountId) {
		AccountEB account = findByd(accountId);
		//f player balance doesn't support acton return 
		if (account.getBalance() < gameService.getGameCharge()) {
			throw new NotEnoughCreditException("Not enuogh credit to perform ths operation");
		}
		GameEB gameResult = gameService.pullLever(accountId);
		updateBalance(accountId, gameResult.getCredit());
		return gameResult;
	}
	
	public AccountEB findByd(Long accountId){
		Optional<AccountEB> optionalAccount = accountRepository.findById(accountId);
		AccountEB account = optionalAccount.orElseThrow(() -> new AccountNotFoundException("accountId doesn't exist"));
		return account;
	}
	
	public Double updateBalance(Long accountId, Double change){
		AccountEB account = findByd(accountId);
		Double newBalance = account.getBalance() + change;	
		//handle invalid account transactons
		if (newBalance < 0) {
			throw new NotEnoughCreditException("Not enuogh credit to perform ths operation");
		}
		account.setBalance(newBalance);
		accountRepository.save(account);
		return account.getBalance();
	}

	

}
