package com.sample.demo.sign.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.sign.domain.Member;
import com.sample.demo.sign.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign")
@Slf4j
public class SignController {

    private final MemberService memberService;

    @Autowired
    SignController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/in-page")
    public String goSignInPage(Model model) {
        log.debug("sign in page");
        model.addAttribute("data1", "테스트 입니다.");
        return "sign";
    }

    @GetMapping("/up-page")
    public String goSignUpPage() {
        log.debug("sign up page");
        return "signUp";
    }

    @PostMapping("/in")
    public ResponseEntity<?> signIn(@RequestBody Member member, HttpSession session) {
        if (!memberService.isMember(member)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 정보 없음");
        }

        member = memberService.findById(member.getId());

        try {
            session.setAttribute("auth", new ObjectMapper().writeValueAsString(member));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body("로그인 성공");
    }

    @PostMapping("/up")
    public ResponseEntity<?> signUp(@Valid @RequestBody Member member) {
        log.debug("join start!");
        memberService.join(member);
        return ResponseEntity.ok().body("회원가입 성공");
    }

}
