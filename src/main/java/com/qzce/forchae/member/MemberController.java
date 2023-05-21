package com.qzce.forchae.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/joinProc")
    public String joinProc(Member member) {

        String password = member.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(encodePassword);

        memberService.doSignUp(member);

        return "redirect:/signUpMessage";
    }

}
