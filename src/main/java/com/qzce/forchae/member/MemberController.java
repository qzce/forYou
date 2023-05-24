package com.qzce.forchae.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/joinProc")
    public String joinProc(Model model, Member member) {

        Optional<Member> memberExist = memberService.searchMember(member.getMname());

        if(memberExist.isPresent()) {
            model.addAttribute("message", "중복된 이름입니다");
            model.addAttribute("searchUrl", "/signup");
            return "message";
        }

        String password = member.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        member.setPassword(encodePassword);

        memberService.doSignUp(member);

        return "redirect:/signUpMessage";
    }

}
