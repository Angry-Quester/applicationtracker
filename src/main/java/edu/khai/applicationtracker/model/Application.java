package edu.khai.applicationtracker.model;

public class Application extends BaseObject {

	private static final long serialVersionUID = 259729176139504809L;

	private Long applicationId;
	private String applicationType;
	private Integer applicationData;

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}
	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	/**
	 * @return the applicationData
	 */
	public Integer getApplicationData() {
		return applicationData;
	}
	/**
	 * @param applicationData the applicationData to set
	 */
	public void setApplicationData(Integer applicationData) {
		this.applicationData = applicationData;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationData == null) ? 0 : applicationData.hashCode());
		result = prime * result
				+ ((applicationType == null) ? 0 : applicationType.hashCode());
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Application other = (Application) obj;
		if (applicationData == null) {
			if (other.applicationData != null) {
				return false;
			}
		} else if (!applicationData.equals(other.applicationData)) {
			return false;
		}
		if (applicationType == null) {
			if (other.applicationType != null) {
				return false;
			}
		} else if (!applicationType.equals(other.applicationType)) {
			return false;
		}
		if (applicationId == null) {
			if (other.applicationId != null) {
				return false;
			}
		} else if (!applicationId.equals(other.applicationId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicationType=" + applicationType
				+ ", applicationData=" + applicationData + "]";
	}

}