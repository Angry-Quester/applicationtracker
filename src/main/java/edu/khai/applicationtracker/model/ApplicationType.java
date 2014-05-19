package edu.khai.applicationtracker.model;

import java.util.HashSet;
import java.util.Set;

public class ApplicationType extends BaseObject {

    private static final long serialVersionUID = -6443052395300638511L;

    private Long id;
    private Long applicationTypeId;
    private String abbreviation;
    private String shortName;
    private String fullName;
    private String description;
    private String viewAttribute;


    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
        this.applicationTypeId = id;
    }

    /**
     * @return the applicationTypeId
     */
    public Long getApplicationTypeId() {
        return applicationTypeId;
    }

    /**
     * @param applicationTypeId the applicationTypeId to set
     */
    public void setApplicationTypeId(Long applicationTypeId) {
        this.id = applicationTypeId;
        this.applicationTypeId = applicationTypeId;
    }

    /**
     * @return the abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @param abbreviation the abbreviation to set
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the viewAttribute
     */
    public String getViewAttribute() {
        return viewAttribute;
    }

    /**
     * @param viewAttribute the viewAttribute to set
     */
    public void setViewAttribute(String viewAttribute) {
        this.viewAttribute = viewAttribute;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime
                * result
                + ((applicationTypeId == null) ? 0 : applicationTypeId
                        .hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((shortName == null) ? 0 : shortName.hashCode());
        result = prime * result
                + ((viewAttribute == null) ? 0 : viewAttribute.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApplicationType other = (ApplicationType) obj;
        if (abbreviation == null) {
            if (other.abbreviation != null)
                return false;
        } else if (!abbreviation.equals(other.abbreviation))
            return false;
        if (applicationTypeId == null) {
            if (other.applicationTypeId != null)
                return false;
        } else if (!applicationTypeId.equals(other.applicationTypeId))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        if (viewAttribute == null) {
            if (other.viewAttribute != null)
                return false;
        } else if (!viewAttribute.equals(other.viewAttribute))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ApplicationType [id=" + id + ", applicationTypeId="
                + applicationTypeId + ", abbreviation=" + abbreviation
                + ", shortName=" + shortName + ", fullName=" + fullName
                + ", description=" + description + ", viewAttribute="
                + viewAttribute + "]";
    }

}