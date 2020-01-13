package com.webservice.demo;

import com.webservice.demo.domain.Posts;
import com.webservice.demo.domain.PostsRepository;
import com.webservice.demo.domain.PostsSaveRequestDto;
import com.webservice.demo.service.PostsService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup () {
        postsRepository.deleteAll();
    }

    @Test
    public void saveToTable () {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("jojoldu@gmail.com")
                .content("test")
                .title("test title")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        //assertThat(posts.getContent()).isEqualTo(dto.getContent());
        //assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
