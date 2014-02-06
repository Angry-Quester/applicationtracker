package edu.khai.applicationtracker.model;

public class AppUserUserRole extends BaseObject{
	/**
	 *
	 */
	private static final long serialVersionUID = -8671726963351901022L;
	private Long appUserUserRoleId;
	private AppUser appUser;
	private UserRole userRole;
	/**
	 * @return the idAppUserUserRole
	 */
	public Long getAppUserUserRoleId() {
		return appUserUserRoleId;
	}
	/**
	 * @param appUserUserRoleId the idAppUserUserRole to set
	 */
	public void setAppUserUserRoleId(Long appUserUserRoleId) {
		this.appUserUserRoleId = appUserUserRoleId;
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
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appUser == null) ? 0 : appUser.hashCode());
		result = prime
				* result
				+ ((appUserUserRoleId == null) ? 0 : appUserUserRoleId
						.hashCode());
		result = prime * result
				+ ((userRole == null) ? 0 : userRole.hashCode());
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
		AppUserUserRole other = (AppUserUserRole) obj;
		if (appUser == null) {
			if (other.appUser != null)
				return false;
		} else if (!appUser.equals(other.appUser))
			return false;
		if (appUserUserRoleId == null) {
			if (other.appUserUserRoleId != null)
				return false;
		} else if (!appUserUserRoleId.equals(other.appUserUserRoleId))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppUserUserRole [idAppUserUserRole=" + appUserUserRoleId
								+ ", appUser=" + appUser
								+ ", userRole=" + userRole + "]";
	}


}