package efub.SweetMeback.domain.post.dto;

import efub.SweetMeback.domain.post.entity.Category;
import efub.SweetMeback.domain.post.entity.Contact;
import efub.SweetMeback.domain.post.entity.Meeting;
import efub.SweetMeback.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String memberName;
    private String profileImage;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime deadLine;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer people;
    private Integer view;
    private boolean recruitment;
    private Category category;
    private Meeting meeting;
    private Contact contact;
    private boolean promotion;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.memberName = post.getMember().getNickname();
        this.profileImage = post.getMember().getProfileImage();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.deadLine = post.getDeadline();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.people = post.getPeople();
        this.view = post.getView();
        this.recruitment = post.isRecruitment();
        this.category = post.getCategory();
        this.meeting = post.getMeeting();
        this.contact = post.getContact();
        this.promotion = post.isPromotion();
    }
}
