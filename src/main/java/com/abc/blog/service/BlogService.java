package com.abc.blog.service;

import com.abc.blog.model.BlogPost;
import com.abc.blog.model.ResponseDataFormat;

import java.util.List;

public interface BlogService {
    BlogPost createANewPost(BlogPost post);
    ResponseDataFormat editPost (Long id, BlogPost post);
    ResponseDataFormat deletePost (Long id);
    List<BlogPost> viewPost ();
}
