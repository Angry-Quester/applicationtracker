package edu.khai.applicationtracker.service.impl;

import java.util.List;

import edu.khai.applicationtracker.dao.ApplicationTypeDAO;
import edu.khai.applicationtracker.model.ApplicationType;
import edu.khai.applicationtracker.service.ApplicationTypeService;

public class ApplicationTypeServiceImpl implements ApplicationTypeService{

    private ApplicationTypeDAO applicationTypeDAO;

    /**
     * @return the applicationTypeDAO
     */
    public ApplicationTypeDAO getApplicationTypeDAO() {
        return applicationTypeDAO;
    }

    /**
     * @param applicationTypeDAO the applicationTypeDAO to set
     */
    public void setApplicationTypeDAO(ApplicationTypeDAO applicationTypeDAO) {
        this.applicationTypeDAO = applicationTypeDAO;
    }

    @Override
    public ApplicationType getApplicationType(Long applicationTypeId) {
        return applicationTypeDAO.find(applicationTypeId);
    }

    /* (non-Javadoc)
     * @see edu.khai.applicationtracker.service.ApplicationTypeService#getApplicationTypes()
     */
    @Override
    public List<ApplicationType> getApplicationTypes() {

        return applicationTypeDAO.list();
    }


}