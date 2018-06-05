package com.xing.sm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class AccountEB {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	@NotNull
	@Column(unique = true)
	private String player;	
	private Double balance;
	
	public AccountEB(){}
	public AccountEB(String player, Double balance){
		this.player = player;
		this.balance = balance;
	}
	
	public AccountEB(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}		
	
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
	
}
