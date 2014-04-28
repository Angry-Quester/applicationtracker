package edu.khai.applicationtracker.service;

import java.util.List;

import edu.khai.applicationtracker.model.ApplicationType;

public interface ApplicationTypeService {
    public ApplicationType getApplicationType(Long applicationTypeId);
    public List<ApplicationType> getApplicationTypes();
}