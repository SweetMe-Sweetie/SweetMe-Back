package efub.SweetMeback.domain.post.dto;

import efub.SweetMeback.domain.post.entity.Category;
import efub.SweetMeback.domain.post.entity.Sort;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFilteringRequest {

    private boolean recruitment;

    private Category category;

    private Sort sort;
}
