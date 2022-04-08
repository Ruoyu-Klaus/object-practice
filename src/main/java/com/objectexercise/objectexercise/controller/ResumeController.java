package com.objectexercise.objectexercise.controller;


import com.objectexercise.objectexercise.controller.responseDTO.ResumeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @PostMapping("")
    public ResumeResponse createResume(){
        return null;
    }

}
