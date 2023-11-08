package efub.SweetMeback.domain.post.controller;

import efub.SweetMeback.domain.heart.service.HeartService;
import efub.SweetMeback.domain.post.dto.PostFilteringRequest;
import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.dto.PostResponseDto;
import efub.SweetMeback.domain.post.dto.PostResponseDtoWithHeart;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts", produces = "application/json; charset=utf8")
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
    public PostResponseDtoWithHeart findPost(@PathVariable Long post_id){
        postService.updateView(post_id);

        Post post = postService.findPostById(post_id);
        boolean isHeart = heartService.isHeartByMember(post);

        return new PostResponseDtoWithHeart(post, isHeart);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDtoWithHeart>> findAllPostsWithHeart() {
        return ResponseEntity.ok(postService.findAllPostsWithHeart());
    }

    @GetMapping("/member")
    public ResponseEntity<List<PostResponseDtoWithHeart>> findPostsByMember() {
        return ResponseEntity.ok(postService.findPostsByMember());
    }

    @GetMapping("/heart")
    public ResponseEntity<List<PostResponseDtoWithHeart>> findPostsByHeart() {
        return ResponseEntity.ok(postService.findPostsByHeart());
    }

    @GetMapping("/promotion")
    public ResponseEntity<List<PostResponseDtoWithHeart>> findPostsByPromotion() {
        return ResponseEntity.ok(postService.findPostsByPromotion());
    }

    @GetMapping("/filtering")
    public ResponseEntity<List<PostResponseDtoWithHeart>> filtering(PostFilteringRequest filteringRequest) {
        return ResponseEntity.ok(postService.filtering(filteringRequest));
    }

    @PostMapping("/{post_id}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createHeart(@PathVariable final Long post_id){
        heartService.create(post_id);
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/{post_id}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteHeart(@PathVariable final Long post_id){
        heartService.delete(post_id);
        return "좋아요가 취소되었습니다.";
    }
}
