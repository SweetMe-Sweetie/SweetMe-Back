package efub.SweetMeback.domain.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    FRONTEND("FRONTEND", "프론트엔드"),
    BACKEND("BACKEND", "백엔드"),
    ALGORITHM("ALGORITHM", "알고리즘"),
    GAME("GAME", "게임"),
    AI("AI", "인공지능"),
    DEV_ETC("DEV_ETC", "개발_기타"),
    ENGLISH("ENGLISH", "영어"),
    CHINESE("CHINESE", "중국어"),
    JAPANESE("JAPANESE", "일본어"),
    FRENCH("FRENCH", "프랑스어"),
    GERMAN("GERMAN", "독일어"),
    SPANISH("SPANISH", "스페인어"),
    LAN_ETC("LAN_ETC", "어학_기타"),
    ISSUE("ISSUE", "시사"),
    BOOK("BOOK", "독서"),
    EXERCISE("EXERCISE", "운동"),
    GET_UP("GET_UP", "기상"),
    DAILY_ETC("DAILY_ETC", "일상_기타");

    private final String label;
    private final String detail;
}
