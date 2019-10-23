package edu.mum.cs.auctioneer.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminStatisticsService {

    ResponseEntity getAdminStatistics();
    List<?> getPostsPerMonth();
    List<?> getPostsPerCategory();
    List<?> getPostsPerUser();
}
