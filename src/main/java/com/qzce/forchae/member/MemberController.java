package com.qzce.forchae.member;

import com.qzce.forchae.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/joinProc")
    public String joinProc(Model model, @Valid MemberRequestDto dto, Errors errors) {
        
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("dto", dto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            /* 회원가입 페이지로 다시 리턴 */
            return "/signup";
        }
        
        // 중복 이름 체크
        memberService.mnameDuplicationCheck(dto);

        // 가입
        memberService.doSignUp(dto);

        return "redirect:/signUpMessage";
    }

}
