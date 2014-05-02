package edu.khai.applicationtracker.model.application;

/**
 * Контракт (Форма 1)
 *
 *  Родитель для
 *  Формы 1а == ApplicationForm11 (Дополнительного соглашения)
 *  Формы 1в == ApplicationForm13 (Заявление к контракту)
 *  Формы 1п == ApplicationForm14 (Дополнительное соглашение для пенсионеров)
 *
 * Заявление не несёт информации о ставках
 *
 * @author Quester
 *
 */
public class ApplicationForm1 extends NoRate {

    private static final long serialVersionUID = -1253069388098152146L;

    private Long contractNumber;

    /**
     * @return the contractNumber
     */
    public Long getContractNumber() {
        return contractNumber;
    }

    /**
     * @param contractNumber the contractNumber to set
     */
    public void setContractNumber(Long contractNumber) {
        this.contractNumber = contractNumber;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((contractNumber == null) ? 0 : contractNumber.hashCode());
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
        ApplicationForm1 other = (ApplicationForm1) obj;
        if (contractNumber == null) {
            if (other.contractNumber != null)
                return false;
        } else if (!contractNumber.equals(other.contractNumber))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ApplicationForm1 [contractNumber=" + contractNumber + "]";
    }
}