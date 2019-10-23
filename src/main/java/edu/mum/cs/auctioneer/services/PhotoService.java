package edu.mum.cs.auctioneer.services;
import java.util.List;
import java.util.Set;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface PhotoService {
   String[] uploadPostPhotoData(MultipartFile[] file);
   String uploadOnePhoto(MultipartFile file);
   List<Photo> getAllPhotoByPost(Post post);
void deletePostPhotos(long postId);
Set<Photo> savePhotos(long userId, Post post, MultipartFile[] files);


}
