package com.objectexercise.objectexercise.exceptions;

public class ResumeException extends RuntimeException {
    public ResumeException(String message) {
        super(message);
    }

    public static ResumeException ResumeNotFound(){
        return new ResumeException("Resume not found");
    }

    public static ResumeException ResumeIsRequired(){
        return new ResumeException("Resume is required");
    }
}
