package com.xing.sm.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.xing.sm.enums.FruitEnum;
import com.xing.sm.model.ReelEB;
import com.xing.sm.repository.ReelRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReelRepostoryTest {	
	@Autowired
	private ReelRepository reelRepository;
	
	@Before
	public void setUp() {
		List<ReelEB> reels = new ArrayList<>();
    	reels.add(new ReelEB(FruitEnum.APPLE, 10d));
    	reels.add(new ReelEB(FruitEnum.BANANA, 15d));
    	reels.add(new ReelEB(FruitEnum.CITRUS_FRUIT, 20d));
    	reelRepository.saveAll(reels);
	}
	
	@Test
    public void whenAddReelWithSameFruit_thenReturnError() {
        // given
		ReelEB reel = new ReelEB(FruitEnum.CITRUS_FRUIT, 20d);    	     
        // when  
    	try{
    		reelRepository.save(reel);
    	}
    	catch(Exception ex){
    	      assertThat(ex).isInstanceOf(PersistenceException.class);
    	}    	
    }
	
	@Test
    public void whenfindAllReels_thenReturnList() {
		List<ReelEB> allReels = reelRepository.findAllReels();		
		assertThat(allReels.size()).isEqualTo(3);
    }
	
	
}
