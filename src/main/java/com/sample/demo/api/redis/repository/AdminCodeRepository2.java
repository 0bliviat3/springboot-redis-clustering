package com.sample.demo.api.redis.repository;

import com.sample.demo.api.redis.domain.AdminCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCodeRepository2 extends CrudRepository<AdminCode, String> {
}
