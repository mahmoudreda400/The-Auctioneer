package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PhotoService photoService;

	@Value("${prefix.imagesDir}")
	private String imagesDir;
	
	@Autowired
	private SimpMessagingTemplate template;


	@Override
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> getAllPostByLogin(User user) {
		return postRepository.findAllByUser(user);
	}

	@Override
	public Optional<Post> addPostByUser(User user, Post post, MultipartFile[] files) {
//		String[] photoURLs = photoService.uploadPostPhotoData(files);
//		Set<Photo> postPhotos = new HashSet<>();
//		for (int i = 0; i < photoURLs.length; i++) {
//			Photo photo = new Photo();
//			photo.setUrl(photoURLs[i]);
//			postPhotos.add(photo);
//		}
		Set<Photo> postPhotos = photoService.savePhotos(user.getId(), post, files);
		post.setPhotos(postPhotos);
		post.setUser(user);
		return Optional.of(postRepository.save(post));

	}

	@Override
	public Optional<Post> updatePostByUser(User user, Post post, MultipartFile[] files) {
		Set<Photo> postPhotos = photoService.savePhotos(user.getId(), post, files);
		if (postPhotos.size() > 0) {
			photoService.deletePostPhotos(post.getId());
			post.setPhotos(postPhotos);
		}
		post.setUser(user);
		return Optional.of(postRepository.save(post));

	}

	@Override
	public boolean deletePostByUserAndId(User user, Post post) {
		if (post.getUser().getId() == user.getId()) {
			postRepository.deleteById(post.getId());
			return true;
		}
		return false;
	}

	@Override
	public Optional<Post> UpdatePostByUser(User user, Post post) {
		if (post.getUser().getId() == user.getId()) {
			return Optional.of(postRepository.save(post));

		}
		return Optional.empty();
	}

	@Override
	public Optional<Post> getPostById(long id) {

		return postRepository.getPostById(id);
	}
	
	@Override
	public List<Post> getAllExpiredPosts(){
		LocalDate today = LocalDate.now();
		List<Post> posts =  postRepository.getAllExpired(today);
		for (Post post : posts) {
			this.template.convertAndSend("/norifications/" + post.getUser().getId(), post);
		}
		return posts;

	}
	@Override
	public List<?> getPostsPerMonth() {
		return postRepository.getPostsPerMonth();
	}

	@Override
	public List<?> getPostsPerCategory() {
		return postRepository.getPostsPerCategory();
	}

	@Override
	public List<?> getPostsPerUser() {
		return postRepository.getPostsPerUser();
	}

	@Override
	public Post updatePost(Post post) {
		return postRepository.save(post);
	}


}
