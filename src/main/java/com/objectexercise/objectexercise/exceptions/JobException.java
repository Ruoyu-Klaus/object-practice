package com.objectexercise.objectexercise.exceptions;

public class JobException extends RuntimeException {

    public JobException(String message) {
        super(message);
    }

    public static JobException JobNotFound() {
        return new JobException("Job not found");
    }

    public static JobException JobHasSaved() {
        return new JobException("Job has been saved");
    }

}
