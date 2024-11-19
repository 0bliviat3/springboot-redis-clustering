package com.sample.demo.sign.repository;

import com.sample.demo.sign.domain.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(String id);
}
