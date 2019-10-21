package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.jwt.JwtTokenUtil;
import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.services.PersonService;
import edu.mum.cs.auctioneer.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PersonService personService;

//    @GetMapping("/posts/{user}")
//    public List<Post>getAllPostByLogin(@RequestHeader("Authorization") String token){
//        List<Post>userPosts=null;
//        try {
//            String email = jwtTokenUtil.getEmailFromToken(token);
//            User user =(User) personService.getPersonByEmail(email).get();
//            if (user != null) {
//                userPosts= postService.getAllPostByLogin(user);}}catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userPosts;
//}
@GetMapping("/posts")
public ResponseEntity<Object> getAllPostByLogin(@RequestHeader("Authorization") String token) {
    ResponseEntity<Object> response = null;
    try {
        String email = jwtTokenUtil.getEmailFromToken(token);
        User user =(User) personService.getPersonByEmail(email).get();
        if (user != null) {
            List<Post>userPost = postService.getAllPostByLogin(user);
            response = new ResponseEntity<Object>(userPost, HttpStatus.OK);
        } else {
            response = new ResponseEntity<Object>("you aren't authorized ", HttpStatus.FORBIDDEN);
        }

    } catch (Exception e) {
        e.printStackTrace();
        response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
    }
    return response;
}
    @PostMapping("/deletePost")
    public ResponseEntity<Object> delete(@RequestHeader("Authorization") String token,@RequestBody Post post){
        ResponseEntity<Object> response = null;
        try {
            String email = jwtTokenUtil.getEmailFromToken(token);
            User user =(User) personService.getPersonByEmail(email).get();
            if (user != null) {
              boolean  result=  postService.deletePostByUserAndId(user,post);
                if(result==true){
                    response = new ResponseEntity<Object>(result, HttpStatus.OK);
                }else{
                    response = new ResponseEntity<Object>("you can't delete this post", HttpStatus.FORBIDDEN);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.FORBIDDEN);

        }
        return response;
    }
//@GetMapping("/posts/{id}")
//    public Post getPostById(@PathVariable Long id){
//    return postService.getPostById(id).get();
//}
@PostMapping(value = "/posts/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Long id) {
        ResponseEntity<Object> response = null;
        try {

            Post post = postService.getPostById(id).get();
            if (post != null) {
                response = new ResponseEntity<Object>(post, HttpStatus.OK);
            } else {
                response = new ResponseEntity<Object>("the post is not found", HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return response;
    }
//    @PostMapping("/deletePost")
//    public boolean delete(@RequestHeader("Authorization") String token,@RequestParam("Post") Post post){
//        boolean result = false;
//        try {
//            String email = jwtTokenUtil.getEmailFromToken(token);
//            User user =(User) personService.getPersonByEmail(email).get();
//            if (user != null) {
//                result=  postService.deletePostByUserAndId(user,post);
//            }
//            } catch (Exception e) {
//        e.printStackTrace();
//    }
//        return result;
//    }
//    @PostMapping("/updatePost")
//    public Post update(@RequestBody Post post,@RequestHeader("Authorization") String token){
//        Post savedpost= null;
//        try {
//            String email = jwtTokenUtil.getEmailFromToken(token);
//            User user =(User) personService.getPersonByEmail(email).get();
//            if (user != null) {
//            savedpost = postService.UpdatePostByUser(user,post).get();}
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return savedpost;
//    }
@PostMapping("/updatePost")
public ResponseEntity<Object> update(@RequestBody Post post,@RequestHeader("Authorization") String token){
    ResponseEntity<Object> response = null;
    try {
        String email = jwtTokenUtil.getEmailFromToken(token);
        User user =(User) personService.getPersonByEmail(email).get();
        if (user != null) {
            Post savedpost = postService.UpdatePostByUser(user,post).get();
            response = new ResponseEntity<Object>(savedpost, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Object>("you can't update this post ", HttpStatus.FORBIDDEN);

        }

    } catch (Exception e) {
        e.printStackTrace();
        response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
    }
    return response;
}
//    @PostMapping("/addPost")
//    public Post add(@RequestBody Post post, @RequestHeader("Authorization") String token, @RequestBody MultipartFile[]files){
//        Post savedpost= null;
//        try {
//            String email = jwtTokenUtil.getEmailFromToken(token);
//            User user =(User) personService.getPersonByEmail(email).get();
//            if (user != null) {
//                savedpost = postService.addPostByUser(user,post,files).get();}
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return savedpost;
//    }
@PostMapping("/addPost")
public ResponseEntity<Object> add(@RequestBody Post post, @RequestHeader("Authorization") String token,@RequestParam("file") MultipartFile[]files){
    ResponseEntity<Object> response = null;
    try {
        String email = jwtTokenUtil.getEmailFromToken(token);
        User user =(User) personService.getPersonByEmail(email).get();
        if (user != null) {
            Post savedpost = postService.addPostByUser(user,post,files).get();
            response = new ResponseEntity<Object>(savedpost, HttpStatus.OK);

        }else{
            response = new ResponseEntity<Object>("you aren't authorized ", HttpStatus.FORBIDDEN);

        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return response;
}
}
