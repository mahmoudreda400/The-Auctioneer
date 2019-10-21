package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {
    Optional<Photo>findByUrl(String url);
    List<Photo> findAllByPost(Post post);

}
