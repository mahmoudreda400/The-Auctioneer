package edu.mum.cs.auctioneer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

public class ExpirChecker extends TimerTask{

	@Override
	public void run() {
		
		System.out.println(">>> timer check<<<"+new Date().getSeconds());
	}

}
