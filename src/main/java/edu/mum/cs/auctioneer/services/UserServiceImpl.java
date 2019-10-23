package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private BiddingService biddingService;
	private ReportService reportService;
    private ReportRepository reportRepository;


    @Autowired
	public UserServiceImpl(UserRepository userRepo, BiddingService biddingService, ReportService reportService,ReportRepository reportRepository) {
		this.userRepo = userRepo;
		this.biddingService = biddingService;
		this.reportService = reportService;
		this.reportRepository = reportRepository;
	}

	@Override
	public ResponseEntity bidOnPost(Post post) {
		User user = new User(); //TODO load the user from session

		return getBiddingService().bid(post, user);
	}

	@Override
	public ResponseEntity reportUser(Report report) {
		return getReportService().reportUser(report);
	}

	@Override
	public List<User> getAllUsers() {
		return getUserRepo().findAll();
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


    //----------------setters and getters-------------------

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


    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
