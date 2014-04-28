package edu.khai.applicationtracker.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TestModel {

    private Long testIntegerField;

    @NotEmpty(message="Empty!")
    @Email(message="E-Mail is incorrect!")
    @Size(min=5, max=30, message="To big!")
    private String testField;

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }

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


}
