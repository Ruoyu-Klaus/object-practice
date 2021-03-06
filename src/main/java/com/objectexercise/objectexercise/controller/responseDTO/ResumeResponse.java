package com.objectexercise.objectexercise.controller.responseDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {

    private Integer id;

    @JsonProperty("title")
    private String name;

    private JobSeekerResponse jobSeeker;
}
