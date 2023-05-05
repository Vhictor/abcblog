package com.abc.blog.controller;

import com.abc.blog.model.BlogPost;
import com.abc.blog.model.ResponseDataFormat;
import com.abc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
//@PreAuthorize("hasRole('ADMIN')")
public class BlogController {
    @Autowired
    private BlogService blogService;
    //@PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/create")
    public ResponseEntity<BlogPost> createAPost (@RequestBody BlogPost blogPost){
    BlogPost created = blogService.createANewPost(blogPost);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    //@PreAuthorize("hasAuthority('admin:update')")
    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseDataFormat> editAPost (@PathVariable Long id, @RequestBody BlogPost blogPost){
        return new ResponseEntity<>(blogService.editPost(id,blogPost), HttpStatus.valueOf(200));
    }
    //@PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/view")
    public ResponseEntity<List<BlogPost>> getAllPosts (){
        return new ResponseEntity<>(blogService.viewPost(), HttpStatus.valueOf(200));
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDataFormat> deletePost (@PathVariable Long id){
        return new ResponseEntity<>(blogService.deletePost(id),HttpStatus.valueOf(200));
    }
}
