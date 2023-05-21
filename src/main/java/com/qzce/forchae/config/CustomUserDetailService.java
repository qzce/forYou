package com.qzce.forchae.config;

import com.qzce.forchae.member.Member;
import com.qzce.forchae.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByMname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user."));

        return User.builder()
                .username(member.getMname())
                .password(member.getPassword())
                .authorities("DEFAULT")
                .build();
    }
}
