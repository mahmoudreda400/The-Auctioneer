package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {
	
	Bidding findTopByPostOrderByPriceDesc(Post post);
	
	@Query("select b from Bidding b where b.user.id =:userId and b.post.expirDate <= :todayDate order by b.date desc ")
	List<Bidding> getNotifications(@Param("userId") long userId, @Param("todayDate") LocalDate todayDate);
	
	@Query("select b.post from Bidding b where b.user.id =:userId and b.post.expirDate <= :todayDate order by b.date desc ")
	List<Post> getPostsThatReadyToNotify(@Param("userId") long userId, @Param("todayDate") LocalDate todayDate);
	

//	List<Bidding> findPostExpirDateLessThanEqualOrderByPrice(LocalDate todatDate);
}
