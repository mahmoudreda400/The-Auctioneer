package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Report;
import org.springframework.http.ResponseEntity;

public interface ReportService {

    ResponseEntity reportUser(Report report);
}
