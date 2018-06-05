package com.sm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "game")
public class GameEB {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	@NotNull
	@OneToOne
	private	AccountEB account;
	private Boolean win;
	private String result;
	private Double credit;
	private Date created;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}		
	public AccountEB getAccount() {
		return account;
	}
	public void setAccount(AccountEB account) {
		this.account = account;
	}
	public Boolean getWin() {
		return win;
	}
	public void setWin(Boolean win) {
		this.win = win;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getCreated() {
		return created;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public void setCreated(Date created) {
		this.created = created;
	}	
	
	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	}
}
