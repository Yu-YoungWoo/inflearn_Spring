package com.example.spring_mvc.web.frontcontroller.v4.controller;

import com.example.spring_mvc.domain.member.Member;
import com.example.spring_mvc.domain.member.MemberRepository;
import com.example.spring_mvc.web.frontcontroller.ModelView;
import com.example.spring_mvc.web.frontcontroller.v3.ControllerV3;
import com.example.spring_mvc.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members", members);

        return "members";
    }
}
