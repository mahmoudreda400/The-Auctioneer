package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
		Set<Photo> postPhotos = savePhotos(user.getId(), post, files);
		post.setPhotos(postPhotos);
		post.setUser(user);
		return Optional.of(postRepository.save(post));

	}

	private Set<Photo> savePhotos(long userId,Post post, MultipartFile[] files) {

		Set<Photo> photos = new HashSet<>();

		File dirFile = new File(imagesDir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String curPathDir = imagesDir + "/" + userId + "-" + new Date().getTime() ;
		dirFile = new File(curPathDir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		for (MultipartFile mfile : files) {
			System.out.println(mfile.getOriginalFilename());
			String curPath = curPathDir+ "/" + mfile.getOriginalFilename();
			System.out.println("curPath " + curPath);
			File file = new File(curPath);
			try {
				mfile.transferTo(file);
				Photo photo = new Photo();
				photo.setUrl(curPath);
				photo.setPost(post);
				photos.add(photo);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return photos;
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
