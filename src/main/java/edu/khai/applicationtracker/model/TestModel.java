package edu.khai.applicationtracker.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TestModel {

    private Long testIntegerField;

    @NotEmpty
    @Email(message="E-Mail is incorrect!")
    @Size(min=5, max=30, message="To big!")
    private String testField;

    @Valid
    private TestModel testModel;

    private List<TestModel> testModels;

    /**
     * @return the testIntegerField
     */
    public Long getTestIntegerField() {
        return testIntegerField;
    }

    /**
     * @param testIntegerField the testIntegerField to set
     */
    public void setTestIntegerField(Long testIntegerField) {
        this.testIntegerField = testIntegerField;
    }

    /**
     * @return the testField
     */
    public String getTestField() {
        return testField;
    }

    /**
     * @param testField the testField to set
     */
    public void setTestField(String testField) {
        this.testField = testField;
    }

    /**
     * @return the testModel
     */
    public TestModel getTestModel() {
        return testModel;
    }

    /**
     * @param testModel the testModel to set
     */
    public void setTestModel(TestModel testModel) {
        this.testModel = testModel;
    }

    /**
     * @return the testModels
     */
    public List<TestModel> getTestModels() {
        return testModels;
    }

    /**
     * @param testModels the testModels to set
     */
    public void setTestModels(List<TestModel> testModels) {
        this.testModels = testModels;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((testField == null) ? 0 : testField.hashCode());
        result = prime
                * result
                + ((testIntegerField == null) ? 0 : testIntegerField.hashCode());
        result = prime * result
                + ((testModel == null) ? 0 : testModel.hashCode());
        result = prime * result
                + ((testModels == null) ? 0 : testModels.hashCode());
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
        TestModel other = (TestModel) obj;
        if (testField == null) {
            if (other.testField != null)
                return false;
        } else if (!testField.equals(other.testField))
            return false;
        if (testIntegerField == null) {
            if (other.testIntegerField != null)
                return false;
        } else if (!testIntegerField.equals(other.testIntegerField))
            return false;
        if (testModel == null) {
            if (other.testModel != null)
                return false;
        } else if (!testModel.equals(other.testModel))
            return false;
        if (testModels == null) {
            if (other.testModels != null)
                return false;
        } else if (!testModels.equals(other.testModels))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestModel [testIntegerField=" + testIntegerField
                + ", testField=" + testField + ", testModel=" + testModel
                + ", testModels=" + testModels + "]";
    }

}
