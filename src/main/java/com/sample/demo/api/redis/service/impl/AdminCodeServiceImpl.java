package com.sample.demo.api.redis.service.impl;

import com.sample.demo.api.redis.domain.AdminCode;
import com.sample.demo.api.redis.repository.AdminCodeRepository;
import com.sample.demo.api.redis.repository.AdminCodeRepository2;
import com.sample.demo.api.redis.service.AdminCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminCodeServiceImpl implements AdminCodeService {

    private final AdminCodeRepository2 adminCodeRepository;

    @Autowired
    public AdminCodeServiceImpl(final AdminCodeRepository2 adminCodeRepository) {
        this.adminCodeRepository = adminCodeRepository;
    }

    @CachePut(cacheNames="adminCode", key="#adminCode.code", unless="#result == null")
    @Override
    public void save(AdminCode adminCode) {
        adminCodeRepository.save(adminCode);

    }

    @Cacheable(cacheNames="adminCode", key="#code")
    @Override
    public Optional<AdminCode> find(String code) {
        return adminCodeRepository.findById(code);
    }

    @CacheEvict(cacheNames="adminCode", key="#code")
    @Override
    public void delete(String code) {
        adminCodeRepository.deleteById(code);

    }

    @Override
    public List<AdminCode> findAll() {
        return IterableConverter.toList(adminCodeRepository.findAll());
    }
}
