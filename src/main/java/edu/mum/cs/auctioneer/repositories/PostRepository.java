package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post>getPostById(long Id);
    List<Post> findAllByUser(User user);

    //where month(CURRENT_DATE)-month(p.created) < 6
    @Query("select month(p.created), count(p.id) from Post as p  group by month(p.created)")
    List<?> getPostsPerMonth();

    @Query("select p.category.name, count(p.id) from Post as p  group by p.category.name")
    List<?> getPostsPerCategory();

    @Query("select p.user, count(p.id) from Post as p  group by p.user order by count(p.id) desc ")
    List<?> getPostsPerUser();
}
