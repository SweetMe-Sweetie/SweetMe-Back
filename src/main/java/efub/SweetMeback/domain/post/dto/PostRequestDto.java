package efub.SweetMeback.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.post.entity.Category;
import efub.SweetMeback.domain.post.entity.Contact;
import efub.SweetMeback.domain.post.entity.Meeting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private Integer memberId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadLine;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Integer people;
    private Category category;
    private Meeting meeting;
    private Contact contact;
}
