package com.junction.hackathon.domain.member.domain.service;

import com.junction.hackathon.domain.member.domain.entity.Member;
import com.junction.hackathon.domain.member.domain.repository.MemberRepository;
import com.junction.hackathon.domain.member.exception.NotFoundByEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member getMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email).orElseThrow(()->new NotFoundByEmailException());
    }
}