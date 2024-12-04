package com.sample.demo.api.redis.repository;

import com.sample.demo.api.redis.domain.AdminCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// custom version -> crudrepository 사용 방식으로 변경
@Repository
public class AdminCodeRepository implements CrudRepository<AdminCode, String> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AdminCodeRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Redis에 AdminCode 저장

    @Override
    public <S extends AdminCode> S save(S entity) {
        redisTemplate.opsForValue().set(entity.getCode(), entity);
        return entity;
    }

    @Override
    public <S extends AdminCode> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    // Redis에서 AdminCode 조회
    @Override
    public Optional<AdminCode> findById(String code) {
        AdminCode adminCode = (AdminCode) redisTemplate.opsForValue().get(code);
        return Optional.ofNullable(adminCode);
    }

    @Override
    public boolean existsById(String code) {
        return false;
    }

    @Override
    public Iterable<AdminCode> findAll() {
        return null;
    }

    @Override
    public Iterable<AdminCode> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String code) {
        redisTemplate.delete(code);
    }

    @Override
    public void delete(AdminCode entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends AdminCode> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
