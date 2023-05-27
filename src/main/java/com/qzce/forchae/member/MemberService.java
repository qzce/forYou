package com.qzce.forchae.member;

import com.qzce.forchae.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void doSignUp(MemberRequestDto dto) {

        String password = dto.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        dto.setPassword(encodePassword);

        memberRepository.save(dto.toEntity());
    }

    public Optional<Member> searchMember(String mname) {
        return memberRepository.findByMname(mname);
    }


    /* 회원가입 시, 유효성 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> vResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            vResult.put(validKeyName, error.getDefaultMessage());
        }

        return vResult;
    }
    
    @Transactional(readOnly = true)
    public void mnameDuplicationCheck(MemberRequestDto dto) {
        boolean checkDto = memberRepository.existsByMname(dto.getMname());
        if(checkDto) {
            throw new IllegalStateException("중복된 아이디입니다");
        }
    }


}
