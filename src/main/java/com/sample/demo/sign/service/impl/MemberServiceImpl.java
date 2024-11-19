package com.sample.demo.sign.service.impl;

import com.sample.demo.sign.domain.Member;
import com.sample.demo.sign.repository.MemberRepository;
import com.sample.demo.sign.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    MemberServiceImpl(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public boolean isMember(Member member) {
        Member preMember = findById(member.getId());
        if (preMember == null) {
            return false;
        }
        return member.getPwd().equals(preMember.getPwd());
    }
}
