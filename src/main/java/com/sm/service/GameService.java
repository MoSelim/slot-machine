package com.sm.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Random;

import com.sm.model.AccountEB;
import com.sm.model.GameEB;
import com.sm.model.ReelEB;
import com.sm.repository.GameRepository;
import com.sm.repository.ReelRepository;

@Service
@Transactional
@PropertySource("classpath:application.properties")
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private ReelRepository reelRepository;	
	private  Double gameCharge;		
	
	@Autowired 
	public GameService(@Value("${game.charge}") Double gameCharge) {
		super();
		this.gameCharge = gameCharge;
	}

	public GameEB pullLever(Long accountId) {
		GameEB game = new GameEB();
		//charge the player first
		double credit = gameCharge;
		List<ReelEB> allReels = reelRepository.findAllReels();
		int reelsNum = allReels.size();
		//generate random numbers to simulate the result of the pull lever
		//assume the slot machine will always be n*n whch s n our case 3*3 3 reels with 3 possble outcme
		List<ReelEB> resultedReels = allReels.stream().map(reel -> allReels.get(new Random().nextInt(reelsNum))).collect(Collectors.toList());
		//check that all outcoming fruits n the reels are the same
		Boolean win = resultedReels.stream().distinct().count() == 1;
		game.setWin(win);
		if(win){
			//get the award asscated with the fruit type
			credit += resultedReels.get(0).getAward();			
		}
		game.setCredit(credit);
		game.setAccount(new AccountEB(accountId));
		game.setResult(resultedReels.stream().map(reel -> reel.getFruit().toString()).collect(Collectors.joining(" ")));    	
    	return gameRepository.save(game);
	}

	public Double getGameCharge() {
		return gameCharge;
	}

	public void setGameCharge(Double gameCharge) {
		this.gameCharge = gameCharge;
	}
	
	

}
