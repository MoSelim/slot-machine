package com.xing.sm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xing.sm.model.AccountEB;
import com.xing.sm.model.GameEB;
import com.xing.sm.service.AccountService;




@RestController
public class AccountController{
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/account/create", method = RequestMethod.POST)
	public ResponseEntity<AccountEB> create(@Valid @RequestBody AccountEB account) {
		ResponseEntity<AccountEB> response = new ResponseEntity<>(accountService.create(account), HttpStatus.CREATED);	
		return response;
    }
	
	@RequestMapping(value = "/account/balance/{id}", method = RequestMethod.GET)
	public ResponseEntity<Double> getBalance(@PathVariable("id") Long accountId) {	
		ResponseEntity<Double> response = new ResponseEntity<>(accountService.getBalance(accountId), HttpStatus.OK);
		return response;
    }
	
	@RequestMapping(value = "/account/deposite/{id}", method = RequestMethod.POST)
	public ResponseEntity<Double> deposite(@PathVariable("id") Long accountId, @RequestBody Double deposite) {	
		ResponseEntity<Double> response = new ResponseEntity<>(accountService.deposite(accountId, deposite), HttpStatus.OK);
		return response;
    }
	
	@RequestMapping(value = "/account/play/{id}", method = RequestMethod.POST)
	public ResponseEntity<GameEB> play(@PathVariable("id") Long accountId) {	
		ResponseEntity<GameEB> response = new ResponseEntity<>(accountService.play(accountId), HttpStatus.OK);
		return response;
    }

}