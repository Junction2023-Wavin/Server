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
public class MemberSaveService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }
}