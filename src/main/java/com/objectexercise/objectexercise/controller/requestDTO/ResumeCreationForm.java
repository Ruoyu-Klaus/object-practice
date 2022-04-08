package com.objectexercise.objectexercise.controller.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ResumeCreationForm {

    @JsonProperty("title")
    @NotNull(message = "resume title must not be null")
    @Size(min = 4, max = 64, message = "resume title length is out of range")
    private String name;

}
