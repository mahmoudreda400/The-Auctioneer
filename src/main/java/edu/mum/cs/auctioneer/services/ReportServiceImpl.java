package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ResponseEntity reportUser(Report report) {
        Report responseBody = getReportRepository().save(report);

        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    //--------------setters and getters--------------

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
