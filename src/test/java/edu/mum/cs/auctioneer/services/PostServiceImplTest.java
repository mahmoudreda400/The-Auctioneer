package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.PersonType;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.mum.cs.auctioneer.repositories.PostRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {
    @InjectMocks
    private PostServiceImpl postService;
    @Mock
    private PostRepository postRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllPost() {
        List<Post> list=new ArrayList<Post>();
        Post post=new Post(1,"Tititle","This is a post", LocalDate.now(),10,1,"DesMoines","USA");
        list.add(post);
        when(postRepository.findAll()).thenReturn(list);
        List<Post>actualList=postService.getAllPost();
        assertEquals(1, actualList.size());
        verify(postRepository, times(1)).findAll();
    }
    @Test
    public void getAllPostByLogin(){
        //	public User(long id, String name, String email, String password, String phone, String address, Boolean blocked, PersonType role) {
        User user=new User(3,"A","maiada399@live.com","A","01157673788","string",false, PersonType.user);
        //int userID=3;
        List<Post> list=new ArrayList<Post>();
        Post post=new Post(1,"Tititle","This is a post", LocalDate.now(),10,1,"DesMoines","USA");
        list.add(post);
        when(postRepository.findAllByUser(user)).thenReturn(list);
        List<Post>actualList=postService.getAllPostByLogin(user);
        assertEquals(1,actualList.size());
        verify(postRepository, times(1)).findAllByUser(user);
    }

}