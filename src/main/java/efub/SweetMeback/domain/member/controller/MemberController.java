package efub.SweetMeback.domain.member.controller;

import efub.SweetMeback.domain.member.dto.MemberResponseDto;
import efub.SweetMeback.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(){
        return memberService.getMember();
    }
}
