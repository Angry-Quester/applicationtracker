package edu.khai.applicationtracker.model;

import java.util.HashSet;
import java.util.Set;

public class UserRole extends BaseObject{
    /**
     *
     */
    private static final long serialVersionUID = 8940350881770698263L;


    private Long id;
    private Long userRoleId;
    private String authority;

    private Set<AppUser> appUsers = new HashSet<AppUser>(0);


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
        this.userRoleId = id;
    }

    /**
     * @return the userRoleId
     */
    public Long getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
        this.id = userRoleId;
    }

    /**
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }


    /**
     * @return the appUserUserRoles
     */
    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    /**
     * @param appUserUserRoles the appUserUserRoles to set
     */
    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((authority == null) ? 0 : authority.hashCode());
        result = prime * result
                + ((userRoleId == null) ? 0 : userRoleId.hashCode());
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
        UserRole other = (UserRole) obj;
        if (authority == null) {
            if (other.authority != null)
                return false;
        } else if (!authority.equals(other.authority))
            return false;
        if (userRoleId == null) {
            if (other.userRoleId != null)
                return false;
        } else if (!userRoleId.equals(other.userRoleId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserRole [userRoleId=" + userRoleId + ", authority="
                + authority + "]";
    }



}