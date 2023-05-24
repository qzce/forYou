package com.qzce.forchae.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void doSignUp(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> searchMember(String mname) {
        return memberRepository.findByMname(mname);
    }


}
