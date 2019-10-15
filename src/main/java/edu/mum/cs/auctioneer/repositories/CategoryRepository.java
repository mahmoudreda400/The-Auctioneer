package edu.mum.cs.auctioneer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.auctioneer.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
