package com.example.model;

/**
 * Created by bcaramihai on 9/8/2016.
 */
public class User {
    private String firstName;
    private String lastName;
    private int age;

    public User (){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User other = (User) obj;
        if (this.getAge() != other.getAge() ||
                !this.getFirstName().equals(other.getFirstName()) ||
                !this.getLastName().equals(other.getLastName())) return false;

        // The two objects are equal
        return true;
    }
}