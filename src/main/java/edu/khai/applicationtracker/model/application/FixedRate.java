package edu.khai.applicationtracker.model.application;

import edu.khai.applicationtracker.model.Application;

public class FixedRate extends Application {

    private static final long serialVersionUID = 3652323150005009751L;

    Float rate;

    /**
     * @return the rate
     */
    public Float getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Float rate) {
        this.rate = rate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
        FixedRate other = (FixedRate) obj;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FixedRate [rate=" + rate + "]";
    }

}