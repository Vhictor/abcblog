package com.abc.blog.controller;

import com.abc.blog.model.BlogPost;
import com.abc.blog.model.ResponseDataFormat;
import com.abc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @PostMapping("/create")
    public ResponseEntity<BlogPost> createAPost (@RequestBody BlogPost blogPost){
    BlogPost created = blogService.createANewPost(blogPost);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseDataFormat> editAPost (@PathVariable Long id, @RequestBody BlogPost blogPost){
        return new ResponseEntity<>(blogService.editPost(id,blogPost), HttpStatus.valueOf(200));
    }
    @GetMapping("/view")
    public ResponseEntity<List<BlogPost>> getAllPosts (){
        return new ResponseEntity<>(blogService.viewPost(), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDataFormat> deletePost (@PathVariable Long id){
        return new ResponseEntity<>(blogService.deletePost(id),HttpStatus.valueOf(200));
    }
}
