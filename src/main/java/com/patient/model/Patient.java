package com.patient.model;



import static java.lang.String.format;

public class Patient {

    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private String gender;
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Patient(String first_name, String middle_name, String last_name, int age, String gender, String phone) {
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return
                format("%20s%20s%16s%4d%8s%16s", lastName, firstName , middleName, age, gender, phone ) ;
    }
}
