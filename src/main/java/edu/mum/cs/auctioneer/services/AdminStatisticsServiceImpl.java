package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.AdminStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

    @Autowired
    private PostService postService;

    @Override
    public ResponseEntity getAdminStatistics() {
        AdminStatistics adminStatistics = new AdminStatistics();
        adminStatistics.setPostsPerMonth(getPostsPerMonth());
        adminStatistics.setPostsPerCategory(getPostsPerCategory());
        adminStatistics.setPostsPerUser(getPostsPerUser());


        return new ResponseEntity(adminStatistics, HttpStatus.OK);
    }

    @Override
    public List<?> getPostsPerMonth() {
        return getPostService().getPostsPerMonth();
    }

    @Override
    public List<?> getPostsPerCategory() {
        return getPostService().getPostsPerCategory();
    }

    @Override
    public List<?> getPostsPerUser() {
        return getPostService().getPostsPerUser();
    }

    //--------------------setters and getters----------------------

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}

