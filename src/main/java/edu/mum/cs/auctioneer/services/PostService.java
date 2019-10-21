package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPostByLogin(User user);
    Optional<Post>addPostByUser(User user, Post post, MultipartFile[] file);
    boolean deletePostByUserAndId(User user,Post post);
    Optional<Post>UpdatePostByUser(User user,Post post);
    Optional<Post> getPostById(long id);

}
