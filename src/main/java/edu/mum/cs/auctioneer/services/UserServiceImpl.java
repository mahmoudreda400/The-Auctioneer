package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.*;
import edu.mum.cs.auctioneer.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private ReportService reportService;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, ReportService reportService) {
		this.userRepo = userRepo;
		this.reportService = reportService;
	}

//	@Override
//	public ResponseEntity bidOnPost(Post post) {
//		if (null != UserSession.getLoggedInPerson() && PersonType.user.equals(UserSession.getLoggedInPerson().getRole())){
//			User user = userRepo.findByEmail(UserSession.getLoggedInPerson().getEmail());
//			return getBiddingService().bid(post, user);
//		}
//		return new ResponseEntity("please login before you can bid!", HttpStatus.FORBIDDEN);
//	}



	@Override
	public ResponseEntity reportUser(String msg, User reported) {
		Person person;
		if (null == UserSession.getLoggedInPerson()){
			return new ResponseEntity("please login first", HttpStatus.FORBIDDEN);
		}
		person = UserSession.getLoggedInPerson();
		User reporter = getUserRepo().findByEmail(person.getEmail());
		Report report = new Report(msg, reporter, reported);

		return getReportService().reportUser(report);
	}

	@Override
	public User findByEmail(String email) {
		return getUserRepo().findByEmail(email);
	}
	//----------------setters and getters-------------------

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
