package efub.SweetMeback.domain.post.repository;

import efub.SweetMeback.domain.post.dto.PostFilteringRequest;
import efub.SweetMeback.domain.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> filteringAll(PostFilteringRequest filteringRequest);
}
