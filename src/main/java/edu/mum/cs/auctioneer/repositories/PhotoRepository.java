package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {
    Optional<Photo>findByUrl(String url);
    List<Photo> findAllByPost(Post post);
    
    @Query("delete from Photo p where p.post.id =:postId")
    void deletePhotosForPost(@Param("postId") long postId);

}
