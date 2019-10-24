package edu.mum.cs.auctioneer.schedules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.services.BiddingService;
import edu.mum.cs.auctioneer.services.UserService;

@Component
public class ScheduledTasks {

	private UserService userService;
	private BiddingService biddingService;
	private SimpMessagingTemplate template;

	@Autowired
	public ScheduledTasks(UserService userService, BiddingService biddingService, SimpMessagingTemplate template) {
		this.userService = userService;
		this.biddingService = biddingService;
		this.template = template;
	}

	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {
		System.out.println("The time is now {}" + new Date());
		List<User> users = userService.getAllUsers();
		System.out.println(" >>> users: " + users.size());
		List<User> usersHasBiddings = userService.getAllUsers();
		System.out.println(" >>> users: " + usersHasBiddings.size());
		for (User user : usersHasBiddings) {
			List<Bidding> bids = biddingService.getUserNotificatios(user.getId());
			if (bids.size() > 0) {
				System.out.println(" >>>> push notifications to user: "+user.getId()+"  name: "+user.getName()+" >> bids:"+bids.size());
				this.template.convertAndSend("/notifications/" + user.getId(), bids);
			}
		}
	}
}
