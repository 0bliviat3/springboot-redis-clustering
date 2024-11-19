package com.sample.demo.sign.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class Member {

    @NotBlank
    private String id;
    @NotBlank
    private String pwd;
    @NotBlank
    private String name;

}
