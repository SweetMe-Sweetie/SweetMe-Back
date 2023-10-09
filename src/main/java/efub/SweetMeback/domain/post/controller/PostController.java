package efub.SweetMeback.domain.post.controller;

import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.dto.PostResponseDto;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        Post post = postService.createPost(requestDto);
        return new PostResponseDto(post);
    }
}
