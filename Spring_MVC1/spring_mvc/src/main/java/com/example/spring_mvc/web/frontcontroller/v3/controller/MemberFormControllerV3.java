package com.example.spring_mvc.web.frontcontroller.v3.controller;

import com.example.spring_mvc.web.frontcontroller.ModelView;
import com.example.spring_mvc.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
