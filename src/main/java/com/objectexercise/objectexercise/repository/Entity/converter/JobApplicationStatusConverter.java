package com.objectexercise.objectexercise.repository.Entity.converter;

import com.objectexercise.objectexercise.controller.DTO.ApplicationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JobApplicationStatusConverter implements AttributeConverter<ApplicationStatus,String> {
    @Override
    public String convertToDatabaseColumn(ApplicationStatus applicationStatus) {
        if(applicationStatus == null) return null;
        return applicationStatus.name();
    }

    @Override
    public ApplicationStatus convertToEntityAttribute(String status) {
        if(status == null || status.isEmpty()) return null;
        return ApplicationStatus.valueOf(status);
    }
}
