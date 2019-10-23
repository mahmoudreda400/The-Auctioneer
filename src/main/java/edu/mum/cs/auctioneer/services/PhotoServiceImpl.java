package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	PhotoRepository photoRepository;
	@Value("${prefix.imagesDir}")
	private String imagesDir;
	
	public static String uploadDirectory = "C:\\Users\\mai\\Desktop\\project\\src\\main\\resources\\uploads";

//    @Override
//    public Photo uploadPhotoData(String url) {
//        Photo photo=new Photo(url);
//        return photoRepository.save(photo) ;
//    }

	@Override
	public String[] uploadPostPhotoData(MultipartFile[] files) {
		String[] result = null;
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				result[i] = uploadOnePhoto(files[i]);
			}
		}
		return result;
	}

	@Override
	public String uploadOnePhoto(MultipartFile file) {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		new File(uploadDirectory).mkdirs();
		fileNames.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fileNameAndPath.toString();
	}

	public List<Photo> getAllPhotoByPost(Post post) {
		return photoRepository.findAllByPost(post);
	}

	@Override
	public void deletePostPhotos(long postId) {
		photoRepository.deletePhotosForPost(postId);
	}
	
	@Override
	public Set<Photo> savePhotos(long userId,Post post, MultipartFile[] files) {

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

}





