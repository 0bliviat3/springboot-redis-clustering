package com.sample.demo.api.redis.service;

import com.sample.demo.api.redis.domain.AdminCode;

import java.util.List;
import java.util.Optional;

public interface AdminCodeService {

    public void save(AdminCode adminCode);
    public Optional<AdminCode> find(String code);
    public void delete(String code);

    public List<AdminCode> findAll();

}
