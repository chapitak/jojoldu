package org.book.jojoldu.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.book.jojoldu.springboot.web.domain.posts.Posts;
import org.book.jojoldu.springboot.web.domain.posts.PostsRepository;
import org.book.jojoldu.springboot.web.dto.PostsResponseDto;
import org.book.jojoldu.springboot.web.dto.PostsSaveRequestDto;
import org.book.jojoldu.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));

        return new PostsResponseDto(entity);
    }
}
