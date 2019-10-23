package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    void deleteByReported(User user);
}
