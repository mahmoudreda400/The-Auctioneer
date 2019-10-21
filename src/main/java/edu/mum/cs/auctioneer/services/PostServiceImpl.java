package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public List<Post> getAllPostByLogin(User user) {
		return postRepository.findAllByUser(user);
	}

	@Override
	public Optional<Post> addPostByUser(User user, Post post, MultipartFile[] file) {
		String[] photoURLs = photoService.uploadPostPhotoData(file);
		Set<Photo> postPhotos = new HashSet<>();
		for (int i = 0; i < photoURLs.length; i++) {
			Photo photo = new Photo();
			photo.setUrl(photoURLs[i]);
			postPhotos.add(photo);
		}
		post.setPhotos(postPhotos);
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
}
