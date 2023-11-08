package efub.SweetMeback.domain.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sort {
    DATE("최신순"),
    VIEW("조회수순"),
    HEART("좋아요순");

    private final String detail;
}
