package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.services.AdminStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminStatisticsController {

    @Autowired
    private AdminStatisticsService adminStatisticsService;

    @PostMapping("stat")
    public ResponseEntity getAdminStat(){
        return getAdminStatisticsService().getAdminStatistics();
    }

    //---------------------------setters and getters------------------

    public AdminStatisticsService getAdminStatisticsService() {
        return adminStatisticsService;
    }

    public void setAdminStatisticsService(AdminStatisticsService adminStatisticsService) {
        this.adminStatisticsService = adminStatisticsService;
    }
}
