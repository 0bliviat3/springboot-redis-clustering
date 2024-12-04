package com.sample.demo.api.redis.web;

import com.sample.demo.api.redis.domain.AdminCode;
import com.sample.demo.api.redis.service.AdminCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RedisAPIController {

    private final AdminCodeService adminCodeService;

    @Autowired
    public RedisAPIController(final AdminCodeService adminCodeService) {
        this.adminCodeService = adminCodeService;
    }

    @GetMapping("/code/{code}")
    public AdminCode getAdminCode(@PathVariable String code) {
        log.debug("redis add req: " + code);
        return adminCodeService.find(code).orElse(new AdminCode());
    }

    @GetMapping("/codes")
    public List<AdminCode> getAdminCodeList() {
        log.debug("get redis adminCodes");
        return adminCodeService.findAll();
    }

    @PostMapping("/code")
    public AdminCode addAdminCode(@RequestBody AdminCode adminCode) {
        log.debug("redis add req: " + adminCode.toString());
        adminCodeService.save(adminCode);
        return adminCode;
    }

    @DeleteMapping("/code/{code}")
    public void deleteAdminCode(@PathVariable String code) {
        adminCodeService.delete(code);
    }

}
