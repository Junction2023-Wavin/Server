package com.junction.hackathon.global.config.security.oauth;

import com.junction.hackathon.domain.member.domain.entity.Member;
import com.junction.hackathon.domain.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberQueryService memberQueryService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = this.memberQueryService.getMemberByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new CustomUserDetails(member);
    }
}

