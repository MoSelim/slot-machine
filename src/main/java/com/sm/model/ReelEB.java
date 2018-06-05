package com.sm.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sm.enums.FruitEnum;

@Entity
@Table(name = "reel")
public class ReelEB {
	
	@Id
	@Enumerated(EnumType.STRING)
	private FruitEnum fruit;	
	private Double award;
	
	public ReelEB(){}
	public ReelEB(FruitEnum fruit, Double award) {		
		this.fruit = fruit;
		this.award = award;
	}
	public FruitEnum getFruit() {
		return fruit;
	}
	public void setFruit(FruitEnum fruit) {
		this.fruit = fruit;
	}
	public Double getAward() {
		return award;
	}
	public void setAward(Double award) {
		this.award = award;
	}
	
	
	
	
	
}
