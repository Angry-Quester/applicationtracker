package edu.khai.applicationtracker.model;

public class AppUserApplication extends BaseObject{

	private static final long serialVersionUID = -3628522496430525353L;

	private Long appUserApplicationId;
	private AppUser appUser;
	private Application application;
	/**
	 * @return the appUserApplicationId
	 */
	public Long getAppUserApplicationId() {
		return appUserApplicationId;
	}
	/**
	 * @param appUserApplicationId the appUserApplicationId to set
	 */
	public void setAppUserApplicationId(Long appUserApplicationId) {
		this.appUserApplicationId = appUserApplicationId;
	}
	/**
	 * @return the appUser
	 */
	public AppUser getAppUser() {
		return appUser;
	}
	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((appUserApplicationId == null) ? 0 : appUserApplicationId
						.hashCode());
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
		AppUserApplication other = (AppUserApplication) obj;
		if (appUserApplicationId == null) {
			if (other.appUserApplicationId != null)
				return false;
		} else if (!appUserApplicationId.equals(other.appUserApplicationId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppUserApplication [appUserApplicationId="
				+ appUserApplicationId + "]";
	}

}