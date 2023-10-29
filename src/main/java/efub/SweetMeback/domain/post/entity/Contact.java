package efub.SweetMeback.domain.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Contact {
    KAKAO_OPENCHAT("KAKAO_OPENCHAT", "카카오 오픈채팅"),
    GOOGLE_FORM("GOOGLE_FORM", "구글폼"),
    NAVER_FORM("NAVER_FORM", "네이퍼폼"),
    EMAIL("EMAIL", "이메일");

    private final String label;
    private final String detail;
}
