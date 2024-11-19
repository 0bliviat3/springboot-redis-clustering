package com.sample.demo.sign.repository.impl;

import com.sample.demo.sign.domain.Member;
import com.sample.demo.sign.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<String, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(String id) {
        return store.get(id);
    }
}
