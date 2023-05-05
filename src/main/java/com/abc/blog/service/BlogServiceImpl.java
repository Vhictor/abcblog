package com.abc.blog.service;

import com.abc.blog.config.UserHelper;
import com.abc.blog.exception.GlobalRequestException;
import com.abc.blog.model.BlogPost;
import com.abc.blog.model.ResponseDataFormat;
import com.abc.blog.repository.BlogPostRepository;
import com.abc.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * Create a new Post
     * @param post
     * @return
     */
    @Override
    public BlogPost createANewPost(BlogPost post) {
        if (post.getTitle().isEmpty() || post.getBody().isEmpty()){
            throw new GlobalRequestException("Please enter title and body");
        }
        post.setCreationDate(new Date());
        post.setUpdatedDate(new Date());
        post.setUser(userRepository.findByEmail(UserHelper.getUserEmail()).get());
        return blogPostRepository.save(post);
    }

    /**
     * Edit existing Post using post ID
     * @param id
     * @param post
     * @return
     */
    @Override
    public ResponseDataFormat editPost(Long id, BlogPost post) {
        Optional<BlogPost> blogPost  = blogPostRepository.findById(id);
        if (blogPost.isEmpty()){
        throw new GlobalRequestException("No data associated with this ID");
        }
        blogPost.get().setTitle(post.getTitle());
        blogPost.get().setBody(post.getBody());
        blogPost.get().setUpdatedDate(new Date());
        blogPostRepository.save(blogPost.get());
        return new ResponseDataFormat(true, "Data updated successfully");
    }

    /**
     * Delete post using the ID
     * @param id
     * @return
     */

    @Override
    public ResponseDataFormat deletePost(Long id) {
        if(!blogPostRepository.existsById(id)){
            throw new GlobalRequestException("No data associated with the ID");
        }
        blogPostRepository.deleteById(id);
        return new ResponseDataFormat(true, "Deleted successfully");
    }


    /**
     * View All available post
     * @return
     */
    @Override
    public List<BlogPost> viewPost() {
        return blogPostRepository.findAll();
    }



}
