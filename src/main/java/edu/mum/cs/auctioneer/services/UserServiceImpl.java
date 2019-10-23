package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import edu.mum.cs.auctioneer.models.PersonType;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	private BiddingService biddingService;
	private ReportService reportService;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, BiddingService biddingService, ReportService reportService) {
		this.userRepo = userRepo;
		this.biddingService = biddingService;
		this.reportService = reportService;
	}

	@Override
	public ResponseEntity bidOnPost(Post post) {

		if (null != UserSession.getLoggedInPerson() && PersonType.user.equals(UserSession.getLoggedInPerson().getRole())){
			User user = userRepo.findByEmail(UserSession.getLoggedInPerson().getEmail());
			return getBiddingService().bid(post, user);
		}
		return new ResponseEntity("please login before you can bid!", HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity reportUser(Report report) {
		return getReportService().reportUser(report);
	}
	// ----------------setters and getters-------------------

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public BiddingService getBiddingService() {
		return biddingService;
	}

	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findById(id).get();
	}

	@Transactional
	@Override
	public Optional<User> registerNewUserAccount(User user) {
		Optional<User> person1 = Optional.empty();
		try {
			person1 = Optional.of(userRepo.save(user));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return person1;
	}

	@Override
	public User updateUserData(User user) {
		return userRepo.save(user);
	}
	

}
