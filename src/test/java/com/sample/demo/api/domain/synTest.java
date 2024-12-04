package com.sample.demo.api.domain;

import org.junit.jupiter.api.Test;
import org.springframework.data.keyvalue.core.IterableConverter;

public class synTest {

    @Test
    void iterableTest() {
        System.out.println(IterableConverter.toList(null));
    }

}
