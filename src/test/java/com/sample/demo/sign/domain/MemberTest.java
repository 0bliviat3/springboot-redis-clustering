package com.sample.demo.sign.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class MemberTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void 빈값_확인_테스트() {
        Member m = new Member();
        m.setId("");

        Set<ConstraintViolation<Member>> violationSet = validator.validate(m);
        for (ConstraintViolation<Member> validation : violationSet) {
            System.out.println(validation);
            System.out.println(validation.getMessage());
        }

    }

    @Test
    @DisplayName("jackson lib 테스트")
    void memberToJSON () {
        Member m = new Member();
        m.setId("aaa");
        m.setName("name_aaa");
        m.setPwd("pwd_aaa");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String s = objectMapper.writeValueAsString(m);

            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
