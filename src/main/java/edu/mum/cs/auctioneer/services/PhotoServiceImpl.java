package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Photo;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class PhotoServiceImpl implements  PhotoService {
    @Autowired
    PhotoRepository photoRepository;
public static  String uploadDirectory="C:\\Users\\mai\\Desktop\\project\\src\\main\\resources\\uploads";


//    @Override
//    public Photo uploadPhotoData(String url) {
//        Photo photo=new Photo(url);
//        return photoRepository.save(photo) ;
//    }

    @Override
    public String[] uploadPostPhotoData(MultipartFile[] files) {
        String[]result=null;
        if(files.length>0){
        for(int i=0;i<files.length;i++){
            result[i]=uploadOnePhoto(files[i]);
        }}
        return  result;
    }

    @Override
    public String uploadOnePhoto(MultipartFile file) {
        StringBuilder fileNames=new StringBuilder();
        Path fileNameAndPath= Paths.get(uploadDirectory, file.getOriginalFilename());
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

    public List<Photo> getAllPhotoByPost(Post post){
        return photoRepository.findAllByPost(post);
    }
}
