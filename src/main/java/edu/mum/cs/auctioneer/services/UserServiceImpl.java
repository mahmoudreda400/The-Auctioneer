package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.ReportRepository;

import java.util.List;
import java.util.Optional;

import edu.mum.cs.auctioneer.models.*;
import edu.mum.cs.auctioneer.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private ReportService reportService;
    private ReportRepository reportRepository;


    @Autowired
	public UserServiceImpl(UserRepository userRepo, ReportService reportService,ReportRepository reportRepository) {
		this.userRepo = userRepo;
		this.reportService = reportService;
		this.reportRepository = reportRepository;
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
	public List<User> getAllUsers() {
		return getUserRepo().findAll();
	}
	
	@Override
	public List<User> getAllUsersHasBiddings() {
		return getUserRepo().getAllUserHasBidding();
	}


	@Override
	public List<User> getBlockedUsers() {
		return getUserRepo().findAllByBlockedTrue();
	}

	@Override
	public Boolean ignoreReports(Long id) {
        User user = getUserRepo().findById(id).get();
		 getReportRepository().deleteByReported(user);
		 return true;
	}

    @Override
    public Boolean blockUser(Long id) {
        User user = getUserRepo().findById(id).get();
        getReportRepository().deleteByReported(user);
        user.setBlocked(true);
        getUserRepo().save(user);
        return true;
    }

	@Override
	public Boolean activate(Long id) {
		User user = getUserRepo().findById(id).get();
		user.setBlocked(false);
		getUserRepo().save(user);
		return true;
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

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
