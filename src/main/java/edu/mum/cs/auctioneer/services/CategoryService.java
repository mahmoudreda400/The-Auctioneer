package edu.mum.cs.auctioneer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import edu.mum.cs.auctioneer.models.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	
	ResponseEntity<Object> addCategory(Category category);
	
	ResponseEntity<String> deleteCategoryById(long id);
}
