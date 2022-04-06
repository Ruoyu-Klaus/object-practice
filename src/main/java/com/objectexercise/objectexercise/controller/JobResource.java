package com.objectexercise.objectexercise.controller;

import com.objectexercise.objectexercise.controller.responseDTO.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/v1/jobs") @RequiredArgsConstructor
public class JobResource {

    @GetMapping("")
    public CommonResponse<Object> getJobs() {
        return CommonResponse.builder().data("jobs").build();
    }
}
