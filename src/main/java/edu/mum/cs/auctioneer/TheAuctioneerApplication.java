package edu.mum.cs.auctioneer;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TheAuctioneerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheAuctioneerApplication.class, args);
//		ExpirChecker c = new ExpirChecker();
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(c, 0, 5 * 1000);
	}

}
