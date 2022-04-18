package com.objectexercise.objectexercise.exceptions;

public class UserRuntimeException extends RuntimeException {
    public UserRuntimeException(String message){
        super(message);
    }

    public static UserRuntimeException UserNotFound(){
        return new UserRuntimeException("User not found");
    }
    public static UserRuntimeException EmployerNotFound(){
        return new UserRuntimeException("Employer not found");
    }
    public static UserRuntimeException JobSeekerNotFound(){
        return new UserRuntimeException("JobSeeker not found");
    }
    public static UserRuntimeException AuthorizationError(){
        return new UserRuntimeException("User have no authorization for this method");
    }
}
