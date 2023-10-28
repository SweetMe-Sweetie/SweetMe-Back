package efub.SweetMeback.domain.heart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDto {
    private Long memberId;

    @Builder
    public HeartRequestDto(Long memberId){
        this.memberId=memberId;
    }
}
