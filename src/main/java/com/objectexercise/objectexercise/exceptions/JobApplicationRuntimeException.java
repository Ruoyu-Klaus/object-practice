package com.objectexercise.objectexercise.exceptions;

public class JobApplicationRuntimeException extends RuntimeException {
    public JobApplicationRuntimeException(String message) {
        super(message);
    }

    public static JobApplicationRuntimeException JobApplicationNotFound() {
        return new JobApplicationRuntimeException("Job Application not found");
    }
}
