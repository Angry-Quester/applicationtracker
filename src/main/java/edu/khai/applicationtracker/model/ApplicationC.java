package edu.khai.applicationtracker.model;

import org.hibernate.validator.constraints.NotEmpty;


public class ApplicationC extends Application {

    private static final long serialVersionUID = -1404967325013613791L;
    @NotEmpty(message="Empty!")
    private String cField;

    /**
     * @return the cField
     */
    public String getcField() {
        return cField;
    }

    /**
     * @param cField the cField to set
     */
    public void setcField(String cField) {
        this.cField = cField;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cField == null) ? 0 : cField.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApplicationC other = (ApplicationC) obj;
        if (cField == null) {
            if (other.cField != null)
                return false;
        } else if (!cField.equals(other.cField))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ApplicationC [cField=" + cField + "]";
    }

}