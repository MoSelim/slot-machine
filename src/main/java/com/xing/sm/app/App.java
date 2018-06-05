package com.xing.sm.app;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.xing.sm.enums.FruitEnum;
import com.xing.sm.model.AccountEB;
import com.xing.sm.model.ReelEB;
import com.xing.sm.repository.AccountRepository;
import com.xing.sm.repository.GameRepository;
import com.xing.sm.repository.ReelRepository;



@SpringBootApplication
@EnableJpaRepositories("com.xing.sm.repository")
@ComponentScan("com.xing.sm")
@EntityScan("com.xing.sm.model")
public class App {
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    //seeding the database and configuring the game charge
    @Bean
    public CommandLineRunner commandLineRunner(AccountRepository accountRepository, ReelRepository reelRepository, GameRepository gameRepository) {    	
        return args -> {
//        	AccountEB account = new AccountEB("Mohamed Faris", 140d);
//        	account.setId(null);
//        	account = accountRepository.save(account); 
        	List<ReelEB> reels = new ArrayList<>();
        	reels.add(new ReelEB(FruitEnum.APPLE, 10d));
        	reels.add(new ReelEB(FruitEnum.BANANA, 15d));
        	reels.add(new ReelEB(FruitEnum.CITRUS_FRUIT, 20d));
        	reelRepository.saveAll(reels);        	        	
        };
    }

}
