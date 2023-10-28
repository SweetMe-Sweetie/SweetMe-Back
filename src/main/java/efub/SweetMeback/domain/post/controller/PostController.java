package efub.SweetMeback.domain.post.controller;

import efub.SweetMeback.domain.heart.dto.HeartRequestDto;
import efub.SweetMeback.domain.heart.service.HeartService;
import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.dto.PostResponseDto;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final HeartService heartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        Post post = postService.createPost(requestDto);
        return new PostResponseDto(post);
    }

    @GetMapping("/{post_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto findPost(@PathVariable Long post_id){
        Post post = postService.findPostById(post_id);
        return new PostResponseDto(post);
    }

    @PostMapping("/{post_id}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createHeart(@PathVariable final Long post_id, @RequestBody final HeartRequestDto requestDto){
        heartService.create(post_id, requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }
}
