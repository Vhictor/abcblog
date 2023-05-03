package com.abc.blog.service;

import com.abc.blog.exception.GlobalRequestException;
import com.abc.blog.model.BlogPost;
import com.abc.blog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {

    @Mock
    private BlogPostRepository blogPostRepository;
    private BlogServiceImpl blogServiceUnderTest;

    @BeforeEach
    void setUp() {
        blogServiceUnderTest = new BlogServiceImpl(blogPostRepository);
    }

    @Test
    void createANewPost() {
        //Given
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Title");
        blogPost.setBody("Body");
        //When
        blogServiceUnderTest.createANewPost(blogPost);

        //Then

        ArgumentCaptor<BlogPost> blogPostArgumentCaptor =
                ArgumentCaptor.forClass(BlogPost.class);

        verify(blogPostRepository)
                .save(blogPostArgumentCaptor.capture());

        BlogPost capturedBlogPost = blogPostArgumentCaptor.getValue();

        assertThat(capturedBlogPost).isEqualTo(blogPost);

    }

    //This test will only pass if there is any data associated with the ID sent.
    @Test
    void editPost() {
        //Given
        long id = 1;
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Title");
        blogPost.setBody("Body");
        given(blogPostRepository.existsById(id))
                .willReturn(true);
        //When
        blogServiceUnderTest.editPost(id,blogPost);

        //Then

        ArgumentCaptor<BlogPost> blogPostArgumentCaptor =
                ArgumentCaptor.forClass(BlogPost.class);

        verify(blogPostRepository)
                .save(blogPostArgumentCaptor.capture());

        BlogPost capturedBlogPost = blogPostArgumentCaptor.getValue();
        assertThat(capturedBlogPost).isEqualTo(blogPost);

    }

    @Test
    void deletePost() {
        // given
        long id = 1;
        given(blogPostRepository.existsById(id))
                .willReturn(true);
        // when
        blogServiceUnderTest.deletePost(id);

        // then
        verify(blogPostRepository).deleteById(id);
    }

    @Test
    void viewPost() {

        // when
        blogServiceUnderTest.viewPost();
        // then
        verify(blogPostRepository).findAll();
    }

    @Test
    void willThrowWhenDeleteBlogPostNotFound() {
        // given
        long id = 10;
        given(blogPostRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> blogServiceUnderTest.deletePost(id))
                .isInstanceOf(GlobalRequestException.class)
                .hasMessageContaining("No data associated with the ID");

        verify(blogPostRepository, never()).deleteById(any());
    }
}