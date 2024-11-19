package com.sample.demo.sign.service;

import com.sample.demo.sign.domain.Member;

public interface MemberService {

    void join(Member member);
    Member findById(String id);
    boolean isMember(Member member);
}
